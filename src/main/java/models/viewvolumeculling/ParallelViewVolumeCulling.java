
package main.java.models.viewvolumeculling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.models.collision.BoundingVolume;
import main.java.models.newmodels.RenderableObject;

/**
 * A culler that solves the cull problem in parallel using multiple threads and tasks.
 * 
 * @author Elwin Slokker
 */
public class ParallelViewVolumeCulling implements ViewVolumeCullInterface
{
    /**
     * The culler needs multiple threads.
     */
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
     * 
     * @param volume
     * @param objects
     * @return a list with only the meshes that have intersecting bounding shapes
     * with the {@code viewFrustum}. The list order is not preserved (unstable).
     */
    @Override
    public List<RenderableObject> meshesInsideViewFrustum(BoundingVolume volume, List<RenderableObject> objects)
    {
        refreshThreads();
        CompletionService<RenderableObject> pool = new ExecutorCompletionService<>(threadPool);
        List<RenderableObject> visibleMeshes = new ArrayList<>();
        for(RenderableObject object: objects)
        {
            pool.submit(new VVCullingTask(volume, object));
        }
        for(RenderableObject mesh: objects)
        {
            try
            {
                RenderableObject objectRef = pool.take().get();
                if(objectRef != null)
                {
                    visibleMeshes.add(objectRef);
                }
            } catch (InterruptedException | ExecutionException ex)
            {
                //Should not throw any exception actually.
                Logger.getLogger(ParallelViewVolumeCulling.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return visibleMeshes;
    }
    
}
