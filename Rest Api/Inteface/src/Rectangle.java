/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */
public class Rectangle implements Interface {

    private double width;
    private double height;

    public Rectangle(double w, double h){
        this.width=w;
        this.height=h;
    }
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }

    @Override
    public double getArea() {
        return this.height*this.width;
    }

}
