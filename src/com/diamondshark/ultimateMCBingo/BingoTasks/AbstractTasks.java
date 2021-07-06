package com.diamondshark.ultimateMCBingo.BingoTasks;

import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;

public abstract class AbstractTasks
{
    public static final int NOT_STARTED = 0;
    public static final int IN_PROGRESS = 1;
    public static final int FINISHED = 2;

    protected static final int CHARS_PER_SQUARE = 9;

    private int Status = NOT_STARTED;

    public AbstractTasks ()
    {

    }

    public abstract int checkCompletion(boolean isGameFinished);                    //Returns completion level of task

    public void drawBingoSquare(MapCanvas mapCanvas, int squareX, int squareY)      //draws 24x24 pixel square
    {
        for(int x = 0; x < 24; x++)
        {
            for(int y = 0; y < 24; y++)
            {
                switch (Status)
                {
                    case NOT_STARTED:
                        mapCanvas.setPixel((x + squareX), (y + squareY), MapPalette.GRAY_1);
                        break;

                    case IN_PROGRESS:
                        mapCanvas.setPixel((x + squareX), (y + squareY), MapPalette.PALE_BLUE);
                        break;

                    case FINISHED:
                        mapCanvas.setPixel((x + squareX), (y + squareY), MapPalette.LIGHT_GREEN);
                        break;
                }
            }
        }
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        Status = Status;
    }
}
