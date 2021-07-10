package com.diamondshark.ultimateMCBingo.BingoTasks.EventTasks;

import com.diamondshark.ultimateMCBingo.BingoTasks.AbstractTask;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class EventTasks extends AbstractTask
{

    public EventTasks(Material bingoSymbol)
    {
        super(bingoSymbol);
    }

    @Override
    public boolean checkCompletion(Player player)
    {
        return false;
    }

}
