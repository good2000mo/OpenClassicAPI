package ch.spacebase.openclassic.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import ch.spacebase.openclassic.api.util.Constants;

/**
 * Manages the server's web heartbeats.
 */
public final class HeartbeatManager {

	private static final long salt = new SecureRandom().nextLong();
	private static final Map<String, Runnable> customBeats = new HashMap<String, Runnable>();
	private static String url = "";
	
	/**
	 * Gets the server's current salt.
	 * @return The server's salt.
	 */
	public static long getSalt() {
		return salt;
	}
	
	/**
	 * Gets the server's minecraft.net url.
	 * @return The url.
	 */
	public static String getURL() {
		return url;
	}
	
	/**
	 * Sets the server's known minecraft.net url.
	 * @param url The url.
	 */
	public static void setURL(String url) {
		HeartbeatManager.url = url;
	}
	
	/**
	 * Triggers a heartbeat.
	 */
	public static void beat() {
		mineBeat();
		womBeat();
		
		for(String id : customBeats.keySet()) {
			try {
				customBeats.get(id).run();
			} catch(Exception e) {
				OpenClassic.getLogger().severe("Exception while running a custom heartbeat with the ID \"" + id + "\"!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Adds a custom heartbeat to run when {@link beat()} is called.
	 * @param id ID of the custom heartbeat.
	 * @param run Runnable to call when beating.
	 */
	public static void addBeat(String id, Runnable run) {
		customBeats.put(id, run);
	}
	
	/**
	 * Removes a custom heartbeat.
	 * @param id ID of the heartbeat.
	 */
	public static void removeBeat(String id) {
		customBeats.remove(id);
	}
	
	/**
	 * Clears the custom heartbeat list.
	 */
	public static void clearBeats() {
		customBeats.clear();
	}

	private static void mineBeat() {
		URL url = null;
		
		try {
			url = new URL("https://minecraft.net/heartbeat.jsp?port=" + OpenClassic.getServer().getPort() + "&max=" + OpenClassic.getServer().getMaxPlayers() + "&name=" + URLEncoder.encode(Color.stripColor(OpenClassic.getServer().getServerName()), "UTF-8") + "&public=" + OpenClassic.getServer().isPublic() + "&version=" + Constants.PROTOCOL_VERSION + "&salt=" + salt + "&users=" + OpenClassic.getServer().getPlayers().size());
		} catch(MalformedURLException e) {
			OpenClassic.getLogger().severe("Malformed URL while attempting minecraft.net heartbeat?");
			return;
		} catch(UnsupportedEncodingException e) {
			OpenClassic.getLogger().severe("UTF-8 URL encoding is unsupported on your system.");
			return;
		}
		
		HttpURLConnection conn = null;
		
		try {
			conn = (HttpURLConnection) url.openConnection();
			
			try {
				conn.setRequestMethod("GET");
			} catch (ProtocolException e) {
				OpenClassic.getLogger().severe("Exception while performing minecraft.net heartbeat: Connection doesn't support GET...?");
				return;
			}
			
			conn.setDoOutput(false);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Content-type", "text/xml; charset=" + "UTF-8");
			
			InputStream input = conn.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String result = reader.readLine();
			
			reader.close();
			input.close();
			
			if(!HeartbeatManager.url.equals(result)) {
				HeartbeatManager.url = result;
				OpenClassic.getLogger().info(Color.GREEN + "The server's URL is now \"" + getURL() + "\".");
				
				try {
					File file = new File(OpenClassic.getGame().getDirectory(), "server-address.txt");
					if(!file.exists()) file.createNewFile();
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					writer.write(result);
					writer.close();
				} catch(IOException e) {
					OpenClassic.getLogger().severe("Failed to save server address!");
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			OpenClassic.getLogger().severe("Exception while performing minecraft.net heartbeat!");
			e.printStackTrace();
		} finally {
			if (conn != null) conn.disconnect();
		}
	}
	
	private static void womBeat() {
		URL url = null;
		
		try {
			url = new URL("http://direct.worldofminecraft.com/hb.php?port=" + OpenClassic.getServer().getPort() + "&max=" + OpenClassic.getServer().getMaxPlayers() + "&name=" + URLEncoder.encode(Color.stripColor(OpenClassic.getServer().getServerName()), "UTF-8") + "&public=" + OpenClassic.getServer().isPublic() + "&version=" + Constants.PROTOCOL_VERSION + "&salt=" + salt + "&users=" + OpenClassic.getServer().getPlayers().size() + "&noforward=1");
		} catch(MalformedURLException e) {
			OpenClassic.getLogger().severe("Malformed URL while attempting WOM heartbeat?");
			return;
		} catch(UnsupportedEncodingException e) {
			OpenClassic.getLogger().severe("UTF-8 URL encoding is unsupported on your system.");
			return;
		}
		
		HttpURLConnection conn = null;
		
		try {
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoOutput(false);
			conn.setDoInput(false);
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Content-type", "text/xml; charset=" + "UTF-8");
		} catch (IOException e) {
			OpenClassic.getLogger().severe("Exception while performing WOM heartbeat!");
			e.printStackTrace();
		} finally {
			if (conn != null) conn.disconnect();
		}
	}

}
