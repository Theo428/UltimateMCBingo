package com.diamondshark.ultimateMCBingo;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class UltimateMCBingo extends JavaPlugin
{
    private ArrayList<BingoCard> bingoCards = new ArrayList<BingoCard>();

    private boolean gameStarted = false;
    private boolean gameOver = false;

    private long startTime = 0;

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        this.saveDefaultConfig();

        getCommand("bingo").setExecutor(new CommandBingo(this));

        getServer().getPluginManager().registerEvents(new BingoCardListener(this), this);
    }

    public void gameOver()
    {
        gameStarted = false;
        gameOver = true;

        long gameLength = System.currentTimeMillis() - startTime;

        for(int i = 0; i < bingoCards.size(); i++)
        {
            bingoCards.get(i).gameOver();
            if(bingoCards.get(i).HasWon())
            {
                broadcastMessage(bingoCards.get(i).getPlayer().getWorld(), "§l§aGame Over!");
                broadcastMessage(bingoCards.get(i).getPlayer().getWorld(), ("§l§a" + bingoCards.get(i).getPlayer().getDisplayName() + " Has Won!"));
            }
        }
    }

    public void gameStart()
    {
        startTime = System.currentTimeMillis();
        gameStarted = true;
        gameOver = false;

        for(int i = 0; i < bingoCards.size(); i++)
        {
            if(getConfig().getBoolean(ConfigKeyReference.IDENTICAL_CARDS_KEY))
            {
                long seed = (long)(Math.random() * 1000000000);
                bingoCards.get(i).gameStart(seed);
            }
            else
            {
                bingoCards.get(i).gameStart();
            }
        }
    }

    public void playerJoin(Player player)
    {

        for(int i = 0; i < bingoCards.size(); i++)
        {
            if(player.getUniqueId() == bingoCards.get(i).getPlayer().getUniqueId())
            {
                player.sendMessage("§cYou have already joined the current bingo game.");
            }
        }
        bingoCards.add(new BingoCard(this, player));
        player.sendMessage("§aYou have joined the bingo game.");
    }

    public void playerLeave(Player player)
    {
        for(int i = 0; i < bingoCards.size(); i++)
        {
            if(player.getUniqueId() == bingoCards.get(i).getPlayer().getUniqueId())
            {
                bingoCards.remove(i);
                player.sendMessage("§aYou have left the bingo game");
            }
        }
        player.sendMessage("§cYou are not currently in a bingo game");
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    public ArrayList<BingoCard> getBingoCards() {
        return bingoCards;
    }

    public void setBingoCards(ArrayList<BingoCard> bingoCards) {
        this.bingoCards = bingoCards;
    }


    public void broadcastMessage(World world, String message)
    {
        for(Player player: world.getPlayers())
        {
            player.sendMessage(message);
        }
    }
}
