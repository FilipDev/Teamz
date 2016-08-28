/*
 *  Created by Filip P. on 8/28/16 6:49 PM.
 */

package me.pauzen.teamz.utils;

import org.bukkit.ChatColor;

public final class StringUtils {

    private StringUtils() {
    }

    public static String addColors(String base, ChatColor defaultColour, ChatColor varColour, Object... vars) {
        base = defaultColour + base;
        for (Object var : vars) {
            base = base.replaceFirst("%s", varColour + var.toString() + defaultColour);
        }
        return base;
    }

}
