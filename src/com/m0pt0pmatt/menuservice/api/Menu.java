package com.m0pt0pmatt.menuservice.api;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * A MenuComponent is a MenuComponent which implements a Menu.
 * 
 * A MenuComponent acts like a DefaultComponent with added features.
 * 
 * @author mbroomfield
 *
 */
public final class Menu{

	private List<MenuPart> parts;
	private List<String> players;
	private Set<String> commands;
	private String title;
	private int size;

	public Menu(){
		parts = new LinkedList<MenuPart>();
		players = new LinkedList<String>();
		commands = new HashSet<String>();
		size = 1;
	}
	
	public int getSize(){
		return size;
	}
	
	public void setSize(int size){
		this.size = size;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	//--------------------------Methods for all menuparts--------------------------

	public List<MenuPart> getParts() {
		return parts;
	}


	public void setParts(List<MenuPart> parts) {
		this.parts = parts;
	}

	//--------------------------Methods for one menupart--------------------------
	
	public MenuPart getPart(int index) {
		return parts.get(index);
	}
	
	
	public boolean hasPart(MenuPart part) {
		return parts.contains(part);
	}
	
	
	public void addPart(MenuPart part) {
		parts.add(part);
	}

	
	public void removePart(MenuPart part) {
		parts.remove(part);
	}
	
	/**
	 * Catch when a player executes a command if a Menu should be opened
	 * @param event
	 */
	@EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();
        
        if (commands.contains(command)){
            event.setCancelled(true);
            player.sendMessage("currently, commands are broken with menus");
            player.sendMessage("this code should be moved to easymenus");
            //this.openMenu(player.getName());
        }
    }

	public void removePlayer(String playerName) {
		players.remove(playerName);
	}
	
}
