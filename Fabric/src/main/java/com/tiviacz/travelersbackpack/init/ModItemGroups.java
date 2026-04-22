package com.tiviacz.travelersbackpack.init;

import com.tiviacz.travelersbackpack.TravelersBackpack;
import com.tiviacz.travelersbackpack.components.RenderInfo;
import com.tiviacz.travelersbackpack.inventory.Tiers;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ModItemGroups {
    public static final ResourceKey<CreativeModeTab> TRAVELERS_BACKPACK = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "travelers_backpack"));

    private ModItemGroups() {
    }

    public static void registerItemGroup() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TRAVELERS_BACKPACK, FabricCreativeModeTab.builder()
                .icon(ModItemGroups::createTabStack)
                .title(Component.translatable("itemGroup.travelersbackpack")).build());
    }

    public static ItemStack createTabStack() {
        ItemStack stack = new ItemStack(ModItems.STANDARD_TRAVELERS_BACKPACK);
        stack.set(ModDataComponents.RENDER_INFO, RenderInfo.createCreativeTabInfo());
        return stack;
    }

    public static void addItemGroup() {
        CreativeModeTabEvents.modifyOutputEvent(TRAVELERS_BACKPACK).register(output ->
        {
            //Standard
            output.accept(createBackpackStack(ModBlocks.STANDARD_TRAVELERS_BACKPACK));
            output.accept(createTieredBackpack(Tiers.IRON));
            output.accept(createTieredBackpack(Tiers.GOLD));
            output.accept(createTieredBackpack(Tiers.DIAMOND));
            output.accept(createTieredBackpack(Tiers.NETHERITE));

            //Blocks
            output.accept(createBackpackStack(ModBlocks.NETHERITE_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.DIAMOND_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.GOLD_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.EMERALD_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.IRON_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.LAPIS_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.REDSTONE_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.COAL_TRAVELERS_BACKPACK));

            output.accept(createBackpackStack(ModBlocks.QUARTZ_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.BOOKSHELF_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.END_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.NETHER_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.SANDSTONE_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.SNOW_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.SPONGE_TRAVELERS_BACKPACK));

            output.accept(createBackpackStack(ModBlocks.CAKE_TRAVELERS_BACKPACK));

            output.accept(createBackpackStack(ModBlocks.CACTUS_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.HAY_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.MELON_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.PUMPKIN_TRAVELERS_BACKPACK));

            output.accept(createBackpackStack(ModBlocks.CREEPER_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.DRAGON_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.ENDERMAN_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.BLAZE_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.GHAST_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.MAGMA_CUBE_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.SKELETON_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.SPIDER_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.WITHER_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.WARDEN_TRAVELERS_BACKPACK));

            //Friendly Mobs
            output.accept(createBackpackStack(ModBlocks.BAT_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.BEE_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.WOLF_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.FOX_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.OCELOT_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.HORSE_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.COW_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.PIG_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.SHEEP_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.CHICKEN_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.SQUID_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.VILLAGER_TRAVELERS_BACKPACK));
            output.accept(createBackpackStack(ModBlocks.IRON_GOLEM_TRAVELERS_BACKPACK));

            output.accept(ModItems.WHITE_SLEEPING_BAG);
            output.accept(ModItems.ORANGE_SLEEPING_BAG);
            output.accept(ModItems.MAGENTA_SLEEPING_BAG);
            output.accept(ModItems.LIGHT_BLUE_SLEEPING_BAG);
            output.accept(ModItems.YELLOW_SLEEPING_BAG);
            output.accept(ModItems.LIME_SLEEPING_BAG);
            output.accept(ModItems.PINK_SLEEPING_BAG);
            output.accept(ModItems.GRAY_SLEEPING_BAG);
            output.accept(ModItems.LIGHT_GRAY_SLEEPING_BAG);
            output.accept(ModItems.CYAN_SLEEPING_BAG);
            output.accept(ModItems.PURPLE_SLEEPING_BAG);
            output.accept(ModItems.BLUE_SLEEPING_BAG);
            output.accept(ModItems.BROWN_SLEEPING_BAG);
            output.accept(ModItems.GREEN_SLEEPING_BAG);
            output.accept(ModItems.RED_SLEEPING_BAG);
            output.accept(ModItems.BLACK_SLEEPING_BAG);
        });
    }

    public static ItemStack createTieredBackpack(Tiers.Tier tier) {
        ItemStack stack = new ItemStack(ModItems.STANDARD_TRAVELERS_BACKPACK);
        stack.set(ModDataComponents.TIER, tier.getOrdinal());
        return stack;
    }

    public static ItemStack createBackpackStack(Block block) {
        return new ItemStack(block);
    }
}
