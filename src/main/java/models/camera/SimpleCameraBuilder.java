
package main.java.models.camera;

import main.java.models.camera.builderinterfaces.SceneSetter;
import main.java.models.camera.builderinterfaces.SimpleCameraBuildInterface;
import main.java.models.camera.builderinterfaces.SimpleLinkInterface;

/**
 * Is able to build a {@see main.java.models.camera.SimpleCamera}.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class SimpleCameraBuilder extends BaseCameraBuilder<SimpleCamera, SimpleLinkInterface>
        implements SimpleCameraBuildInterface, SimpleLinkInterface
{   
    @Override
    public SimpleCamera build()
    {
        return new SimpleCamera(this);
    }
    protected SimpleCameraBuilder()
    {
    }
    /**
     * This method starts the building chain. Followed by {@link setScene}.
     *
     * @param camera a new 
     * @return
     */
    public static SceneSetter startBuild(SimpleCamera camera)
    {
        BaseCameraBuilder<SimpleCamera,SimpleLinkInterface> builder = new SimpleCameraBuilder();
        return builder;
    }

    @Override
    public SimpleCameraBuildInterface linkToSimple()
    {
        return this;
    }
}
