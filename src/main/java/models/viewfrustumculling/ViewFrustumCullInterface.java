
package main.java.models.viewfrustumculling;

import java.util.List;
import main.java.models.BoundingFrustum;
import main.java.models.Mesh;

/**
 *
 * @author Elwin Slokker
 */
public interface ViewFrustumCullInterface
{
    public List<Mesh> meshesInsideViewFrustum(BoundingFrustum viewFrustum, List<Mesh> meshes);
}
