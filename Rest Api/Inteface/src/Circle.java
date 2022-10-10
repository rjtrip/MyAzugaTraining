/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */
public class Circle implements Interface {

    private double radius;

    public Circle(double r){
        this.radius = r;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }

    @Override
    public double getArea(){
        return Math.PI*this.radius*this.radius;
    }

    public double getRadius(){
        return this.radius;
    }
}