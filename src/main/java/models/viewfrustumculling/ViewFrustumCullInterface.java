
package main.java.models.viewfrustumculling;

import java.util.List;
import main.java.models.collision.BoundingFrustum;
import main.java.models.Mesh;

/**
 * Defines basic behaviour of a view frustum culler (view frustum culling is the
 * step before the actual rendering algorithms start).
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public interface ViewFrustumCullInterface
{
    /**
     * Checks whether {@code meshes} are inside the {@code viewFrustum}.
     * 
     * @param viewFrustum
     * @param meshes
     * @return every mesh that is inside {@code viewFrustum}.
     */
    public List<Mesh> meshesInsideViewFrustum(BoundingFrustum viewFrustum, List<Mesh> meshes);
}
