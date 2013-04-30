package ch.spacebase.openclassic.api.pkg.task;

import java.io.File;

import ch.spacebase.openclassic.api.Color;
import ch.spacebase.openclassic.api.OpenClassic;
import ch.spacebase.openclassic.api.command.Sender;
import ch.spacebase.openclassic.api.config.Configuration;
import ch.spacebase.openclassic.api.plugin.Plugin;

/**
 * A task that removes a package.
 */
public class PackageRemoveTask implements Runnable {

	private String name;
	private Sender executor;
	
	public PackageRemoveTask(String name, Sender executor) {
		this.name = name;
		this.executor = executor;
	}
	
	@Override
	public void run() {
		Configuration pkgs = OpenClassic.getGame().getPackageManager().getInstalled();
		
		if(pkgs.getNode(this.name) == null) {
			if(this.executor != null) this.executor.sendMessage(Color.RED + "This package is not installed!");
			return;
		}
		
		if(this.executor != null) this.executor.sendMessage(Color.AQUA + "Disabling related plugins...");
		
		String plugins = pkgs.getString(this.name + ".plugins");
		if(plugins != null && !plugins.equals("")) {
			for(String plugin : plugins.split(",")) {
				if(OpenClassic.getGame().getPluginManager().isPluginEnabled(plugin)) {
					Plugin p = OpenClassic.getGame().getPluginManager().getPlugin(plugin);
					OpenClassic.getGame().getPluginManager().disablePlugin(p);
					OpenClassic.getGame().getPluginManager().removePlugin(p);
				}
			}
		}
		
		if(this.executor != null) this.executor.sendMessage(Color.AQUA + "Removing files...");
		
		for(String file : pkgs.getStringList(this.name + ".files")) {
			(new File(OpenClassic.getGame().getDirectory(), file)).delete();
		}
		
		for(String dir : pkgs.getStringList(this.name + ".dirs")) {
			(new File(OpenClassic.getGame().getDirectory(), dir)).delete();
		}
		
		pkgs.remove(this.name);
		pkgs.save();
		
		if(this.executor != null) this.executor.sendMessage(Color.GREEN + "The package \"" + this.name + "\" has been removed successfully!");
	}
	
}
