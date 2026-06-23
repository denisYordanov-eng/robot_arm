package com.robot_arm.demo.jsonMapperSingleton;

import com.fasterxml.jackson.databind.json.JsonMapper;

public class JsonMapperSingleton {
    private static JsonMapper jsonMapperSingleton = new JsonMapper();

    private JsonMapperSingleton(){
    }

    public static JsonMapper jsonMapperSingleton(){
        return jsonMapperSingleton;
    }
}
