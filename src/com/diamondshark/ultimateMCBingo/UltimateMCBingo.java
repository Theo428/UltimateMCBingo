package com.diamondshark.ultimateMCBingo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class UltimateMCBingo extends JavaPlugin
{
    private ArrayList<BingoCard> bingoCards = new ArrayList<BingoCard>();

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        this.getCommand("bingo").setExecutor(new CommandBingo(bingoCards));
    }

}