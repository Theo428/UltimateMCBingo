package com.diamondshark.ultimateMCBingo;

import com.diamondshark.ultimateMCBingo.BingoTasks.AbstractTasks;
import com.diamondshark.ultimateMCBingo.BingoTasks.ItemTaskHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.map.*;

import java.util.UUID;

public class BingoCard extends MapRenderer
{
    private AbstractTasks[][] BingoCard = new AbstractTasks[5][5];

    private UUID playerUUID;

    public BingoCard(UUID playerUUID)
    {
        this.playerUUID = playerUUID;

        GenerateBingoCard();
    }

    public UUID getPlayerUUID()
    {
        return playerUUID;
    }


    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player)
    {
        mapCanvas.setCursors(new MapCursorCollection());
        for(int x = 0; x < 128; x++)
        {
            for(int y = 0; y < 128; y++)
            {
                mapCanvas.setPixel(x, y, MapPalette.DARK_GRAY);
            }
        }

        for(int x = 0; x < BingoCard.length; x++)
        {
            for(int y = 0; y < BingoCard[x].length; y++)
            {
                BingoCard[x][y].drawBingoSquare(mapCanvas, (x * 26), (y * 26));
            }
        }
    }

    public void GenerateBingoCard()
    {
        for(int x = 0; x < BingoCard.length; x++)
        {
            for(int y = 0; y < BingoCard[x].length; y++)
            {
                BingoCard[x][y] = new ItemTaskHandler();
            }
        }
    }
}
