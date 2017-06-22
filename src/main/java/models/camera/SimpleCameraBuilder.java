
package main.java.models.camera;


/**
 * Is able to build a {@see main.java.models.camera.SimpleCamera}.
 * 
 * @author Elwin Slokker
 * @version 0.2
 * @param <B> the builder type.
 */
public class SimpleCameraBuilder<B extends SimpleCameraBuilder> extends BaseCameraBuilder<B>
{   
    public static SimpleCameraBuilder getBuilder()
    {
        return new SimpleCameraBuilder<>();
    }
    protected SimpleCameraBuilder()
    {
    }
    
    @Deprecated
    public void test()
    {
        
    }
}
