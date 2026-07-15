#include <Servo.h>

Servo Gripper;
Servo WristPitch;
Servo WristRoll;
Servo Elbow;
Servo Shoulder;
Servo Base;

int posGripper = 0;
int posWristPitch = 0;
int posWristRoll = 0;
int posElbow = 0;
int posShoulder = 0;
int posBase = 0;

void setup() {
  Serial.begin(9600);

  Gripper.attach(3);
  WristPitch.attach(5);
  WristRoll.attach(6);
  Elbow.attach(9);
  Shoulder.attach(10);
  Base.attach(11);

  Gripper.write(posGripper);
  WristPitch.write(posWristPitch);
  WristRoll.write(posWristRoll);
  Elbow.write(posElbow);
  Shoulder.write(posShoulder);
  Base.write(posBase);
}

void loop() {
  if (Serial.available() > 0) {

    String data = Serial.readStringUntil('\n');
    data.trim(); 

    int separatorIndex = data.indexOf(':');
    if (separatorIndex != -1) {
      String motorName = data.substring(0, separatorIndex); 
      String angleStr = data.substring(separatorIndex + 1); 
      int degrees = angleStr.toInt();

      if (motorName == "ELBOW") {
        if (degreesIsGreater(degrees, posElbow)) {
          goUp(Elbow, posElbow, degrees);
        } else {
          goDown(Elbow, posElbow, degrees); 
        }
        posElbow = degrees;
      } 
      else if (motorName == "WRIST_ROLL") {
        if (degreesIsGreater(degrees, posWristRoll)) {
          goUp(WristRoll, posWristRoll, degrees);
        } else {
          goDown(WristRoll, posWristRoll, degrees); 
        }
        posWristRoll = degrees;
      } 
      else if (motorName == "WRIST_PITCH") {
        if (degreesIsGreater(degrees, posWristPitch)) {
          goUp(WristPitch, posWristPitch, degrees);
        } else {
          goDown(WristPitch, posWristPitch, degrees); 
        }
        posWristPitch = degrees;
      } 
      else if (motorName == "SHOULDER") {
        if (degreesIsGreater(degrees, posShoulder)) {
          goUp(Shoulder, posShoulder , degrees);
        } else {
          goDown(Shoulder, posShoulder, degrees); 
        }
        posShoulder = degrees;
      } 
      else if (motorName == "BASE") {
        if (degreesIsGreater(degrees, posBase)) {
          goUp(Base, posBase, degrees);
        } else {
          goDown(Base, posBase, degrees);
        }
        posBase = degrees;
      } 
      else if (motorName == "GRIPPER") {
        if (degrees > posGripper) {
          goUp(Gripper, posGripper, degrees);
        } else {
          goDown(Gripper, posGripper, degrees); 
        }
        posGripper = degrees;
      }
    }
    
    while(Serial.available() > 0){
      Serial.read();
    }
  } 
}

void goUp(Servo myServo, int startPos, int targetPos) {
  for (int pos = startPos; pos <= targetPos; pos++) {
    myServo.write(pos);
    delay(10);
  }
}

void goDown(Servo myServo, int startPos, int targetPos) {
  for (int pos = startPos; pos >= targetPos; pos--) {
    myServo.write(pos);
    delay(10);
  }
}

bool degreesIsGreater(int degrees, int targetAngle) {
  return degrees > targetAngle;
}
