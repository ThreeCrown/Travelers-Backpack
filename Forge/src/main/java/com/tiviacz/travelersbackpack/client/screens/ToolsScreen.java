package com.tiviacz.travelersbackpack.client.screens;

import com.mojang.blaze3d.platform.Window;
import com.mojang.datafixers.util.Pair;
import com.tiviacz.travelersbackpack.capability.CapabilityUtils;
import com.tiviacz.travelersbackpack.config.TravelersBackpackConfig;
import com.tiviacz.travelersbackpack.handlers.ModClientEventHandler;
import com.tiviacz.travelersbackpack.handlers.NeoForgeClientEventHandler;
import com.tiviacz.travelersbackpack.init.ModDataHelper;
import com.tiviacz.travelersbackpack.inventory.Tiers;
import com.tiviacz.travelersbackpack.inventory.menu.slot.ToolSlotItemHandler;
import com.tiviacz.travelersbackpack.network.ServerboundActionTagPacket;
import com.tiviacz.travelersbackpack.util.NbtHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class ToolsScreen extends Screen {
    private static final double REF_W = 1920.0;
    private static final double REF_H = 1080.0;
    private long openStartMs = -1;

    protected int hoveredResult = -1;
    private boolean swapWithRelease = true;

    public ToolsScreen() {
        super(Component.translatable("screen.travelersbackpack.tools_overlay"));
    }

    private float getOpenProgress() {
        long now = System.currentTimeMillis();
        if(openStartMs < 0) openStartMs = now;

        float durMs = 180.0F;
        return Mth.clamp((now - openStartMs) / durMs, 0.0f, 1.0f);
    }

    private Pair<Integer, Integer> getScaledWindow(boolean applyOverlayOffsets) {
        Window window = Minecraft.getInstance().getWindow();
        int centerX = window.getGuiScaledWidth() / 2;
        int centerY = window.getGuiScaledHeight() / 2;

        if(applyOverlayOffsets) {
            centerX += TravelersBackpackConfig.CLIENT.toolsOverlay.offsetX.get();
            centerY += TravelersBackpackConfig.CLIENT.toolsOverlay.offsetY.get();
        }

        return Pair.of(centerX, centerY);
    }

    @Override
    protected void init() {
        super.init();
        Pair<Integer, Integer> scaled = getScaledWindow(false);
        GLFW.glfwSetCursorPos(Minecraft.getInstance().getWindow().getWindow(), scaled.getFirst(), scaled.getSecond());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        Pair<Integer, Integer> scaled = getScaledWindow(true);
        float progress = getOpenProgress();

        ItemStack backpack = CapabilityUtils.getWearingBackpack(player);
        ItemStack heldItem = !player.getMainHandItem().isEmpty() ? player.getMainHandItem() : player.getOffhandItem();

        NonNullList<ItemStack> tools = NbtHelper.getOrDefault(backpack, ModDataHelper.TOOLS_CONTAINER, NonNullList.withSize(NbtHelper.getOrDefault(backpack, ModDataHelper.TOOL_SLOTS, Tiers.LEATHER.getToolSlots()), ItemStack.EMPTY));
        int nonEmptyCount = getNonEmptyTools(tools).size();

        boolean canAdd = ToolSlotItemHandler.isValid(heldItem) && nonEmptyCount < tools.size();
        int hoveredResult = RadialToolsOverlay.renderRadial(graphics, backpack, heldItem, tools, canAdd, scaled.getFirst(), scaled.getSecond(), mouseX, mouseY, partialTick, progress);
        this.hoveredResult = hoveredResult;

        if(!NeoForgeClientEventHandler.isKeyDown(ModClientEventHandler.SWAP_TOOL)) {
            if(hoveredResult != -1) {
                if(swapWithRelease && TravelersBackpackConfig.CLIENT.toolsOverlay.swapOnClose.get()) {
                    ServerboundActionTagPacket.create(ServerboundActionTagPacket.SWAP_TOOL, hoveredResult, 0);
                }
            }
            onClose();
        }
    }

    public static NonNullList<ItemStack> getNonEmptyTools(NonNullList<ItemStack> inventory) {
        NonNullList<ItemStack> tools = NonNullList.create();
        for(ItemStack itemStack : inventory) {
            if(!itemStack.isEmpty()) {
                tools.add(itemStack);
            }
        }
        return tools;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(this.hoveredResult != -1) {
            swapWithRelease = false;
            ServerboundActionTagPacket.create(ServerboundActionTagPacket.SWAP_TOOL, hoveredResult, button);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}