package ru.gtncraft.bungeeservercommand;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class BungeeServerCommand extends JavaPlugin {
    private final static String channel = "BungeeCord";

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getMessenger().registerOutgoingPluginChannel(this, channel);

        new CommandServer(this);

        Messages.init(getConfig().getConfigurationSection("messages"));
    }

    public void connect(final Player player, final String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(player.getName());
        out.writeUTF(server);
        player.sendPluginMessage(this, channel, out.toByteArray());
    }
}
