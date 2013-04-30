package ch.spacebase.openclassic.api.entity;

import ch.spacebase.openclassic.api.Position;
import ch.spacebase.openclassic.api.block.VanillaBlock;
import ch.spacebase.openclassic.api.event.EventFactory;
import ch.spacebase.openclassic.api.event.entity.EntityBlockRemoveEvent;
import ch.spacebase.openclassic.api.event.entity.EntityBlockSetEvent;
import ch.spacebase.openclassic.api.event.entity.EntityMoveEvent;

/**
 * Represents an entity made of blocks.
 */
public class BlockEntity {

	private static int nextId = 0;
	
	private int entityId;
	private Position pos;
	private Controller controller;
	
	public BlockEntity() {
		this(null);
	}
	
	public BlockEntity(Controller controller) {
		this.entityId = assignId(this);
		this.controller = controller;
		
		if(this.controller != null) {
			if(this.controller.getParent() != null) {
				this.controller.getParent().detach();
			}
			
			this.controller.attach(this);
		}
	}
	
	/**
	 * Attaches a controller to this entity.
	 * @param controller Controller to attach.
	 */
	public void attach(Controller controller) {
		if(this.controller != null) {
			this.detach();
		}
		
		this.controller = controller;
		
		if(this.controller.getParent() != null) {
			this.controller.getParent().detach();
		}
		
		this.controller.attach(this);
	}
	
	/**
	 * Detaches this entity's controller.
	 */
	public void detach() {
		if(this.controller == null) return;
		this.controller.detach();
		this.controller = null;
	}
	
	/**
	 * Gets the entity's ID.
	 * @return The entity's ID.
	 */
	public int getEntityId() {
		return this.entityId;
	}

	/**
	 * Gets the position of the entity.
	 * @return The entity's position.
	 */
	public Position getPosition() {
		return this.pos;
	}
	
	/**
	 * Sets the entity's position.
	 * @param pos Position to set.
	 */
	public void setPosition(Position pos) {
		if(this.controller == null) return;
		if(pos == null) return;
		
		EntityMoveEvent event = EventFactory.callEvent(new EntityMoveEvent(this, this.pos, pos));
		if(event.isCancelled()) 
			return;
		
		if(EventFactory.callEvent(new EntityBlockRemoveEvent(this, BlockRemoveCause.PLAYER, this.pos.getLevel().getBlockAt(this.pos))).isCancelled() || !this.controller.onBlockRemoval(BlockRemoveCause.POSITION_CHANGE, this.pos.getLevel().getBlockAt(this.pos)))
			return;
		
		if(EventFactory.callEvent(new EntityBlockSetEvent(this, pos.getLevel().getBlockAt(pos))).isCancelled() || !this.controller.onBlockSet(pos.getLevel().getBlockAt(pos)))
			return;
		
		this.pos.getLevel().setBlockAt(event.getFrom(), VanillaBlock.AIR);
		pos.getLevel().setBlockAt(event.getTo(), this.controller.getBlock());
		this.pos = event.getTo();
	}
	
	/**
	 * Kills the entity.
	 */
	public void die() {
		this.pos.getLevel().removeBlockEntity(this);
	}
	
	/**
	 * Gets this entity's controller.
	 * @return This entity's controller.
	 */
	public Controller getController() {
		return this.controller;
	}
	
	private static int assignId(BlockEntity entity) {
		return nextId++;
	}
	
	/**
	 * Represents the cause of the entity's block being removed.
	 */
	public enum BlockRemoveCause {
		/** Removed by a player. */
		PLAYER,
		/** Removed because of a position change. */
		POSITION_CHANGE,
		/** Removed because of another unknown or generic reason. */
		OTHER;
	}
	
}
