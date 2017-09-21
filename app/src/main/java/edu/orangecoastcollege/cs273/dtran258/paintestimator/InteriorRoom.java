package edu.orangecoastcollege.cs273.dtran258.paintestimator;

/**
 * Represents a four-walled room with a ceiling. Contains information about its dimensions and the
 * amount of paint in gallons need to paint the walls and the ceiling.
 *
 * @author Derek Tran
 * @version 1.0
 * @since September 19, 2017
 */

public class InteriorRoom
{
    private static final float DOOR_AREA = 21;
    private static final float SQ_FEET_PER_GALLON = 275;
    private static final float WINDOW_AREA = 16;

    private int mDoors;
    private float mHeight;
    private float mWidth;
    private float mLength;
    private int mWindows;

    /**
     * Gets the number of doors in the room.
     * @return The number of doors in the room.
     */
    public int getDoors()
    {
        return mDoors;
    }

    /**
     * Sets the number of doors in the room.
     * @param doors The number of doors in the room.
     */
    public void setDoors(int doors)
    {
        mDoors = doors;
    }

    /**
     * Gets the height (feet) of the room.
     * @return The height (feet) of the room.
     */
    public float getHeight()
    {
        return mHeight;
    }

    /**
     * Sets the height (feet) of the room.
     * @param height The height (feet) of the room.
     */
    public void setHeight(float height)
    {
        mHeight = height;
    }

    /**
     * Gets the width (feet) of the room.
     * @return The width (feet) of the room.
     */
    public float getWidth()
    {
        return mWidth;
    }

    /**
     * Sets the width (feet) of the room.
     * @param width The width (feet) of the room.
     */
    public void setWidth(float width)
    {
        mWidth = width;
    }

    /**
     * Gets the length (feet) of the room.
     * @return The length (feet) of the room.
     */
    public float getLength()
    {
        return mLength;
    }

    /**
     * Sets the length (feet) of the room.
     * @param length The length (feet) of the room.
     */
    public void setLength(float length)
    {
        mLength = length;
    }

    /**
     * Gets the number of windows in the room.
     * @return The number of windows in the room.
     */
    public int getWindows()
    {
        return mWindows;
    }

    /**
     * Sets the number of windows in the room.
     * @param windows The number of windows in the room.
     */
    public void setWindows(int windows)
    {
        mWindows = windows;
    }

    /**
     * Gets the combined surface area (square feet) of the windows and doors in the room.
     * @return The combined surface area (square feet) of the windows and doors in the room.
     */
    public float doorAndWindowArea()
    {
        return mDoors * DOOR_AREA + mWindows * WINDOW_AREA;
    }

    /**
     * Gets the combined surface area (square feet) of the walls and ceiling in the room.
     * @return The combined surface area (square feet) of the walls and ceiling in the room.
     */
    public float wallSurfaceArea()
    {
        return 2 * mHeight * (mWidth + mLength) + mLength * mWidth;
    }

    /**
     * Gets the combined area (square feet) of the room without the doors and windows.
     * @return The combined area (square feet) of the room without the doors and windows.
     */
    public float totalSurfaceArea()
    {
        return wallSurfaceArea() - doorAndWindowArea();
    }

    /**
     * Gets the gallons of paint required to paint the surface area of the room.
     * @return The gallons of paint required to paint the surface area of the room.
     */
    public float gallonsOfPaintRequired()
    {
        return totalSurfaceArea() / SQ_FEET_PER_GALLON;
    }
}
