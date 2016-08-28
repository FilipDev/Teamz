/*
 *  Created by Filip P. on 8/27/16 6:14 PM.
 */

/*
 *  Created by Filip P. on 7/12/16 10:38 PM.
 */

/*
 *  Created by Filip P. on 2/2/15 11:13 PM.
 */

package me.pauzen.teamz.updater;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UpdateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private UpdateType updateType;

    public UpdateEvent(UpdateType updateType) {
        this.updateType = updateType;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }
}
