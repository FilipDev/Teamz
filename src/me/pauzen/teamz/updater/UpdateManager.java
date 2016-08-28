/*
 *  Created by Filip P. on 8/27/16 6:14 PM.
 */

/*
 *  Created by Filip P. on 7/12/16 10:41 PM.
 */

package me.pauzen.teamz.updater;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class UpdateManager implements Listener {

    private static UpdateManager instance;

    public UpdateManager() {
        instance = this;
    }

    public static UpdateManager getInstance() {
        return instance;
    }

    public void register(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (UpdateType updateType : UpdateType.values())
                    if (updateType.elapsed())
                        Bukkit.getPluginManager().callEvent(new UpdateEvent(updateType));
            }
        }, 1L, 1L);
    }
}