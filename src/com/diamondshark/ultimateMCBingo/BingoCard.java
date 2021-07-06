package com.diamondshark.ultimateMCBingo;

import com.diamondshark.ultimateMCBingo.BingoTasks.AbstractTasks;
import com.diamondshark.ultimateMCBingo.BingoTasks.ItemTaskHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class BingoCard implements InventoryHolder
{
    private static final String INVENTORY_NAME = "Bingo Card";

    private AbstractTasks[][] BingoCard = new AbstractTasks[5][5];

    private UUID playerUUID;

    private Inventory bingoCardGUI;

    public BingoCard(UUID playerUUID)
    {
        this.playerUUID = playerUUID;

        bingoCardGUI = Bukkit.createInventory(this, 54, INVENTORY_NAME);

        GenerateBingoCard();
    }

    @Override
    public Inventory getInventory()
    {
        return bingoCardGUI;
    }

    public UUID getPlayerUUID()
    {
        return playerUUID;
    }

    public void GenerateBingoCard()
    {
        for(int x = 0; x < BingoCard.length; x++)
        {
            for(int y = 0; y < BingoCard[x].length; y++)
            {
                BingoCard[x][y] = new ItemTaskHandler(0);
                ItemStack taskSymbol =  new ItemStack(BingoCard[x][y].getBingoSymbol(), BingoCard[x][y].getTaskSymbolQuantity());
                ItemMeta meta = taskSymbol.getItemMeta();
                meta.setDisplayName(BingoCard[x][y].getTaskTitle());
                meta.setLore(BingoCard[x][y].getTaskDescription());
                taskSymbol.setItemMeta(meta);

                bingoCardGUI.setItem(((x+2) + (y*9)), taskSymbol);
            }
        }
    }
}
