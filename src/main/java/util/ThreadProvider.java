
package main.java.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Provides an {@code ExecutorService} that has threads equal to the (virtual) processors of the systems CPU.
 * 
 * @author Elwin Slokker
 * @version 0.3
 */
public class ThreadProvider
{
    /**
     * Keeps the threads.
     */
    private ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    /**
     * @return the threads.
     */
    public ExecutorService getTreadPool()
    {
        return this.threadPool;
    }
    /**
     * Closes the threads.
     *
     * Should be called when the threads are not needed for a long period of time.
     * Or when it is not going to be used anymore.
     */
    public void closeThreads()
    {
        threadPool.shutdown();
    }
    /**
     * Makes new threads when the old ones are shut down.
     */
    public void refreshThreads()
    {
        if (threadPool.isShutdown())
        {
            threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        }
    }
}
