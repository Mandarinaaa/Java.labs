package com.company;

public class Point3d extends Point2d {
    private double zCoord;
    public Point3d(double x, double y, double z){
        super(x,y);
        zCoord=z;
    }

    public Point3d(){
        super();
        zCoord=0;
    }
    public double getZ () {
        return zCoord;
    }
    public void setZ ( double val) {
        zCoord = val;
    }

    @Override
    public boolean equals(Object obj){
        Point3d point = (Point3d)obj;
        if (point.getX()==this.getX() & point.getY()==this.getY() & point.getZ()==this.getZ()){
            return true;
        }

        return false;


    }
    public double distanceTo(Point3d point){
        double d = Math.sqrt((point.getX()-this.getX())*(point.getX()-this.getX())+(point.getY()-this.getY())*(point.getY()-this.getY())+(point.getZ()-this.getZ())*(point.getZ()-this.getZ()));
        d = d*100;
        int t = (int) d;
        double k = t;
        k = k/100;
        return k;

    }


}
