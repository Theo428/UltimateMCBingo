package com.diamondshark.ultimateMCBingo;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

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
                    return playerJoin(player);

                case "leave":
                    return playerLeave(player);

                case "card":
                    return playerViewCard(player);

                case "start":
                    return bingoStart();

                default:
                    commandSender.sendMessage("§cInvalid arguments");
                    return true;
            }
        }

        commandSender.sendMessage("§cOnly players can use this command");
        return true;
    }

    private boolean playerJoin(Player player)
    {

        for(int i = 0; i < plugin.getBingoCards().size(); i++)
        {
            if(player.getUniqueId() == plugin.getBingoCards().get(i).getPlayer().getUniqueId())
            {
                player.sendMessage("§cYou have already joined the current bingo game.");
                return true;
            }
        }
        plugin.getBingoCards().add(new BingoCard(plugin, player));
        player.sendMessage("§aYou have joined the bingo game.");
        return true;
    }

    private boolean playerLeave(Player player)
    {
        for(int i = 0; i < plugin.getBingoCards().size(); i++)
        {
            if(player.getUniqueId() == plugin.getBingoCards().get(i).getPlayer().getUniqueId())
            {
                plugin.getBingoCards().remove(i);
                player.sendMessage("§aYou have left the bingo game");
                return true;
            }
        }
        player.sendMessage("§cYou are not currently in a bingo game");
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

    private boolean bingoStart()
    {
        plugin.gameStart();
        return true;
    }

}
