package com.company;

public class Lab1 {

   public static void main(String[] args) {
       Point3d point1 = new Point3d(1,2,3);
       Point3d point2 = new Point3d(9,3,1);
       Point3d point3 = new Point3d(5,6,9);

       if (point1.equals(point2) | point1.equals(point3) | point2.equals(point3)){
         System.out.println("Введены неверные координаты точек");
       }
       else{
         System.out.println(computeArea ( point3,  point2,  point1));
       }
   }
   public static double computeArea (Point3d point1, Point3d point2, Point3d point3) {
        double p = (point1.distanceTo(point2) + point1.distanceTo(point3) + point2.distanceTo(point3))/2;
        double a = point1.distanceTo(point2);
        double b = point1.distanceTo(point3);
        double c = point2.distanceTo(point3);
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
}
