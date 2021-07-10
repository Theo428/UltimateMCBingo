package com.diamondshark.ultimateMCBingo.BingoTasks;


import com.diamondshark.ultimateMCBingo.BingoCard;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public abstract class AbstractTask
{
    public static final int NOT_STARTED = 0;
    public static final int IN_PROGRESS = 1;
    public static final int PENDING = 2;
    public static final int FINISHED = 3;


    private int Status = NOT_STARTED;

    private Material bingoSymbol;
    private String taskTitle;
    private List<String> taskDescription;
    private int taskSymbolQuantity = 1;

    public AbstractTask(Material bingoSymbol)
    {
        this.bingoSymbol = bingoSymbol;
    }

    public AbstractTask(Material bingoSymbol, String taskTitle)
    {
        this.bingoSymbol = bingoSymbol;
        this.taskTitle = taskTitle;
    }

    public AbstractTask(Material bingoSymbol, String taskTitle, List<String> taskDescription)
    {
        this.bingoSymbol = bingoSymbol;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }

    public abstract boolean checkCompletion(Player player);


    public void gameOver()
    {
        if(Status == PENDING)
        {
            setStatus(FINISHED);
        }

    }

    //getters
    public Material getBingoSymbol(int viewType) {
        switch (viewType)
        {
            case BingoCard.DEFAULT_CARD_VIEW:
                return bingoSymbol;

            case BingoCard.PROGRESS_CARD_VIEW:
                switch (Status)
                {
                    case NOT_STARTED:
                        return Material.GRAY_STAINED_GLASS_PANE;

                    case IN_PROGRESS:
                        return Material.ORANGE_STAINED_GLASS_PANE;

                    case PENDING:
                        return Material.BLUE_STAINED_GLASS_PANE;

                    case FINISHED:
                        return Material.LIME_STAINED_GLASS_PANE;
                }
        }
        return bingoSymbol;
    }

    public int getStatus() {
        return Status;
    }

    public String getTaskTitle() {
        String taskTitleOutput = "§l" + taskTitle;

        switch (Status)
        {
            case NOT_STARTED:
                taskTitleOutput = "§8" + taskTitleOutput;
                break;

            case IN_PROGRESS:
                taskTitleOutput = "§6" + taskTitleOutput;
                break;

            case PENDING:
                taskTitleOutput = "§9" + taskTitleOutput;
                break;

            case FINISHED:
                taskTitleOutput = "§a" + taskTitleOutput;
                break;
        }
        return taskTitleOutput;
    }

    public List<String> getTaskDescription() {
        return taskDescription;
    }

    public int getTaskSymbolQuantity() {
        return taskSymbolQuantity;
    }


    //setters
    protected void setBingoSymbol(Material bingoSymbol) {
        this.bingoSymbol = bingoSymbol;
    }

    protected void setStatus(int Status) {
        this.Status = Status;
    }

    protected void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    protected void setTaskDescription(List<String> taskDescription) {
        this.taskDescription = taskDescription;
    }

    protected void setTaskSymbolQuantity(int taskSymbolQuantity) {
        this.taskSymbolQuantity = taskSymbolQuantity;
    }
}
