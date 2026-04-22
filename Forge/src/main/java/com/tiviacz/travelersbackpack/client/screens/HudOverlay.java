package com.tiviacz.travelersbackpack.client.screens;

import com.tiviacz.travelersbackpack.TravelersBackpack;
import com.tiviacz.travelersbackpack.config.TravelersBackpackConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class HudOverlay {
    public static final ResourceLocation OVERLAY = new ResourceLocation(TravelersBackpack.MODID, "textures/gui/overlay.png");

    public static void renderOverlay(ItemStack stack, Minecraft mc, GuiGraphics g) {
        if(mc == null) return;

        var player = mc.player;
        var window = mc.getWindow();
        if(window == null) return;

        int x = window.getGuiScaledWidth() - TravelersBackpackConfig.CLIENT.overlay.offsetX.get();
        int y = window.getGuiScaledHeight() - TravelersBackpackConfig.CLIENT.overlay.offsetY.get();
        if(player == null) return;
        g.blit(OVERLAY, x, y, 10, 0, 10, 23);
        g.blit(OVERLAY, x - 12, y, 10, 0, 10, 23);
    }
}