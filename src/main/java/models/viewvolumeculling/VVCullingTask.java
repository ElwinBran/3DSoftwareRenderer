
package main.java.models.viewvolumeculling;

import java.util.concurrent.Callable;
import main.java.models.collision.BoundingVolume;
import main.java.models.Mesh;

/**
 *
 * @author Elwin Slokker
 * @version 0.2
 */
public class VVCullingTask implements Callable<Mesh>
{
    private final BoundingVolume volume;
    private final Mesh mesh;
    
    /**
     * 
     * @param volume
     * @param mesh 
     * @since 0.1
     */
    public VVCullingTask(BoundingVolume volume, Mesh mesh)
    {
        this.volume = volume;
        this.mesh = mesh;
    }
    /**
     * 
     * @return
     * @throws Exception 
     * @since 0.1
     */
    @Override
    public Mesh call() throws Exception
    {
        return null;
    }
    
}
