package com.diamondshark.ultimateMCBingo.BingoTasks;

import org.bukkit.Material;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapCursor;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MinecraftFont;

public class ItemTaskHandler extends AbstractTasks{

    public static final ItemTaskContainer[] ITEM_TASK_LIST = {
            new ItemTaskContainer(new Material[]{Material.OBSIDIAN}, 3, 10),
            new ItemTaskContainer(new Material[]{Material.QUARTZ_BLOCK}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.SEAGRASS}, 48, 63),
            new ItemTaskContainer(new Material[]{Material.GOLD_BLOCK}, 3, 3),
            new ItemTaskContainer(new Material[]{Material.CRIMSON_STEM}, 30, 30),
            new ItemTaskContainer(new Material[]{Material.BONE_BLOCK}, 8, 8),
            new ItemTaskContainer(new Material[]{Material.DAMAGED_ANVIL}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.BROWN_WOOL}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.ROTTEN_FLESH}, 28, 57),
            new ItemTaskContainer(new Material[]{Material.FIRE_CHARGE}, 6, 6),
            new ItemTaskContainer(new Material[]{Material.PUMPKIN}, 8, 8),
            new ItemTaskContainer(new Material[]{Material.PISTON}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.BLAZE_ROD}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.HEART_OF_THE_SEA}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.GREEN_DYE}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.BASALT}, 3, 3),
            new ItemTaskContainer(new Material[]{Material.DRIED_KELP_BLOCK}, 16, 32),
            new ItemTaskContainer(new Material[]{Material.MUSHROOM_STEW}, 4, 4),
            new ItemTaskContainer(new Material[]{Material.LAPIS_BLOCK}, 2, 2),
            new ItemTaskContainer(new Material[]{Material.CRYING_OBSIDIAN}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.BEETROOT_SOUP}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.CARROT_ON_A_STICK}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.GOLDEN_APPLE}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.HONEYCOMB_BLOCK}, 3, 3),
            new ItemTaskContainer(new Material[]{Material.SOUL_SOIL}, 10, 10),
            new ItemTaskContainer(new Material[]{Material.MELON_SLICE}, 48, 50),
            new ItemTaskContainer(new Material[]{Material.SPECTRAL_ARROW}, 18, 18),
            new ItemTaskContainer(new Material[]{Material.MAGMA_BLOCK}, 13, 28),
            new ItemTaskContainer(new Material[]{Material.HONEY_BLOCK}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.GUNPOWDER}, 5, 8),
            new ItemTaskContainer(new Material[]{Material.RABBIT_STEW}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.GOLD_NUGGET}, 33, 52),
            new ItemTaskContainer(new Material[]{Material.EGG}, 16, 16),
            new ItemTaskContainer(new Material[]{Material.PUFFERFISH}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.IRON_BLOCK}, 6, 6),
            new ItemTaskContainer(new Material[]{Material.BOOKSHELF}, 4, 4),
            new ItemTaskContainer(new Material[]{Material.PRISMARINE}, 2, 2),
            new ItemTaskContainer(new Material[]{Material.ITEM_FRAME}, 20, 21),
            new ItemTaskContainer(new Material[]{Material.FLINT}, 49, 49),
            new ItemTaskContainer(new Material[]{Material.INK_SAC}, 7, 7),
            new ItemTaskContainer(new Material[]{Material.APPLE}, 3, 3),
            new ItemTaskContainer(new Material[]{Material.ENDER_PEARL}, 2, 6),
            new ItemTaskContainer(new Material[]{Material.SLIME_BALL}, 7, 7),
            new ItemTaskContainer(new Material[]{Material.SOUL_LANTERN}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.SALMON}, 4, 4),
            new ItemTaskContainer(new Material[]{Material.ENDER_EYE}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.DAYLIGHT_DETECTOR}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.CAVE_VINES}, 14, 14),
            new ItemTaskContainer(new Material[]{Material.STRIPPED_WARPED_HYPHAE}, 17, 17),
            new ItemTaskContainer(new Material[]{Material.EMERALD}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.SPIDER_EYE}, 8, 8),
            new ItemTaskContainer(new Material[]{Material.HAY_BLOCK}, 2, 2),
            new ItemTaskContainer(new Material[]{Material.JUKEBOX}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.FIREWORK_STAR}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.STRIPPED_DARK_OAK_LOG}, 20, 20),
            new ItemTaskContainer(new Material[]{Material.RED_NETHER_BRICKS}, 30, 30),
            new ItemTaskContainer(new Material[]{Material.PHANTOM_MEMBRANE}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.CARVED_PUMPKIN}, 4, 4),
            new ItemTaskContainer(new Material[]{Material.FERMENTED_SPIDER_EYE}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.IRON_NUGGET}, 49, 49),
            new ItemTaskContainer(new Material[]{Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS}, 1, 1),
            new ItemTaskContainer(new Material[]{Material.ROTTEN_FLESH, Material.SPIDER_EYE, Material.BONE, Material.GUNPOWDER, Material.ENDER_PEARL}, 1, 1)

    };

    private int ItemIndex = 0;
    private int ItemQuantity = 5;

    public ItemTaskHandler ()
    {
        super();
    }

    @Override
    public int checkCompletion(boolean isGameFinished) {
        return AbstractTasks.NOT_STARTED;
    }

    @Override
    public void drawBingoSquare(MapCanvas mapCanvas, int squareX, int squareY) {
        super.drawBingoSquare(mapCanvas, squareX, squareY);

        String bingoSquareText = "";

        if(ItemQuantity > 1)
        {
            bingoSquareText = ItemQuantity + " " + ITEM_TASK_LIST[ItemIndex].getItemType()[0].name() + "s";
        }
        else
        {
            bingoSquareText = ITEM_TASK_LIST[ItemIndex].getItemType()[0].name();
        }

        MapCursorCollection cursors = mapCanvas.getCursors();

        int TextRow = 1;
        int IndexDrawn = 0;

        while(bingoSquareText.length() > IndexDrawn && TextRow < 6)
        {
            String drawString = "";
            if((IndexDrawn + 9) < bingoSquareText.length())
            {
                drawString = bingoSquareText.substring(IndexDrawn, IndexDrawn + 9);
                drawString = drawString.substring(0, drawString.lastIndexOf(' '));
            }
            else
            {
                drawString = bingoSquareText.substring(IndexDrawn);
            }

            IndexDrawn += drawString.length();


            cursors.addCursor(new MapCursor((byte)((squareX + 12) - (int)(drawString.length()* 1.231)), (byte)(squareY + (TextRow * 3)), (byte) 8, MapCursor.Type.WHITE_POINTER, true, drawString));

            //mapCanvas.drawText(((squareX + 12) - (int)(drawString.length()* 1.231)), (squareY + (TextRow * 7)), MinecraftFont.Font, drawString);

            TextRow += 1;
        }

        mapCanvas.setCursors(cursors);
    }


}
