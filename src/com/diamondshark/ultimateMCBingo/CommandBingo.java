package com.diamondshark.ultimateMCBingo;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import java.util.ArrayList;

public class CommandBingo implements CommandExecutor
{
    private ArrayList<BingoCard> bingoCards;

    public CommandBingo(ArrayList<BingoCard> bingoCards)
    {
        this.bingoCards = bingoCards;
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
            }
        }

        return false;
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

        ItemStack i = new ItemStack(Material.FILLED_MAP, 1);
        MapView view = Bukkit.createMap(player.getWorld());
        view.getRenderers().clear();
        view.addRenderer(bingoCards.get(bingoCards.size() - 1));

        MapMeta mapMeta = (MapMeta) i.getItemMeta();
        mapMeta.setMapView(view);
        mapMeta.setDisplayName("Bingo Card");
        i.setItemMeta(mapMeta);

        player.getInventory().addItem(i);
        return true;
    }

    private boolean playerLeave(Player player)
    {
        for(int i = 0; i < bingoCards.size(); i++)
        {
            if(player.getUniqueId() == bingoCards.get(i).getPlayerUUID())
            {
                bingoCards.remove(i);
                return true;
            }
        }
        player.sendMessage("You have left the bingo game");
        return true;
    }

}
