
package main.java.models.camera;


/**
 * Is able to build a {@see main.java.models.camera.SimpleCamera}.
 * 
 * @author Elwin Slokker
 * @version 0.3
 */
public class SimpleCameraBuilder extends BaseCameraBuilder<SimpleCamera>
{   
    /**
     * Simply creates a builder to use.
     */
    public SimpleCameraBuilder()
    {
    }

    /**
     * @return a {@see main.java.models.camera.SimpleCamera} object. Should be called after specifying the base builder values.
     */
    @Override
    public SimpleCamera build()
    {
        return new SimpleCamera(this);
    }
}
