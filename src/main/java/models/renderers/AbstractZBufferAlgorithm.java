
package main.java.models.renderers;

/**
 * The base implementation of a Z-Buffer Algorithm. 
 * Provides a buffer for the children.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public abstract class AbstractZBufferAlgorithm extends AbstractRenderer
{
    private short width;
    public int getWidth()
    {
        return this.width;
    }
    private short height;
    public int getHeight()
    {
        return this.height;
    }
    private float[][] zBuffer;
    public float[][] getZBuffer()
    {
        return this.zBuffer;
    }
    /**
     * Use at own risk.
     * @param newBuffer 
     */
    public void setZBuffer(float[][] newBuffer)
    {
        this.zBuffer = newBuffer;
    }
    public AbstractZBufferAlgorithm(short width, short height)
    {
        this.width = width;
        this.height = height;
        this.zBuffer = new float[width][height];
        clearDepthBuffer();
    }
    /**
     * Resets the Z-Buffer.
     */
    public void clearDepthBuffer()
    {
        for(short x = 0; x < this.width; x++)
        {
            for(short y = 0; y < this.height; y++)
            {
                this.zBuffer[x][y] = Float.MAX_VALUE;
            }
        }
    }
    @Override
    public void prepareForNewFrame()
    {
        clearDepthBuffer();
    }
}
