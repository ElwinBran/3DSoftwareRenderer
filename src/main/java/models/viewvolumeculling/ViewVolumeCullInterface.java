
package main.java.models.viewvolumeculling;

import java.util.List;
import main.java.models.threedee.collision.BoundingVolumeInterface;
import main.java.models.threedee.objects.RenderableObject;

/**
 * Defines basic behaviour of a view frustum culler (view frustum culling is the
 * step before the actual rendering algorithms start).
 * 
 * @author Elwin Slokker
 * @version 0.4
 */
public interface ViewVolumeCullInterface
{
    /**
     * Checks whether {@code meshes} are inside the {@code viewFrustum}.
     * 
     * @param volume the 'hitbox' that the camera defines as its 'sight'.
     * @param objects all the objects that are to be (possibly) culled.
     * @return every mesh that is inside {@code viewFrustum}.
     */
    public List<RenderableObject> meshesInsideViewFrustum(BoundingVolumeInterface volume, List<RenderableObject> objects);
}
