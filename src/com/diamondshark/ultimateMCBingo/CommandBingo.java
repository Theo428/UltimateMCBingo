package com.diamondshark.ultimateMCBingo;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
    private ArrayList<BingoCard> bingoCards;

    private Plugin plugin;

    public CommandBingo(ArrayList<BingoCard> bingoCards, Plugin plugin)
    {
        this.bingoCards = bingoCards;
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
                    return bingoStart(player.getWorld());

                default:
                    commandSender.sendMessage("Invalid arguments");
                    return true;
            }
        }

        commandSender.sendMessage("Only players can use this command");
        return true;
    }

    private boolean playerJoin(Player player)
    {

        for(int i = 0; i < bingoCards.size(); i++)
        {
            if(player.getUniqueId() == bingoCards.get(i).getPlayerUUID())
            {
                player.sendMessage("You have already joined the current bingo game.");
                return true;
            }
        }
        bingoCards.add(new BingoCard(player.getUniqueId()));
        player.sendMessage("You have joined the bingo game.");
        return true;
    }

    private boolean playerLeave(Player player)
    {
        for(int i = 0; i < bingoCards.size(); i++)
        {
            if(player.getUniqueId() == bingoCards.get(i).getPlayerUUID())
            {
                bingoCards.remove(i);
                player.sendMessage("You have left the bingo game");
                return true;
            }
        }
        player.sendMessage("You are not currently in a bingo game");
        return true;
    }

    private boolean playerViewCard(Player player)
    {
        for(int i = 0; i < bingoCards.size(); i++)
        {
            if(player.getUniqueId() == bingoCards.get(i).getPlayerUUID())
            {
                player.openInventory(bingoCards.get(i).getInventory());
                return true;
            }
        }
        player.sendMessage("You must join a bingo game to view your bingo card.");
        return true;
    }

    private boolean bingoStart(World world)
    {
        new BukkitRunnable() {
            @Override
            public void run()
            {
                for(int i = 0; i < bingoCards.size(); i++)
                {
                    bingoCards.get(i).periodicWinCheck(world);
                }
            }
        }.runTaskTimer(plugin, 0L, 10);
        return true;
    }

}
