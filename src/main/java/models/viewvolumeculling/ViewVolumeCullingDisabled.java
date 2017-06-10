
package main.java.models.viewvolumeculling;

import java.util.List;
import main.java.models.Mesh;
import main.java.models.collision.BoundingVolume;

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
     * @since 0.1
     */
    @Override
    public List<Mesh> meshesInsideViewFrustum(BoundingVolume volume, List<Mesh> meshes)
    {
        return meshes;
    }
    
}
