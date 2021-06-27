package com.company;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public  class BurningShip extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;
    //Rectangle2D - создает прямоугольник
    //getInitialRange - меняет координаты в прямоугольнике

    @Override
    public void getInitialRange (Rectangle2D.Double range) { //вычисляем область фрактала на плоскости, потом разбиваем на пиксели дисплея и соотносим
        range.x = -2;
        range.y = -2.5;
        range.height = 4;
        range.width = 4;
    }

    @Override
    public int numIterations(double x, double y) {// подсчет иттерации для каждой точки
        int countIterations=0;
        int n = 0;
        double a=0,b=0;
        double c=0,d=0;
        //System.out.print(countIterations);
        while(a*a+b*b<4) { // a+bi * a+bi = a^2-b^2+2abi
            c=a;
            d=b;
            a = c*c-d*d + x;
            b = Math.abs(2*c*d) + y;

            if(countIterations++ ==MAX_ITERATIONS)
                return -1;
        }
        return countIterations;
    }

}
