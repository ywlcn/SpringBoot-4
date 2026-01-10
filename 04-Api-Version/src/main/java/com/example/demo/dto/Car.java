package com.example.demo.dto;

import lombok.Data;

@Data
public class Car {

    String name;

    public Car(){
        //System.out.println("init");
        this.name = "init";
    }

    public Car(String text){
        this.name = text;
        //System.out.println(text);
    }

}
