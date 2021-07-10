package com.diamondshark.ultimateMCBingo;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class BingoConfigInventory implements InventoryHolder
{
    public static final String CONFIG_NBT_KEY = "bingoConfig";

    public static final int IDENTICAL_CARDS_TOGGLE_TAG = 0;

    private static final String INVENTORY_NAME = "Bingo Config";

    private static final String[] IDENTICAL_CARDS_DESCRIPTION = {
            "Toggles whether all players",
            "have identical bingo cards."};

    private Inventory bingoConfigGUI;
    private Plugin plugin;
    private NamespacedKey key;

    public BingoConfigInventory(Plugin plugin)
    {
        bingoConfigGUI = Bukkit.createInventory(this, 54, INVENTORY_NAME);

        this.plugin = plugin;

        key = new NamespacedKey(plugin, CONFIG_NBT_KEY);

    }

    @Override
    public Inventory getInventory()
    {
        return null;
    }

    public NamespacedKey getKey()
    {
        return key;
    }

    public void updateConfigInventory()
    {
        //Configure identical Cards
        if(plugin.getConfig().getBoolean(ConfigKeyReference.IDENTICAL_CARDS_KEY))
            addItem(Material.GREEN_STAINED_GLASS_PANE, "§l§9Toggle Identical Bingo Cards", IDENTICAL_CARDS_DESCRIPTION, 4, 1, IDENTICAL_CARDS_TOGGLE_TAG);
        else
            addItem(Material.RED_STAINED_GLASS_PANE, "§l§9Toggle Identical Bingo Cards", IDENTICAL_CARDS_DESCRIPTION, 4, 1, IDENTICAL_CARDS_TOGGLE_TAG);
    }

    public void toggleIdenticalCards()
    {
        plugin.getConfig().set(ConfigKeyReference.IDENTICAL_CARDS_KEY, !plugin.getConfig().getBoolean(ConfigKeyReference.IDENTICAL_CARDS_KEY));
    }

    private void addItem(Material material, String itemName, String[] lore, int x, int y, int NBTTag)
    {
        ItemStack DefaultViewItem =  new ItemStack(material, 1);

        ItemMeta meta = DefaultViewItem.getItemMeta();
        meta.setDisplayName(itemName);
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, NBTTag);

        List<String> itemLoreList = new ArrayList<String>();

        for(String loreLine: lore)
        {
            itemLoreList.add(loreLine);
        }

        meta.setLore(itemLoreList);

        DefaultViewItem.setItemMeta(meta);

        bingoConfigGUI.setItem((x + (y*9)), DefaultViewItem);
    }
}
