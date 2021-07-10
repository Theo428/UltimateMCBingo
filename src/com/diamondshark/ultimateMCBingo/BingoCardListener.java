package com.diamondshark.ultimateMCBingo;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class BingoCardListener implements Listener
{
    private UltimateMCBingo plugin;
    public BingoCardListener(UltimateMCBingo plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if(e.getClickedInventory() == null) { return; }

        if(e.getClickedInventory().getHolder() instanceof BingoCard)
        {
            if(e.getCurrentItem() == null) { return; }

            BingoCard bingoCard = (BingoCard) e.getClickedInventory().getHolder();
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();
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

        if(e.getClickedInventory().getHolder() instanceof BingoConfigInventory)
        {
            if(e.getCurrentItem() == null) { return; }

            BingoConfigInventory configGUI = (BingoConfigInventory) e.getClickedInventory().getHolder();

            NamespacedKey key = configGUI.getKey();
            ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();

            if(container.has(key , PersistentDataType.INTEGER)) {
                int value = container.get(key, PersistentDataType.INTEGER);

                switch (value)
                {
                    case BingoConfigInventory.IDENTICAL_CARDS_TOGGLE_TAG:
                        configGUI.toggleIdenticalCards();
                }
            }

            configGUI.updateConfigInventory();
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        if(plugin.getConfig().getBoolean(ConfigKeyReference.AUTO_JOIN_KEY))
        {
            plugin.playerJoin(e.getPlayer());
        }
    }
}
