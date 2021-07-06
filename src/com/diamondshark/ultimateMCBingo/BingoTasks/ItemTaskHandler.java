package com.diamondshark.ultimateMCBingo.BingoTasks;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

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

    private static final String[] TASK_DESCRIPTION = {
            "§7This Task requires that you have",
            "§7the item(s) in your inventory",
            "§7at the end of the game."};

    private int ItemIndex = 0;
    private int ItemQuantity = 5;

    public ItemTaskHandler (int ItemIndex)
    {
        super(ITEM_TASK_LIST[ItemIndex].getItemType()[0]);

        ItemQuantity = (int)(Math.random() * (ITEM_TASK_LIST[ItemIndex].getMaxQuantity() - ITEM_TASK_LIST[ItemIndex].getMinQuantity() + 1) + ITEM_TASK_LIST[ItemIndex].getMinQuantity());

        List<String> taskDescriptionList = new ArrayList<String>();

        taskDescriptionList.add(TASK_DESCRIPTION[0]);
        taskDescriptionList.add(TASK_DESCRIPTION[1]);
        taskDescriptionList.add(TASK_DESCRIPTION[2]);

        super.setTaskTitle(generateTaskTitle());
        super.setTaskDescription(taskDescriptionList);
        super.setTaskSymbolQuantity(ItemQuantity);

        this.ItemIndex = ItemIndex;
    }

    @Override
    public int checkCompletion(boolean isGameFinished) {
        return AbstractTasks.NOT_STARTED;
    }

    private String generateTaskTitle()
    {
        String taskTitle = "";

        if(ItemQuantity > 1)
        {
            taskTitle = ItemQuantity + " " + ITEM_TASK_LIST[ItemIndex].getItemType()[0].name() + "s";
        }
        else
        {
            taskTitle = ITEM_TASK_LIST[ItemIndex].getItemType()[0].name();
        }

        return taskTitle;
    }

}
