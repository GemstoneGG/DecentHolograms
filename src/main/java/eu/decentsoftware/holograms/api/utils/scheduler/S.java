package eu.decentsoftware.holograms.api.utils.scheduler;

import eu.decentsoftware.holograms.api.DecentHolograms;
import eu.decentsoftware.holograms.api.DecentHologramsAPI;
import eu.decentsoftware.holograms.api.utils.DExecutor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.scheduler.BukkitTask;

public class S {

    private static final DecentHolograms DECENT_HOLOGRAMS = DecentHologramsAPI.get();

    public static void stopTask(int id) {
        Bukkit.getScheduler().cancelTask(id);
    }

    public static void sync(Runnable runnable) {
        Bukkit.getScheduler().runTask(DECENT_HOLOGRAMS.getPlugin(), runnable);
    }

    public static void sync(org.bukkit.entity.Player player, Runnable runnable) {
        player.getScheduler().run(DECENT_HOLOGRAMS.getPlugin(), $ -> runnable.run(), null);
    }

    public static BukkitTask sync(Runnable runnable, long delay) {
        return Bukkit.getScheduler().runTaskLater(DECENT_HOLOGRAMS.getPlugin(), runnable, delay);
    }

    public static void sync(org.bukkit.entity.Player player, Runnable runnable, long delay) {
        player.getScheduler().runDelayed(DECENT_HOLOGRAMS.getPlugin(), $ -> runnable.run(), null, delay);
    }

    public static BukkitTask syncTask(Runnable runnable, long interval) {
        return Bukkit.getScheduler().runTaskTimer(DECENT_HOLOGRAMS.getPlugin(), runnable, 0, interval);
    }

    public static void async(Runnable runnable) {
        try {
            Bukkit.getAsyncScheduler().runNow(DECENT_HOLOGRAMS.getPlugin(), $ -> runnable.run());
        } catch (IllegalPluginAccessException e) {
            DExecutor.execute(runnable);
        }
    }

    public static void async(Runnable runnable, long delay) {
        try {
            Bukkit.getAsyncScheduler().runDelayed(DECENT_HOLOGRAMS.getPlugin(), $ -> runnable.run(), delay * 50, java.util.concurrent.TimeUnit.MILLISECONDS);
        } catch (IllegalPluginAccessException e) {
            DExecutor.execute(runnable);
        }
    }

    public static BukkitTask asyncTask(Runnable runnable, long interval) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(DECENT_HOLOGRAMS.getPlugin(), runnable, 0, interval);
    }

    public static Runnable asyncTask(Runnable runnable, long interval, long delay) {
        return Bukkit.getAsyncScheduler().runAtFixedRate(DECENT_HOLOGRAMS.getPlugin(), $ -> runnable.run(), delay * 50, interval * 50, java.util.concurrent.TimeUnit.MILLISECONDS)::cancel;
    }

}
