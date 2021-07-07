package com.diamondshark.ultimateMCBingo.BingoTasks;


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
    private int numberOfTasks;

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

    public abstract boolean checkCompletion(Player player);                    //Returns completion level of task
    public abstract int getPlayerItemQuantity();

    //getters
    public Material getBingoSymbol() {
        return bingoSymbol;
    }

    public int getStatus() {
        return Status;
    }

    public String getTaskTitle() {
        taskTitle = "§l" + taskTitle;

        switch (Status)
        {
            case NOT_STARTED:
                taskTitle = "§8" + taskTitle;
                break;

            case IN_PROGRESS:
                taskTitle = "§6" + taskTitle;
                break;

            case PENDING:
                taskTitle = "§9" + taskTitle;
                break;

            case FINISHED:
                taskTitle = "§a" + taskTitle;
                break;
        }
        return taskTitle;
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
