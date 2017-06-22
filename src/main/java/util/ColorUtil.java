
package main.java.util;

/**
 * Provides some methods for converting colors.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public abstract class ColorUtil
{
    public static int getRgb(int red, int green, int blue)
    {
        red = (red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        green = (green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        blue = blue & 0x000000FF; //Mask out anything not blue.
        return 0xFF000000 | red | green | blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }
    public static int getArgb(int alpha, int red, int green, int blue)
    {
        alpha = (alpha << 24) & 0xFF000000; //Shift alpha 24-bits and mask out other stuff
        red = (red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        green = (green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        blue = blue & 0x000000FF; //Mask out anything not blue.
        return  alpha | red | green | blue; //Bitwise OR everything together.
    }
}
