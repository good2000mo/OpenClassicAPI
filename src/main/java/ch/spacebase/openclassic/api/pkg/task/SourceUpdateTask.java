package ch.spacebase.openclassic.api.pkg.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import ch.spacebase.openclassic.api.Color;
import ch.spacebase.openclassic.api.OpenClassic;
import ch.spacebase.openclassic.api.command.Sender;
import ch.spacebase.openclassic.api.config.Configuration;
import ch.spacebase.openclassic.api.config.ConfigurationNode;

/**
 * A task that updates a source.
 */
public class SourceUpdateTask implements Runnable {

	private Sender executor;
	
	public SourceUpdateTask(Sender executor) {
		this.executor = executor;
	}
	
	@Override
	public void run() {	
		Configuration sources = OpenClassic.getGame().getPackageManager().getSourcesList();
		if(this.executor != null) this.executor.sendMessage(Color.AQUA + "Updating sources...");
		
		File file = null;
		String id = "";
		String url = "";
		
		for(ConfigurationNode node : sources.getNodes()) {
			id = node.getPath();
			url = node.getString();
			
			file = new File(OpenClassic.getGame().getDirectory(), "source-cache/" + id + "-packages.yml");
			
			if(file.exists()) {
				file.delete();
			}
			
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			
			try {
				file.createNewFile();
			} catch (IOException e) {
				if(this.executor != null) this.executor.sendMessage(Color.RED + "Failed to create source cache file!");
				OpenClassic.getLogger().severe("Failed to create file \"" + file.getName() + "\" when attempting to add a source!");
				e.printStackTrace();
				return;
			}
			
			ReadableByteChannel rbc = null;
			FileOutputStream fos = null;
			
			try {
			    URL u = new URL(url + "/packages.yml");
			    rbc = Channels.newChannel(u.openStream());
			    fos = new FileOutputStream(file);
			    
			    int length = 0;
			    
			    try {
			        HttpURLConnection content = (HttpURLConnection) u.openConnection();
			        length = content.getContentLength();
			    } catch (Exception e) {
			    	if(this.executor != null) this.executor.sendMessage(Color.RED + "Failed to update source " + id + ".");
					OpenClassic.getLogger().severe("Failed to determine file length!");
					e.printStackTrace();
					return;
			    }
			    
			    fos.getChannel().transferFrom(rbc, 0, length);
			} catch(Exception e) {
				if(this.executor != null) this.executor.sendMessage(Color.RED + "Failed to update source " + id + ".");
				OpenClassic.getLogger().severe("Failed to download file from \"" + url + "/packages.yml\"!");
				e.printStackTrace();
				return;
			} finally {
				try {
					if(rbc != null) rbc.close();
					if(fos != null) fos.close();
				} catch(IOException e) {
					OpenClassic.getLogger().warning("Failed to close stream after downloading file!");
					e.printStackTrace();
				}
			}
		}
		
		if(this.executor != null) this.executor.sendMessage(Color.GREEN + "All sources have been updated.");
	}

}
