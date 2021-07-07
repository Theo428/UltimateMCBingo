package com.diamondshark.ultimateMCBingo.BingoTasks.ItemTasks;

import org.bukkit.Material;

public class ItemTaskContainer
{
    private Material[] ItemType;
    private int minQuantity;
    private int maxQuantity;

    public ItemTaskContainer (Material[] ItemType, int minQuantity, int maxQuantity)
    {
        this.ItemType = ItemType;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    //Getters
    public Material[] getItemType() {
        return ItemType;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }


    //Setters
    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public void setItemType(Material[] itemType) {
        ItemType = itemType;
    }
}
