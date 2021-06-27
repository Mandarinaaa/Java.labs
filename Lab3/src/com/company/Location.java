package com.company;

/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    //реализация методов equals и hashCode

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Location) {
            return ((Location) obj).xCoord==xCoord && ((Location)obj).yCoord==yCoord;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return xCoord*100+yCoord;
    }






}
