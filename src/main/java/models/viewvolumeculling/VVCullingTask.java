
package main.java.models.viewvolumeculling;

import java.util.concurrent.Callable;
import main.java.models.collision.BoundingVolume;
import main.java.models.newmodels.RenderableObject;

/**
 *
 * @author Elwin Slokker
 * @version 0.2
 */
public class VVCullingTask implements Callable<RenderableObject>
{
    private final BoundingVolume volume;
    private final RenderableObject object;
    
    /**
     * 
     * @param volume
     * @param object 
     */
    public VVCullingTask(BoundingVolume volume, RenderableObject object)
    {
        this.volume = volume;
        this.object = object;
    }
    /**
     * 
     * @return
     * @throws Exception 
     */
    @Override
    public RenderableObject call() throws Exception
    {
        return null;
    }
    
}
