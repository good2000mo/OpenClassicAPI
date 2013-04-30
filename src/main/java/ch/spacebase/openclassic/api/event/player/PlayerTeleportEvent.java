package ch.spacebase.openclassic.api.event.player;

import ch.spacebase.openclassic.api.Position;
import ch.spacebase.openclassic.api.player.Player;

/**
 * Called when a player teleports.
 */
public class PlayerTeleportEvent extends PlayerMoveEvent {
    
    public PlayerTeleportEvent(Player player, Position from, Position to) {
    	super(EventType.PLAYER_TELEPORT, player, from, to);
    }
	
}
