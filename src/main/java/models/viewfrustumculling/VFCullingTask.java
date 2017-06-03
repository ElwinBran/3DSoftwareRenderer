
package main.java.models.viewfrustumculling;

import java.util.concurrent.Callable;
import main.java.models.BoundingFrustum;
import main.java.models.Mesh;

/**
 *
 * @author Elwin Slokker
 * @version 0.1
 */
public class VFCullingTask implements Callable<Mesh>
{
    private final BoundingFrustum viewFrustum;
    private final Mesh mesh;
    
    public VFCullingTask(BoundingFrustum viewFrustum, Mesh mesh)
    {
        this.viewFrustum = viewFrustum;
        this.mesh = mesh;
    }
    @Override
    public Mesh call() throws Exception
    {
        return null;
    }
    
}
