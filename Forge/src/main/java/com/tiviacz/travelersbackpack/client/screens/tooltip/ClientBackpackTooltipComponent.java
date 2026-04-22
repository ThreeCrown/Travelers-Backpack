package com.tiviacz.travelersbackpack.client.screens.tooltip;

import com.tiviacz.travelersbackpack.util.KeyHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class ClientBackpackTooltipComponent implements ClientTooltipComponent {
    private final BackpackTooltipComponent component;

    public ClientBackpackTooltipComponent(BackpackTooltipComponent component) {
        this.component = component;
    }

    public boolean show() {
        return KeyHelper.isCtrlPressed() || component.isHoveredWithItem();
    }

    @Override
    public int getHeight() {
        int height = 0;

        if(show()) {
            if(!component.storage.isEmpty()) {
                height += 10; //Text
                height += (int)(Math.ceil((float)component.storage.size() / 9) * 18);
            }

            if(!component.tools.isEmpty()) {
                height += 10; //Text
                height += 18;
            }
        }
        return height;
    }

    @Override
    public int getWidth(Font font) {
        int width = 0;

        if(show()) {
            if(!component.storage.isEmpty()) {
                width += Math.min(component.storage.size(), 9) * 18 + Math.min(component.storage.size(), 9) * 2;
            }
        }
        return width;
    }

    @Override
    public void renderText(Font pFont, int pMouseX, int pMouseY, Matrix4f pMatrix, MultiBufferSource.BufferSource pBufferSource) {
        if(show()) {
            int yOffset = 0;

            if(!component.storage.isEmpty()) {
                pFont.drawInBatch(Component.translatable("screen.travelersbackpack.inventory"), (float)pMouseX, (float)pMouseY + yOffset, ChatFormatting.YELLOW.getColor(), true, pMatrix, pBufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
                yOffset += 10;
                yOffset += (int)(Math.ceil((float)component.storage.size() / 9) * 18);
            }

            if(!component.tools.isEmpty()) {
                pFont.drawInBatch(Component.translatable("screen.travelersbackpack.tools"), (float)pMouseX, (float)pMouseY + yOffset, ChatFormatting.YELLOW.getColor(), true, pMatrix, pBufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
                yOffset += 10;
                yOffset += 18;
            }
        }
    }

    @Override
    public void renderImage(Font pFont, int pX, int pY, GuiGraphics pGuiGraphics) {
        int yOffset = 0;

        if(show()) {
            boolean flag = false;

            if(!component.storage.isEmpty()) {
                yOffset += 10; //Text

                int j = 0;
                if(flag) yOffset += 18;
                flag = true;
                boolean nextRow = false;

                for(int i = 0; i < component.storage.size(); i++) {
                    if(nextRow) {
                        yOffset += 18;
                        nextRow = false;
                    }
                    renderItem(component.storage.get(i), pX + j * 2 + j * 18, pY + yOffset, pFont, pGuiGraphics);

                    if(j < 8) {
                        j++;
                    } else {
                        j = 0;
                        nextRow = true;
                    }
                }
            }

            if(!component.tools.isEmpty()) {
                yOffset += 10; //Text
                if(flag) yOffset += 18;

                for(int i = 0; i < component.tools.size(); i++) {
                    renderItem(component.tools.get(i), pX + (i * 18), pY + yOffset, pFont, pGuiGraphics);
                }
            }
        }
    }

    private void renderItem(ItemStack stack, int pX, int pY, Font pFont, GuiGraphics guiGraphics) {
        guiGraphics.renderFakeItem(stack, pX, pY);
        guiGraphics.renderItemDecorations(pFont, stack, pX, pY);
    }
}