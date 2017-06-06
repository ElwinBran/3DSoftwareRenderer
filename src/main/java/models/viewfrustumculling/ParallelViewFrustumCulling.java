
package main.java.models.viewfrustumculling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.models.collision.BoundingFrustum;
import main.java.models.Mesh;

/**
 *
 * @author Elwin Slokker
 */
public class ParallelViewFrustumCulling implements ViewFrustumCullInterface
{
    private ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    /**
     * Closes the threads the solver uses.
     *
     * Should be called when the solver is not needed for a long period of time.
     * Or when it is not going to be used anymore.
     */
    public void closeThreads()
    {
        threadPool.shutdown();
    }
    /**
     * Checks if the thread has not been shut down. If it is shut down, a new
     * pool will be created and replaces the old one.
     */
    private void refreshThreads()
    {
        if (threadPool.isShutdown())
        {
            threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        }
    }
    /**
     * Makes a list with all meshes that intersect the provided {@code viewFrustum}.
     * @param viewFrustum
     * @param meshes
     * @return a list with only the meshes that have intersecting bounding shapes
     * with the {@code viewFrustum}. The list order is not preserved (unstable).
     */
    @Override
    public List<Mesh> meshesInsideViewFrustum(BoundingFrustum viewFrustum, List<Mesh> meshes)
    {
        refreshThreads();
        CompletionService<Mesh> pool = new ExecutorCompletionService<>(threadPool);
        List<Mesh> visibleMeshes = new ArrayList<>();
        for(Mesh mesh: meshes)
        {
            pool.submit(new VFCullingTask(viewFrustum, mesh));
        }
        for(Mesh mesh: meshes)
        {
            try
            {
                Mesh meshRef = pool.take().get();
                if(meshRef != null)
                {
                    visibleMeshes.add(meshRef);
                }
            } catch (InterruptedException | ExecutionException ex)
            {
                //Should not throw any exception actually.
                Logger.getLogger(ParallelViewFrustumCulling.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return visibleMeshes;
    }
    
}
