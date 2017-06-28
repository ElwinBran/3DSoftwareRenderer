
package main.java.models.viewvolumeculling;

import java.util.List;
import main.java.models.threedee.collision.BoundingVolumeInterface;
import main.java.models.threedee.objects.RenderableObject;

/**
 * Represents a culler that actually does not cull.
 * 
 * @author Elwin Slokker
 * @version 0.3
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
    public List<RenderableObject> meshesInsideViewFrustum(BoundingVolumeInterface volume, List<RenderableObject> meshes)
    {
        return meshes;
    }
    
}
