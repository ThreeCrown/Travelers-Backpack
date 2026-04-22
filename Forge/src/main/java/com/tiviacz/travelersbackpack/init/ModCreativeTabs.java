package com.tiviacz.travelersbackpack.init;

import com.tiviacz.travelersbackpack.TravelersBackpack;
import com.tiviacz.travelersbackpack.blocks.TravelersBackpackBlock;
import com.tiviacz.travelersbackpack.components.RenderInfo;
import com.tiviacz.travelersbackpack.inventory.Tiers;
import com.tiviacz.travelersbackpack.util.NbtHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TravelersBackpack.MODID);
    public static RegistryObject<CreativeModeTab> TRAVELERS_BACKPACK = CREATIVE_MODE_TABS.register("travelersbackpack", () -> CreativeModeTab.builder()
            .icon(ModCreativeTabs::createTabStack)
            .title(Component.translatable("itemGroup.travelersbackpack")).displayItems(ModCreativeTabs::displayItems).build());

    public static ItemStack createTabStack() {
        ItemStack stack = new ItemStack(ModItems.STANDARD_TRAVELERS_BACKPACK.get());
        NbtHelper.set(stack, ModDataHelper.RENDER_INFO, RenderInfo.createCreativeTabInfo());
        return stack;
    }

    public static void displayItems(CreativeModeTab.ItemDisplayParameters displayParameters, CreativeModeTab.Output output) {
        //Standard
    output.accept(withDefaults(ModBlocks.STANDARD_TRAVELERS_BACKPACK));
        output.accept(createTieredBackpack(Tiers.IRON));
        output.accept(createTieredBackpack(Tiers.GOLD));
        output.accept(createTieredBackpack(Tiers.DIAMOND));
        output.accept(createTieredBackpack(Tiers.NETHERITE));

        //Blocks
    output.accept(withDefaults(ModBlocks.NETHERITE_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.DIAMOND_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.GOLD_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.EMERALD_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.IRON_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.LAPIS_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.REDSTONE_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.COAL_TRAVELERS_BACKPACK));

    output.accept(withDefaults(ModBlocks.QUARTZ_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.BOOKSHELF_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.END_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.NETHER_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.SANDSTONE_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.SNOW_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.SPONGE_TRAVELERS_BACKPACK));

    output.accept(withDefaults(ModBlocks.CAKE_TRAVELERS_BACKPACK));

    output.accept(withDefaults(ModBlocks.CACTUS_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.HAY_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.MELON_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.PUMPKIN_TRAVELERS_BACKPACK));

    output.accept(withDefaults(ModBlocks.CREEPER_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.DRAGON_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.ENDERMAN_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.BLAZE_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.GHAST_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.MAGMA_CUBE_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.SKELETON_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.SPIDER_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.WITHER_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.WARDEN_TRAVELERS_BACKPACK));

        //Friendly Mobs
    output.accept(withDefaults(ModBlocks.BAT_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.BEE_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.WOLF_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.FOX_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.OCELOT_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.HORSE_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.COW_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.PIG_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.SHEEP_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.CHICKEN_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.SQUID_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.VILLAGER_TRAVELERS_BACKPACK));
    output.accept(withDefaults(ModBlocks.IRON_GOLEM_TRAVELERS_BACKPACK));

        output.accept(ModItems.WHITE_SLEEPING_BAG.get());
        output.accept(ModItems.ORANGE_SLEEPING_BAG.get());
        output.accept(ModItems.MAGENTA_SLEEPING_BAG.get());
        output.accept(ModItems.LIGHT_BLUE_SLEEPING_BAG.get());
        output.accept(ModItems.YELLOW_SLEEPING_BAG.get());
        output.accept(ModItems.LIME_SLEEPING_BAG.get());
        output.accept(ModItems.PINK_SLEEPING_BAG.get());
        output.accept(ModItems.GRAY_SLEEPING_BAG.get());
        output.accept(ModItems.LIGHT_GRAY_SLEEPING_BAG.get());
        output.accept(ModItems.CYAN_SLEEPING_BAG.get());
        output.accept(ModItems.PURPLE_SLEEPING_BAG.get());
        output.accept(ModItems.BLUE_SLEEPING_BAG.get());
        output.accept(ModItems.BROWN_SLEEPING_BAG.get());
        output.accept(ModItems.GREEN_SLEEPING_BAG.get());
        output.accept(ModItems.RED_SLEEPING_BAG.get());
        output.accept(ModItems.BLACK_SLEEPING_BAG.get());
    }

    public static ItemStack createTieredBackpack(Tiers.Tier tier) {
        ItemStack stack = new ItemStack(ModItems.STANDARD_TRAVELERS_BACKPACK.get());
        NbtHelper.set(stack, ModDataHelper.TIER, tier.getOrdinal());
        return stack;
    }

    public static ItemStack withDefaults(RegistryObject<TravelersBackpackBlock> deferredBlock) {
        return new ItemStack(deferredBlock.get());
    }
}