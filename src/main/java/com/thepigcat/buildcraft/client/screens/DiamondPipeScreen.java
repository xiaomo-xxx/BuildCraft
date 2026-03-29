package com.thepigcat.buildcraft.client.screens;

import com.thepigcat.buildcraft.BuildcraftLegacy;
import com.thepigcat.buildcraft.content.blockentities.DiamondItemPipeBE;
import com.thepigcat.buildcraft.content.menus.DiamondPipeMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;

/**
 * Diamond pipe filter GUI screen.
 * Shows 6 rows of 9 filter slots, each row labeled with direction + color.
 */
public class DiamondPipeScreen extends AbstractContainerScreen<DiamondPipeMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            BuildcraftLegacy.MODID, "textures/gui/diamond_pipe.png");

    // Direction colors matching Minecraft dye colors
    private static final int[] SIDE_COLORS = {
            0xFFFFFF,  // DOWN  - White
            0xFF6600,  // UP    - Orange
            0xCC00CC,  // NORTH - Magenta
            0x66CCFF,  // SOUTH - Light Blue
            0xFFFF00,  // WEST  - Yellow
            0x00FF00,  // EAST  - Lime
    };

    private static final String[] SIDE_NAMES = {"↓", "↑", "N", "S", "W", "E"};

    public DiamondPipeScreen(DiamondPipeMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 220;
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelY = 4;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Draw background
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        // Draw colored indicators for each side row
        for (int side = 0; side < 6; side++) {
            int rowY = y + 18 + side * 18;
            // Small colored bar on the left
            guiGraphics.fill(x + 1, rowY, x + 3, rowY + 16, 0xFF000000 | SIDE_COLORS[side]);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderLabels(guiGraphics, mouseX, mouseY);

        // Draw direction labels
        for (int side = 0; side < 6; side++) {
            int rowY = 18 + side * 18;
            String label = Direction.values()[side].name().substring(0, 1) + " " + SIDE_NAMES[side];
            guiGraphics.drawString(font, label, 176 - font.width(label) - 2, rowY + 4, SIDE_COLORS[side], false);
        }
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
