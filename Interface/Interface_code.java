public class Interface_code {


    public static void main(String[] args) {

        //programming for interfaces not implementation
        Interface_task shape = new Circle(10);

        shape.draw();
        System.out.println("Area="+shape.getArea());

        //switching from one implementation to another easily
        shape=new Rectangle(10,10);
        shape.draw();
        System.out.println("Area="+shape.getArea());
    }

}