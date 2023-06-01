package eu.decentsoftware.holograms.api.utils.scheduler;

import eu.decentsoftware.holograms.api.DecentHolograms;
import eu.decentsoftware.holograms.api.DecentHologramsAPI;
import eu.decentsoftware.holograms.api.utils.DExecutor;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;

public class S {

    private static final DecentHolograms DECENT_HOLOGRAMS = DecentHologramsAPI.get();

    public static void stopTask(int id) {
        //FIXME need some changes, GRS doesn't have id's
        Bukkit.getGlobalRegionScheduler().cancelTasks(DECENT_HOLOGRAMS.getPlugin());
    }

    public static void sync(Consumer<ScheduledTask> runnable) {
        Bukkit.getGlobalRegionScheduler().run(DECENT_HOLOGRAMS.getPlugin(), runnable);

    }

    public static ScheduledTask sync(Location location, Consumer<ScheduledTask> runnable, long delay) {

        return Bukkit.getRegionScheduler().runDelayed(DECENT_HOLOGRAMS.getPlugin(),location,runnable,delay);
    }

    //  public static BukkitTask syncTask(Runnable runnable, long interval) {
    //      return Bukkit.getScheduler().runTaskTimer(DECENT_HOLOGRAMS.getPlugin(), runnable, 0, interval);
    //  }

    public static void async(Consumer<ScheduledTask> runnable) {
        //There is not async schedulers in folia
        sync(runnable);
    }

    public static void async(Location location,Consumer<ScheduledTask>  runnable, long delay) {
        //There is not async schedulers in folia
        sync(location,runnable,delay);

    }

    //  public static BukkitTask asyncTask(Runnable runnable, long interval) {
    //      return Bukkit.getScheduler().runTaskTimerAsynchronously(DECENT_HOLOGRAMS.getPlugin(), runnable, 0, interval);
    //  }

    public static ScheduledTask asyncTask(Consumer<ScheduledTask> runnable, long interval, long delay) {
        return Bukkit.getGlobalRegionScheduler().runAtFixedRate(DECENT_HOLOGRAMS.getPlugin(),runnable,delay,interval);
    }

}
