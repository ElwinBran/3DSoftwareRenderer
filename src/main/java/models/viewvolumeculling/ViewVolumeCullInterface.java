
package main.java.models.viewvolumeculling;

import java.util.List;
import main.java.models.Mesh;
import main.java.models.collision.BoundingVolume;

/**
 * Defines basic behaviour of a view frustum culler (view frustum culling is the
 * step before the actual rendering algorithms start).
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public interface ViewVolumeCullInterface
{
    /**
     * Checks whether {@code meshes} are inside the {@code viewFrustum}.
     * 
     * @param volume
     * @param meshes
     * @return every mesh that is inside {@code viewFrustum}.
     * @since 0.1
     */
    public List<Mesh> meshesInsideViewFrustum(BoundingVolume volume, List<Mesh> meshes);
}
