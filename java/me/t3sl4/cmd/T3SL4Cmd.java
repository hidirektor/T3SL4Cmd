package me.t3sl4.cmd;

import java.io.File;
import java.io.IOException;
import java.util.List;

import me.t3sl4.cmd.commands.Commands;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class T3SL4Cmd extends JavaPlugin {
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new Commands(), this);
        File folder = new File(this.getDataFolder(), "/");
        File cmdfile = new File(this.getDataFolder(), "/commands.yml");
        YamlConfiguration var3 = YamlConfiguration.loadConfiguration(cmdfile);

        try {
            if (!folder.exists()) {
                folder.mkdir();
            }

            if (!cmdfile.exists()) {
                cmdfile.createNewFile();
                this.FileConfigration("/commands.yml");
            }
        } catch (IOException var5) {
        }
        Bukkit.getConsoleSender().sendMessage("   ");
        Bukkit.getConsoleSender().sendMessage("  ____   __   __  _   _   _____   _____   ____    _       _  _   ");
        Bukkit.getConsoleSender().sendMessage(" / ___|  \\ \\ / / | \\ | | |_   _| |___ /  / ___|  | |     | || |  ");
        Bukkit.getConsoleSender().sendMessage(" \\___ \\   \\ V /  |  \\| |   | |     |_ \\  \\___ \\  | |     | || |_ ");
        Bukkit.getConsoleSender().sendMessage("  ___) |   | |   | |\\  |   | |    ___) |  ___) | | |___  |__   _|");
        Bukkit.getConsoleSender().sendMessage(" |____/    |_|   |_| \\_|   |_|   |____/  |____/  |_____|    |_|  ");
        Bukkit.getConsoleSender().sendMessage("    ");
        Bukkit.getConsoleSender().sendMessage("T3SL4 Series: T3SL4Cmd");
    }

    public void FileConfigration(String dosya) {
        File file = new File(this.getDataFolder(), dosya);
        if (dosya == "/commands.yml") {
            FileConfiguration yml = YamlConfiguration.loadConfiguration(file);
            yml.set("permissions.oyuncu", "");
            List<String> komutList = yml.getStringList("permissions.oyuncu");
            komutList.add("/magaza");
            komutList.add("/market *");
            komutList.add("/is go");
            yml.set("permissions.oyuncu", komutList);
            yml.set("permissions.oyuncu_secret_commands", "");
            List<String> gizliList = yml.getStringList("permissions.oyuncu_secret_commands");
            gizliList.add("/is go");
            yml.set("permissions.oyuncu_error_message", "");
            List<String> hataList = yml.getStringList("permissions.oyuncu_error_message");
            hataList.add("&b&l-----------------------------------");
            hataList.add("&aKullanabilecegin Komutlar: %permissions.oyuncu%");
            hataList.add("&b&l-----------------------------------");
            yml.set("permissions.oyuncu_error_message", hataList);
            yml.set("permissions.oyuncu_error_message_splitter", ",");

            try {
                yml.save(file);
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

    }
}
