package com.diamondshark.ultimateMCBingo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandBingo implements CommandExecutor
{
    private UltimateMCBingo plugin;

    public CommandBingo(UltimateMCBingo plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            switch (args[0])
            {
                case "join":
                    plugin.playerJoin(player);
                    return true;

                case "leave":
                    plugin.playerLeave(player);
                    return true;

                case "card":
                    return playerViewCard(player);

                case "start":
                    return bingoStart(player);

                case "config":
                    return bingoConfig(player, plugin);

                default:
                    commandSender.sendMessage("§cInvalid arguments");
                    return true;
            }
        }

        commandSender.sendMessage("§cOnly players can use this command");
        return true;
    }

    private boolean playerViewCard(Player player)
    {
        if(plugin.isGameStarted() || plugin.isGameOver())
        {
            for (int i = 0; i < plugin.getBingoCards().size(); i++) {
                if (player.getUniqueId() == plugin.getBingoCards().get(i).getPlayer().getUniqueId()) {
                    player.openInventory(plugin.getBingoCards().get(i).getInventory());
                    return true;
                }
            }
        }
        else
        {
            player.sendMessage("§cGame has not started yet");
            return true;
        }

        player.sendMessage("§cYou must join a bingo game to view your bingo card.");
        return true;

    }

    private boolean bingoStart(Player player)
    {
        plugin.broadcastMessage(player.getWorld(), "§l§aThe game has Started!");
        plugin.gameStart();
        return true;
    }

    private boolean bingoConfig(Player player, Plugin plugin)
    {
        player.openInventory(new BingoConfigInventory(plugin).getInventory());
        return true;
    }

}
