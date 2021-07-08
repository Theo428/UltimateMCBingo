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

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        getCommand("bingo").setExecutor(new CommandBingo(this));

        getServer().getPluginManager().registerEvents(new BingoCardListener(), this);
    }

    public void gameOver()
    {
        gameStarted = false;
        gameOver = true;

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
        gameStarted = true;
        gameOver = false;

        for(int i = 0; i < bingoCards.size(); i++)
        {
            bingoCards.get(i).gameStart();
        }
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
