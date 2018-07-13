package steven.vChat;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EventsClass implements Listener {
	
	//get config
	private Main plugin;
		
	public EventsClass(Main pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void chatEvent(AsyncPlayerChatEvent event) {
		if (plugin.getConfig().getBoolean("chatformat")) {
			Player player = event.getPlayer();
			if (player.hasPermission("wanderer.v")) {
				event.setFormat(supergroupTag(player) + ChatColor.GRAY + getGroup(player) + " " + event.getPlayer().getDisplayName() + ": " + ChatColor.WHITE + unicize(event.getMessage()));
			}
			else if (player.hasPermission("citizen.v")) {
				event.setFormat(supergroupTag(player) + ChatColor.WHITE + getGroup(player) + " " + event.getPlayer().getDisplayName() + ": " + ChatColor.WHITE + unicize(event.getMessage()));
			}
			else if (player.hasPermission("noble.v")) {
				event.setFormat(supergroupTag(player) + ChatColor.YELLOW + getGroup(player) + " " + event.getPlayer().getDisplayName() + ": " + ChatColor.WHITE + unicize(event.getMessage()));
			}
			else if (player.hasPermission("merchant.v")) {
				event.setFormat(supergroupTag(player) + ChatColor.GOLD + getGroup(player) + " " + event.getPlayer().getDisplayName() + ": " + ChatColor.WHITE + unicize(event.getMessage()));
			}
			else if (player.hasPermission("knight.v")) {
				event.setFormat(supergroupTag(player) + ChatColor.DARK_GREEN + getGroup(player) + " " + event.getPlayer().getDisplayName() + ": " + ChatColor.WHITE + unicize(event.getMessage()));
			}
			else if (player.hasPermission("baron.v")) {
				event.setFormat(supergroupTag(player) + ChatColor.GREEN + getGroup(player) + " " + event.getPlayer().getDisplayName() + ": " + ChatColor.WHITE + unicize(event.getMessage()));
			}
			else if (player.hasPermission("duke.v")) {
				event.setFormat(supergroupTag(player) + ChatColor.DARK_PURPLE + getGroup(player) + " " + event.getPlayer().getDisplayName() + ": " + ChatColor.WHITE + unicize(event.getMessage()));
			}
			else if (player.hasPermission("chancellor.v")) {
				event.setFormat(supergroupTag(player) + ChatColor.DARK_AQUA + getGroup(player) + " " + event.getPlayer().getDisplayName() + ": " + ChatColor.WHITE + unicize(event.getMessage()));
			}
			else if (player.hasPermission("viceroy.v")) {
				event.setFormat(supergroupTag(player) + ChatColor.AQUA + getGroup(player) + " " + event.getPlayer().getDisplayName() + ": " + ChatColor.WHITE + unicize(event.getMessage()));
			}
			else if (player.hasPermission("guardian.v")) {
				event.setFormat(supergroupTag(player) + ChatColor.BLUE + getGroup(player) + " " + event.getPlayer().getDisplayName() + ": " + ChatColor.WHITE + unicize(event.getMessage()));
			}
			else if (player.hasPermission("avatar.v")) {
				event.setFormat(supergroupTag(player) + ChatColor.DARK_RED + getGroup(player) + " " + event.getPlayer().getDisplayName() + ": " + ChatColor.WHITE + unicize(event.getMessage()));
			}
		}
		else
			return;		
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
	
	String supergroupTag(Player player) {
		String tag = "";
		if (player.hasPermission("owner.v")) {
			tag = (ChatColor.DARK_GRAY + "[" + ChatColor.RED + unicize(":yinyang:") + ChatColor.DARK_GRAY + "] ");
			return tag;
		}
		else if (player.hasPermission("admin.v")) {
			tag = (ChatColor.DARK_GRAY + "[" + ChatColor.RED + unicize(":292c:") + ChatColor.DARK_GRAY + "] ");
			return tag;
		}
		else if (player.hasPermission("mod.v")) {
			tag = (ChatColor.DARK_GRAY + "[" + ChatColor.DARK_PURPLE + unicize(":262c:") + ChatColor.DARK_GRAY + "] ");
			return tag;
		}
		else if (player.hasPermission("trusted.v")) {
			tag = (ChatColor.DARK_GRAY + "[" + ChatColor.LIGHT_PURPLE + unicize(":269c:") + ChatColor.DARK_GRAY + "] ");
			return tag;
		}
		else if (player.hasPermission("sapphire.v")) {
			tag = (ChatColor.DARK_GRAY + "[" + ChatColor.DARK_BLUE + unicize(":06e9:") + ChatColor.DARK_GRAY + "] ");
			return tag;
		}
		else if (player.hasPermission("ruby.v")) {
			tag = (ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + unicize(":0f06:") + ChatColor.DARK_GRAY + "] ");
			return tag;
		}
		else if (player.hasPermission("drangonstone.v")) {
			tag = (ChatColor.DARK_GRAY + "[" + ChatColor.DARK_PURPLE + unicize(":0f3a:") + ChatColor.DARK_GRAY + "] ");
			return tag;
		}
		else if (player.hasPermission("veteran.v")) {
			tag = (ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + unicize(":272f:") + ChatColor.DARK_GRAY + "] ");
			return tag;
		}
		else
			return tag;
	}
	
	static String getGroup(Player player) {
		if (player.hasPermission("wanderer.v")) {
			return "Wanderer";
		}
		else if (player.hasPermission("citizen.v")) {
			return "Citizen";
		}
		else if (player.hasPermission("noble.v")) {
			return "Noble";
		}
		else if (player.hasPermission("merchant.v")) {
			return "Merchant";
		}
		else if (player.hasPermission("knight.v")) {
			return "Knight";
		}
		else if (player.hasPermission("baron.v")) {
			return "Baron";
		}
		else if (player.hasPermission("duke.v")) {
			return "Duke";
		}
		else if (player.hasPermission("chancellor.v")) {
			return "Chancellor";
		}
		else if (player.hasPermission("viceroy.v")) {
			return "Viceroy";
		}
		else if (player.hasPermission("guardian.v")) {
			return "Guardian";
		}
		else if (player.hasPermission("avatar.v")) {
			return "Avatar";
		}
		else
			return "Unknown";
	}
}
