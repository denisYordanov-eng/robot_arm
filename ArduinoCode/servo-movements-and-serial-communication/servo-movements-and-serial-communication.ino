#include <Servo.h>

Servo Gripper;
Servo WristPitch;
Servo WristRoll;
Servo Elbow;
Servo Shoulder;//SHOULDER
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

    String data = Serial.readStringUntil('\n');//read data from the SerialPort
    data.trim(); //trim the data


    int separatorIndex = data.indexOf(':');//set the index of ':'
    String motorName = data.substring(0, separatorIndex); //extract the name of the servo motor
    String angleStr = data.substring(separatorIndex + 1); //extract the angle as a string

    int degrees = angleStr.toInt();// parse the angle to int
   
    if (motorName == "ELBOW") {
      if (degreesIsGreater(degrees, posElbow)) {
        goUp(Elbow, posElbow, degrees);
      } else {
        goDown(Elbow, degrees, posElbow);
      }
      posElbow = degrees;
    } else if (motorName == "WRIST_ROLL") {
      if (degreesIsGreater(degrees, posWristRoll)) {
        goUp(WristRoll, posWristRoll, degrees);
      } else {
        goDown(WristRoll, degrees, posWristRoll);
      }
      posWristRoll = degrees;
    } else if (motorName == "WRIST_PITCH") {
      if (degreesIsGreater(degrees, posWristPitch)) {
        goUp(WristPitch, posWristPitch, degrees);
      } else {
        goDown(WristPitch, degrees, posWristPitch);
      }
      posWristPitch = degrees;
    } else if (motorName == "SHOULDER") {
      if (degreesIsGreater(degrees, posShoulder)) {
        goUp(Shoulder, posShoulder , degrees);
      } else {
        goDown(Shoulder, degrees, posShoulder);
      }
      posShoulder = degrees;
    } else if (motorName == "BASE") {
      if (degreesIsGreater(degrees, posBase)) {
        goUp(Base, posBase, degrees);
      } else {
        goDown(Base, degrees, posBase);
      }
      posBase = degrees;
    } else if (motorName == "GRIPPER") {
      if (degrees > posGripper) {
        goUp(Gripper, posGripper, degrees);
      } else {
        goDown(Gripper, degrees, posGripper);
      }
      posGripper = degrees;
    }
  }
}

void goUp(Servo myServo, int startingPos, int endingPos) {
  for (int pos = startingPos; pos <= endingPos; pos++) {
    myServo.write(pos);
    delay(10);
  }
}

void goDown(Servo servo, int startingPosition, int targetPosition) {
  for (int pos = startingPosition; pos >= targetPosition; pos--) {
    servo.write(pos);
    delay(10);
  }
}

bool degreesIsGreater(int degrees, int targetAngle) {
  return degrees > targetAngle;
}
