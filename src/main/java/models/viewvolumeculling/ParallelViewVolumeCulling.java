
package main.java.models.viewvolumeculling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import main.java.models.collision.BoundingVolume;
import main.java.models.newmodels.RenderableObject;
import main.java.util.ThreadProvider;

/**
 * A culler that solves the cull problem in parallel using multiple threads and tasks.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public class ParallelViewVolumeCulling implements ViewVolumeCullInterface
{
    /**
     * The culler needs multiple threads.
     */
    private ThreadProvider threadProvider = new ThreadProvider();
    public void closeThreads()
    {
        threadProvider.getTreadPool().shutdown();
    }
    /**
     * Checks if the thread has not been shut down. If it is shut down, a new
     * pool will be created and replaces the old one.
     */
    private void refreshThreads()
    {
        threadProvider.refreshThreads();
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
        CompletionService<Boolean> pool = new ExecutorCompletionService<>(threadProvider.getTreadPool());
        List<RenderableObject> visibleMeshes = Collections.synchronizedList(new ArrayList<>());
        for(RenderableObject object: objects)
        {
            pool.submit(new VVCullingTask(volume, object, visibleMeshes));
        }
        for(RenderableObject mesh: objects)
        {
            try
            {
                pool.take().get();//returns true on success, might want to use that.
            } catch (InterruptedException | ExecutionException ex)
            {
                //Should not throw any exception actually.
                //Logger.getLogger(ParallelViewVolumeCulling.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
        return visibleMeshes;
    }
}
