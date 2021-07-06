package com.diamondshark.ultimateMCBingo.BingoTasks;


import org.bukkit.Material;

import java.util.List;

public abstract class AbstractTasks
{
    public static final int NOT_STARTED = 0;
    public static final int IN_PROGRESS = 1;
    public static final int FINISHED = 2;


    private int Status = NOT_STARTED;

    private Material bingoSymbol;
    private String taskTitle;
    private List<String> taskDescription;
    private int taskSymbolQuantity = 1;

    public AbstractTasks (Material bingoSymbol)
    {
        this.bingoSymbol = bingoSymbol;
    }

    public AbstractTasks (Material bingoSymbol, String taskTitle)
    {
        this.bingoSymbol = bingoSymbol;
        this.taskTitle = taskTitle;
    }

    public AbstractTasks (Material bingoSymbol, String taskTitle, List<String> taskDescription)
    {
        this.bingoSymbol = bingoSymbol;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }

    public abstract int checkCompletion(boolean isGameFinished);                    //Returns completion level of task

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
