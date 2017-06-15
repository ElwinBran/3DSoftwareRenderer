
package main.java.models.viewvolumeculling;

import java.util.List;
import main.java.models.collision.BoundingVolume;
import main.java.models.newmodels.RenderableObject;

/**
 * Represents a culler that actually does not cull.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public class ViewVolumeCullingDisabled implements ViewVolumeCullInterface
{
    /**
     * Culls nothing.
     * 
     * @param volume does nothing, since it does not cull.
     * @param meshes the list of objects.
     * @return {@code meshes}.
     */
    @Override
    public List<RenderableObject> meshesInsideViewFrustum(BoundingVolume volume, List<RenderableObject> meshes)
    {
        return meshes;
    }
    
}
