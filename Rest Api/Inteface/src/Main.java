/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */
public class Main {
    public static void main(String[] args) {

        //programming for interfaces not implementation
        Interface shape = new Circle(10);

        shape.draw();
        System.out.println("Area="+shape.getArea());

        //switching from one implementation to another easily
        shape=new Rectangle(10,10);
        shape.draw();
        System.out.println("Area="+shape.getArea());
    }
}