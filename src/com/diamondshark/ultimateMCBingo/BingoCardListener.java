package com.diamondshark.ultimateMCBingo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BingoCardListener implements Listener
{
    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if(e.getClickedInventory() == null) { return; }

        if(e.getClickedInventory().getHolder() instanceof BingoCard)
        {
            e.setCancelled(true);
        }
    }
}
