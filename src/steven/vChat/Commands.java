package steven.vChat;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.minecraft.server.v1_12_R1.CommandExecute;

public class Commands extends CommandExecute implements Listener, CommandExecutor {
	
	//get config
	private Main plugin;
	
	public Commands(Main pl) {
		plugin = pl;
	}
	
	//list of all commands
	public String cmd1 = "chatsymbol";
	public String cmd2 = "HUD";
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			if (cmd.getName().equalsIgnoreCase(cmd1)) {
				if (plugin.getConfig().getBoolean("chatsymbols")) {
					Player player = (Player)sender;
					if (player.hasPermission("chatsymbol.v")) {
						player.sendMessage(ChatColor.GREEN + "[Avaliable Unicodes]");
				    	player.sendMessage(unicodeList());
				    	return true;
					}
					else
						player.sendMessage(ChatColor.RED + plugin.getConfig().getString("no_perms_cmd"));
					return true;
				}
				else {
					sender.sendMessage(ChatColor.RED + "Chat Symbols is not enabled.");
					return true;
				}
			}
			else if (cmd.getName().equalsIgnoreCase(cmd1)) {
				if (plugin.getConfig().getBoolean("Sidebar")) {
					Main.updaterME((Player) sender);
					return true;
				}
				else {
					sender.sendMessage(ChatColor.RED + "Hud is not enabled.");
				return true;
				}
			}
			else {
				sender.sendMessage(ChatColor.RED + "HUD is disabled");
				return true;
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "Only players can use this command.");
			return true;
		}		
	}
	
	String unicodeList() {
    	StringBuilder sb = new StringBuilder();
    	for (String key : unicodes.keySet()) { 
    		sb.append(unicize(key)); 
    		sb.append(key); 
    		sb.append(" ");
    	}
    	return sb.toString();
	}
	
	private final HashMap<String,Integer> unicodes = new HashMap<String,Integer>();
	{
		unicodes.put(":airplane:", 0x2708); unicodes.put(":asterism:", 0x2042); unicodes.put(":notes:", 0x266b);
		unicodes.put(":biohazard:", 0x2623); unicodes.put(":cloud:", 0x2601); unicodes.put(":coffee:", 0x2615);
		unicodes.put(":comet:", 0x2604); unicodes.put(":flower:", 0x2698); unicodes.put(":frowny:", 0x2639);
		unicodes.put(":gear:", 0x2699); unicodes.put(":russia:", 0x262d); unicodes.put(":heart:", 0x2764);
		unicodes.put(":peace:", 0x262e); unicodes.put(":face:", 0x3020); unicodes.put(":note:", 0x266a);
		unicodes.put(":radioactive:", 0x2622); unicodes.put(":skull:", 0x2620); unicodes.put(":smiley:", 0x263a);
		unicodes.put(":snowflake:", 0x2744); unicodes.put(":snowman:", 0x2603); unicodes.put(":squiggly:", 0x2368);
		unicodes.put(":star:", 0x2605); unicodes.put(":sun:", 0x2600); unicodes.put(":umbrella:", 0x2602);
		unicodes.put(":lightning:", 0x26a1); unicodes.put(":yinyang:", 0x262f); unicodes.put(":spades:", 0x2660);
		unicodes.put(":clubs:", 0x2663); unicodes.put(":hearts:", 0x2665); unicodes.put(":diamonds:", 0x2666);
		unicodes.put(":smiley2:", 0x263b); unicodes.put(":wking:", 0x2654); unicodes.put(":wqueen:", 0x2655);
		unicodes.put(":wrook:", 0x2656); unicodes.put(":wbishop:", 0x2657); unicodes.put(":wknight:", 0x2658);
		unicodes.put(":wpawn:", 0x2659); unicodes.put(":bking:", 0x2660); unicodes.put(":bqueen:", 0x2661);
		unicodes.put(":brook:", 0x2662); unicodes.put(":bbishop:", 0x2663); unicodes.put(":bknight:", 0x2664);
		unicodes.put(":bpawn:", 0x2665);
	}
	
	String unicize(String string) {
		String s = string;
		StringBuffer buf; Pattern pat; Matcher mat;
		buf = new StringBuffer(); pat = Pattern.compile(":[0-9a-f]{4}:"); mat = pat.matcher(s);
		while (mat.find()) mat.appendReplacement(buf, Character.toString((char) Integer.parseInt(mat.group().substring(1, 5), 16)));
		mat.appendTail(buf); s = buf.toString();
		for (String key : unicodes.keySet()) s = s.replaceAll(key, Character.toString((char) unicodes.get(key).intValue()));
		return s;
	}
		
	public static String timeU(String world) {
		long time = Bukkit.getServer().getWorld(world).getFullTime();
		int hours = (int)((time/1000+8)%24);
		int minutes = (int)(60*(time%1000)/1000);
		return String.format("%02d:%02d", hours, minutes);
	}

}

