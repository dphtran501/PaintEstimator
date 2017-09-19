package edu.orangecoastcollege.cs273.dtran258.paintestimator;

/**
 * Created by Jeannie on 9/19/2017.
 */

public class InteriorRoom
{
    public static final float DOOR_AREA = 21;
    public static final float SQ_FEET_PER_GALLON = 275;
    public static final float WINDOW_AREA = 16;

    private int mDoors;
    private float mHeight;
    private float mWidth;
    private float mLength;
    private int mWindows;

    public int getDoors()
    {
        return mDoors;
    }

    public void setDoors(int doors)
    {
        mDoors = doors;
    }

    public float getHeight()
    {
        return mHeight;
    }

    public void setHeight(float height)
    {
        mHeight = height;
    }

    public float getWidth()
    {
        return mWidth;
    }

    public void setWidth(float width)
    {
        mWidth = width;
    }

    public float getLength()
    {
        return mLength;
    }

    public void setLength(float length)
    {
        mLength = length;
    }

    public int getWindows()
    {
        return mWindows;
    }

    public void setWindows(int windows)
    {
        mWindows = windows;
    }

    public float doorAndWindowArea()
    {
        return mDoors * DOOR_AREA + mWindows * WINDOW_AREA;
    }

    public float wallSurfaceArea()
    {
        return 2 * mHeight * (mWidth + mLength) + mLength * mWidth;
    }

    public float totalSurfaceArea()
    {
        return wallSurfaceArea() - doorAndWindowArea();
    }

    public float gallonsOfPaintRequired()
    {
        return totalSurfaceArea() / SQ_FEET_PER_GALLON;
    }
}
