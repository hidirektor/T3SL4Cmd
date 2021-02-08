package me.t3sl4.cmd.commands;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import me.t3sl4.cmd.T3SL4Cmd;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Commands implements Listener {
    static T3SL4Cmd plugin;
    static String DIR = "./plugins/T3SL4Cmd/commands.yml";
    static String icerensatir = null;

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        String[] cmdbas = e.getMessage().split(" ");
        String cmd = cmdbas[0];
        Player p = e.getPlayer();
        File cmdfile = new File(DIR);
        FileConfiguration komut = YamlConfiguration.loadConfiguration(cmdfile);
        ConfigurationSection cs = komut.getConfigurationSection("permissions");
        Iterator var9 = cs.getKeys(false).iterator();

        String altliste;
        List lists;
        List gizlikomutlar;
        do {
            do {
                if (!var9.hasNext()) {
                    return;
                }

                altliste = (String)var9.next();
            } while(altliste.endsWith("_error_message") && altliste.endsWith("_secret_commands"));

            lists = komut.getStringList("permissions." + altliste);
            gizlikomutlar = komut.getStringList("permissions." + altliste + "_secret_commands");
        } while(!p.hasPermission("permissions." + altliste));

        if (!p.isOp()) {
            boolean arglarigor = false;
            Iterator var14 = lists.iterator();

            while(var14.hasNext()) {
                String listcik = (String)var14.next();
                if (listcik.endsWith(" *")) {
                    listcik = listcik.replace(" *", "");
                    if (e.getMessage().startsWith(listcik)) {
                        arglarigor = true;
                        icerensatir = listcik;
                        break;
                    }
                } else if (e.getMessage().startsWith(listcik)) {
                    icerensatir = listcik;
                    break;
                }
            }

            Iterator var15;
            String mesajcik;
            String ayrac;
            List messages;
            String mesajlar;
            if (icerensatir != null) {
                if (!arglarigor && !e.getMessage().endsWith(icerensatir)) {
                    e.setCancelled(true);
                    messages = komut.getStringList("permissions." + altliste + "_error_message");
                    var15 = messages.iterator();

                    while(var15.hasNext()) {
                        mesajlar = (String)var15.next();
                        if (mesajlar.contains("%")) {
                            mesajcik = mesajlar.toLowerCase();
                            lists.removeAll(gizlikomutlar);
                            ayrac = String.join(komut.getString("permissions." + altliste + "_error_message_splitter"), lists);
                            mesajcik = mesajcik.replaceAll("%permissions." + altliste + "%", ayrac);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', mesajcik));
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', mesajlar));
                        }
                    }
                } else if (arglarigor && !e.getMessage().startsWith(icerensatir)) {
                    e.setCancelled(true);
                    messages = komut.getStringList("permissions." + altliste + "_error_message");
                    var15 = messages.iterator();

                    while(var15.hasNext()) {
                        mesajlar = (String)var15.next();
                        if (mesajlar.contains("%")) {
                            mesajcik = mesajlar.toLowerCase();
                            lists.removeAll(gizlikomutlar);
                            ayrac = String.join(komut.getString("permissions." + altliste + "_error_message_splitter"), lists);
                            mesajcik = mesajcik.replaceAll("%permissions." + altliste + "%", ayrac);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', mesajcik));
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', mesajlar));
                        }
                    }
                }
            } else {
                e.setCancelled(true);
                messages = komut.getStringList("permissions." + altliste + "_error_message");
                var15 = messages.iterator();

                while(var15.hasNext()) {
                    mesajlar = (String)var15.next();
                    if (mesajlar.contains("%")) {
                        mesajcik = mesajlar.toLowerCase();
                        lists.removeAll(gizlikomutlar);
                        ayrac = String.join(komut.getString("permissions." + altliste + "_error_message_splitter"), lists);
                        mesajcik = mesajcik.replaceAll("%permissions." + altliste + "%", ayrac);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', mesajcik));
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', mesajlar));
                    }
                }
            }
        }

    }
}
