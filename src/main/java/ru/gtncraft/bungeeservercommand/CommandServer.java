package ru.gtncraft.bungeeservercommand;

import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

class CommandServer implements CommandExecutor, TabExecutor {
    private final BungeeServerCommand plugin;

    public CommandServer(final BungeeServerCommand plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginCommand("server").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Messages.get(Message.you_must_supply_server));
            return true;
        }

        String server = args[0];
        String name;

        if (sender instanceof Player) {
            if (args.length > 1) {
                name = args[1];
                if ((!sender.getName().equalsIgnoreCase(name)) || (!sender.hasPermission("bungeecord.server.other"))) {
                    sender.sendMessage(Messages.get(Message.you_dont_have_permissions, name, server));
                    return true;
                }
            } else {
                name = sender.getName();
            }
        } else {
            if (args.length < 2) {
                sender.sendMessage(Messages.get(Message.you_must_supply_player));
                return true;
            }
            name = args[1];
        }

        Player player = Bukkit.getPlayer(name);
        if (player == null) {
            sender.sendMessage(Messages.get(Message.player_not_found, name));
            return true;
        }

        plugin.connect(player, server);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return ImmutableList.of();
    }
}
