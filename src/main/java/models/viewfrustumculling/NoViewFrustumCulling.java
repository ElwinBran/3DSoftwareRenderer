
package main.java.models.viewfrustumculling;

import java.util.List;
import main.java.models.BoundingFrustum;
import main.java.models.Mesh;

/**
 * Represents a culler that actually does not cull.
 * 
 * @author Elwin Slokker
 * @version 0.0
 */
public class NoViewFrustumCulling implements ViewFrustumCullInterface
{
    @Override
    public List<Mesh> meshesInsideViewFrustum(BoundingFrustum viewFrustum, List<Mesh> meshes)
    {
        return meshes;
    }
    
}
