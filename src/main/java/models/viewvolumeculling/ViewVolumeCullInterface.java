
package main.java.models.viewvolumeculling;

import java.util.List;
import main.java.models.collision.BoundingVolume;
import main.java.models.newmodels.RenderableObject;

/**
 * Defines basic behaviour of a view frustum culler (view frustum culling is the
 * step before the actual rendering algorithms start).
 * 
 * @author Elwin Slokker
 * @version 0.3
 */
public interface ViewVolumeCullInterface
{
    /**
     * Checks whether {@code meshes} are inside the {@code viewFrustum}.
     * 
     * @param volume
     * @param meshes
     * @return every mesh that is inside {@code viewFrustum}.
     */
    public List<RenderableObject> meshesInsideViewFrustum(BoundingVolume volume, List<RenderableObject> meshes);
}
