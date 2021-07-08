package com.diamondshark.ultimateMCBingo;

import org.bukkit.entity.Player;
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
            BingoCard bingoCard = (BingoCard) e.getClickedInventory().getHolder();
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();
            if(e.getCurrentItem() == null) { return; }
            if(e.getSlot() == 0)
            {
                bingoCard.setBingoCardView(BingoCard.DEFAULT_CARD_VIEW);
                bingoCard.updateInventoryGUI();
            }
            else if(e.getSlot() == 9)
            {
                bingoCard.setBingoCardView(BingoCard.PROGRESS_CARD_VIEW);
                bingoCard.updateInventoryGUI();
            }
        }
    }
}
