package ru.stqa.pft.sandbox;


public class MyFirstProgram {
	public static void main(String[] args) {

		Square s = new Square(5);
		Rectangle r = new Rectangle(12, 13);

		Point p1 = new Point(12, 33);
		Point p2 = new Point(33, 111);

	    printText("Andrey");
	    printText("Crow");

		System.out.println("Площадь квадрата со стороной " + s.a + " = " + s.area());
		System.out.println("Площадь прямоугольника " + r.a + " и " + r.b + " = " + r.area());

		System.out.println("Расстояние между точками p1("+p1.x+";"+p1.y+") и p2("+p2.x+";"+p2.y+") равно: " + distance(p1, p2));
	}



	public static void printText(String sambody){
        System.out.println("Hello " + sambody);
    }


	public static double distance(Point p1, Point p2){

		double x = p2.x - p1.x;
		double y = p2.y - p2.y;
		double distance;
		return  distance = Math.sqrt(x*x+y*y);

	}


}