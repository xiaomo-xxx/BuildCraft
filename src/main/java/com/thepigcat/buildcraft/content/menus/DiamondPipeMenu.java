package com.thepigcat.buildcraft.content.menus;

import com.thepigcat.buildcraft.content.blockentities.DiamondItemPipeBE;
import com.thepigcat.buildcraft.registries.BCMenuTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

/**
 * Diamond pipe filter GUI.
 * 6 rows × 9 columns = 54 filter slots (one row per direction)
 * + player inventory
 */
public class DiamondPipeMenu extends AbstractContainerMenu {
    public final DiamondItemPipeBE blockEntity;
    private static final int SLOTS_PER_SIDE = DiamondItemPipeBE.SLOTS_PER_SIDE;

    public DiamondPipeMenu(int containerId, @NotNull Inventory inv, @NotNull DiamondItemPipeBE blockEntity) {
        super(BCMenuTypes.DIAMOND_PIPE.get(), containerId);
        this.blockEntity = blockEntity;

        // 6 rows of 9 filter slots (one row per direction: DOWN, UP, NORTH, SOUTH, WEST, EAST)
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < SLOTS_PER_SIDE; i++) {
                int index = side * SLOTS_PER_SIDE + i;
                // Position: 8 + i*18 horizontally, 18 + side*18 vertically
                addSlot(new SlotItemHandler(blockEntity.getFilterHandler(), index, 8 + i * 18, 18 + side * 18));
            }
        }

        // Player inventory (3 rows)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 140 + row * 18));
            }
        }

        // Player hotbar
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inv, col, 8 + col * 18, 198));
        }
    }

    public DiamondPipeMenu(int containerId, @NotNull Inventory inv, @NotNull RegistryFriendlyByteBuf buf) {
        this(containerId, inv, (DiamondItemPipeBE) inv.player.level().getBlockEntity(buf.readBlockPos()));
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        // Shift-click from player inventory into filter slots
        if (index >= 54) { // Player inventory slot
            ItemStack stack = getSlot(index).getItem();
            if (!stack.isEmpty()) {
                // Try to place into empty filter slot
                for (int side = 0; side < 6; side++) {
                    int offset = side * SLOTS_PER_SIDE;
                    for (int i = 0; i < SLOTS_PER_SIDE; i++) {
                        Slot filterSlot = getSlot(offset + i);
                        if (filterSlot.getItem().isEmpty()) {
                            filterSlot.set(stack.copy());
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }
        } else {
            // From filter slot back to player inventory
            getSlot(index).set(ItemStack.EMPTY);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
