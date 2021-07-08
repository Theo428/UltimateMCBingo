package com.diamondshark.ultimateMCBingo;

import com.diamondshark.ultimateMCBingo.BingoTasks.AbstractTask;
import com.diamondshark.ultimateMCBingo.BingoTasks.ItemTasks.ItemTaskHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;


public class BingoCard implements InventoryHolder
{
    private static final String INVENTORY_NAME = "Bingo Card";

    public static final int DEFAULT_CARD_VIEW = 0;
    public static final int PROGRESS_CARD_VIEW = 1;

    private AbstractTask[][] BingoCard = new AbstractTask[5][5];

    private Player player;
    private UltimateMCBingo plugin;

    private Inventory bingoCardGUI;

    private boolean hasWon = false;
    private int bingoCardView = DEFAULT_CARD_VIEW;

    public BingoCard(UltimateMCBingo plugin, Player player)
    {
        this.player = player;
        this.plugin = plugin;

        bingoCardGUI = Bukkit.createInventory(this, 54, INVENTORY_NAME);


        ItemStack DefaultViewItem =  new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
        ItemMeta meta = DefaultViewItem.getItemMeta();
        meta.setDisplayName("§l§9Default View");
        DefaultViewItem.setItemMeta(meta);
        bingoCardGUI.setItem(0, DefaultViewItem);

        ItemStack progressViewItem =  new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
        meta = progressViewItem.getItemMeta();
        meta.setDisplayName("§l§9Progress View");
        progressViewItem.setItemMeta(meta);
        bingoCardGUI.setItem(9, progressViewItem);
    }

    private BukkitRunnable periodicWinChecker;

    @Override
    public Inventory getInventory()
    {
        updateInventoryGUI();
        return bingoCardGUI;
    }

    public Player getPlayer()
    {
        return player;
    }

    public boolean HasWon() {
        return hasWon;
    }

    public void setBingoCardView(int bingoCardView) {
        this.bingoCardView = bingoCardView;
    }

    public void gameOver()
    {
        periodicWinChecker.cancel();

        for(int x = 0; x < BingoCard.length; x++)
        {
            for(int y = 0; y < BingoCard[x].length; y++)
            {
                BingoCard[x][y].gameOver();
            }
        }
    }

    public void gameStart()
    {
        GenerateBingoCard();
        hasWon = false;

        periodicWinChecker = new BukkitRunnable() {
            @Override
            public void run() {
                if(periodicWinCheck())
                {
                    gameWon();
                }
            }
        };

        periodicWinChecker.runTaskTimer(plugin, 0L, 10);
    }

    public void GenerateBingoCard()
    {
        ArrayList<AbstractTask>  TaskList = new ArrayList<AbstractTask>();
        TaskList = ItemTaskHandler.addAllTasksToList(TaskList);

        for(int x = 0; x < BingoCard.length; x++)
        {
            for(int y = 0; y < BingoCard[x].length; y++)
            {
                int randomIndex = (int)(Math.random() * TaskList.size());

                BingoCard[x][y] = TaskList.get(randomIndex);

                TaskList.remove(randomIndex);
            }
        }

        updateInventoryGUI();
    }

    public void updateInventoryGUI()
    {
        for(int x = 0; x < BingoCard.length; x++)
        {
            for(int y = 0; y < BingoCard[x].length; y++)
            {
                ItemStack taskSymbol =  new ItemStack(BingoCard[x][y].getBingoSymbol(bingoCardView), BingoCard[x][y].getTaskSymbolQuantity());
                ItemMeta meta = taskSymbol.getItemMeta();
                meta.setDisplayName(BingoCard[x][y].getTaskTitle());
                meta.setLore(BingoCard[x][y].getTaskDescription());
                taskSymbol.setItemMeta(meta);

                bingoCardGUI.setItem(((x+2) + (y*9)), taskSymbol);
            }
        }
    }

    public boolean periodicWinCheck()
    {
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
                    if(checkWin())
                    {
                        return true;
                    }
                }
            }
        }

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

    private void gameWon()
    {
        hasWon = true;
        plugin.gameOver();
    }
}
