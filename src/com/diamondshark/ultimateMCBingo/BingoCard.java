package com.diamondshark.ultimateMCBingo;

import com.diamondshark.ultimateMCBingo.BingoTasks.AbstractTask;
import com.diamondshark.ultimateMCBingo.BingoTasks.ItemTasks.ItemTaskHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class BingoCard implements InventoryHolder
{
    private static final String INVENTORY_NAME = "Bingo Card";

    private AbstractTask[][] BingoCard = new AbstractTask[5][5];

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
        updateInventoryGUI();
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
                BingoCard[x][y] = generateRandomTask();


                ItemStack taskSymbol =  new ItemStack(BingoCard[x][y].getBingoSymbol(), BingoCard[x][y].getTaskSymbolQuantity());
                ItemMeta meta = taskSymbol.getItemMeta();
                meta.setDisplayName(BingoCard[x][y].getTaskTitle());
                meta.setLore(BingoCard[x][y].getTaskDescription());
                taskSymbol.setItemMeta(meta);

                bingoCardGUI.setItem(((x+2) + (y*9)), taskSymbol);
            }
        }
    }

    public void updateInventoryGUI()
    {
        for(int x = 0; x < BingoCard.length; x++)
        {
            for(int y = 0; y < BingoCard[x].length; y++)
            {
                ItemStack taskSymbol =  new ItemStack(BingoCard[x][y].getBingoSymbol(), BingoCard[x][y].getTaskSymbolQuantity());
                ItemMeta meta = taskSymbol.getItemMeta();
                meta.setDisplayName(BingoCard[x][y].getTaskTitle());
                meta.setLore(BingoCard[x][y].getTaskDescription());
                taskSymbol.setItemMeta(meta);

                bingoCardGUI.setItem(((x+2) + (y*9)), taskSymbol);
            }
        }
    }

    public boolean periodicWinCheck(World world)
    {
        Player player = null;

        for(int i = 0; i < world.getPlayers().size(); i++)
        {
            if(world.getPlayers().get(i).getUniqueId() == playerUUID)
            {
                player = world.getPlayers().get(i);
            }
        }

        if(player == null)
        {
            return false;
        }

        for(int x = 0; x < BingoCard.length; x++)
        {
            for(int y = 0; y < BingoCard[x].length; y++)
            {
                boolean hasStatusChanged = BingoCard[x][y].checkCompletion(player);

                if(hasStatusChanged)
                {
                    player.sendMessage("Task:" + BingoCard[x][y].getTaskTitle() + " has updated its status");
                    if(checkWin())
                    {
                        player.sendMessage("You won!");
                        return true;
                    }
                }
            }
        }

        //player.sendMessage(BingoCard[0][0].getPlayerItemQuantity() + " out of " + BingoCard[0][0].getTaskTitle());
        return false;
    }

    public boolean checkWin()
    {
        //Check Rows
        for(int x = 0; x < BingoCard.length; x++)
        {
            int squaresCompletedInRow = 0;
            for(int y = 0; y < BingoCard[x].length; y++)
            {
                if(BingoCard[x][y].getStatus() >= AbstractTask.PENDING)
                {
                    squaresCompletedInRow++;
                }
            }

            if(squaresCompletedInRow == 5)
            {
                return true;
            }
        }

        //Check Columns
        for(int y = 0; y < BingoCard[0].length; y++)
        {
            int squaresCompletedInColumn = 0;
            for(int x = 0; x < BingoCard.length; x++)
            {
                if(BingoCard[x][y].getStatus() >= AbstractTask.PENDING)
                {
                    squaresCompletedInColumn++;
                }
            }

            if(squaresCompletedInColumn == 5)
            {
                return true;
            }
        }

        //Check diagonal 1
        int squaresCompletedInDiagonal= 0;
        for(int i = 0; i < BingoCard.length; i++)
        {
            if(BingoCard[i][i].getStatus() >= AbstractTask.PENDING)
            {
                squaresCompletedInDiagonal++;
            }
        }
        if(squaresCompletedInDiagonal == 5)
        {
            return true;
        }

        //Check diagonal 2
        squaresCompletedInDiagonal = 0;
        for(int i = 0; i < BingoCard.length; i++)
        {
            if(BingoCard[i][4-i].getStatus() >= AbstractTask.PENDING)
            {
                squaresCompletedInDiagonal++;
            }
        }

        if(squaresCompletedInDiagonal == 5)
        {
            return true;
        }

        return false;
    }

    private AbstractTask generateRandomTask()
    {
        int totalTaskPool = 0;

        //total number of tasks
        totalTaskPool += ItemTaskHandler.getNumberOfTasks();

        int randomTask = (int)(Math.random() * (totalTaskPool+1));

        //check if task is an ItemTask
        if(randomTask < ItemTaskHandler.getNumberOfTasks())
        {
            return new ItemTaskHandler(randomTask);
        }
        else
        {
            randomTask -= ItemTaskHandler.getNumberOfTasks();
        }

        return null;
    }
}
