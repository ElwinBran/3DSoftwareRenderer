
package main.java.models.viewvolumeculling;

import java.util.List;
import java.util.concurrent.Callable;
import main.java.models.threedee.collision.BoundingVolumeInterface;
import main.java.models.threedee.collision.GJKAlgorithm;
import main.java.models.threedee.objects.RenderableObject;

/**
 * Can be used to Cull objects outside the view volume in parallel.
 * 
 * @author Elwin Slokker
 * @version 0.4
 */
public class VVCullingTask implements Callable<Boolean>
{
    private final BoundingVolumeInterface volume;
    private final RenderableObject object;
    private final List<RenderableObject> visibleObjects;
    
    /**
     * 
     * @param volume
     * @param object 
     */
    public VVCullingTask(BoundingVolumeInterface volume, RenderableObject object, List<RenderableObject> visibleObjects)
    {
        this.volume = volume;
        this.object = object;
        this.visibleObjects = visibleObjects;
    }
    /**
     * 
     * @return
     * @throws Exception 
     */
    @Override
    public Boolean call() throws Exception
    {
        if(GJKAlgorithm.convexShapesIntersect(this.volume, this.object.getBoundingVolume()))
        {
            return visibleObjects.add(object);
        }
        return false;
    }
    
}
