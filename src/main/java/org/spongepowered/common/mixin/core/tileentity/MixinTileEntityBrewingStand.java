/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.mixin.core.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBrewingStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.common.interfaces.data.IMixinCustomNameable;
import org.spongepowered.common.item.inventory.adapter.InventoryAdapter;
import org.spongepowered.common.item.inventory.adapter.impl.MinecraftInventoryAdapter;
import org.spongepowered.common.item.inventory.adapter.impl.slots.FilteringSlotAdapter;
import org.spongepowered.common.item.inventory.adapter.impl.slots.InputSlotAdapter;
import org.spongepowered.common.item.inventory.lens.Fabric;
import org.spongepowered.common.item.inventory.lens.SlotProvider;
import org.spongepowered.common.item.inventory.lens.impl.ReusableLens;
import org.spongepowered.common.item.inventory.lens.impl.collections.SlotCollection;
import org.spongepowered.common.item.inventory.lens.impl.minecraft.BrewingStandInventoryLens;
import org.spongepowered.common.item.inventory.lens.impl.slots.FilteringSlotLensImpl;
import org.spongepowered.common.item.inventory.lens.impl.slots.InputSlotLensImpl;

@Mixin(TileEntityBrewingStand.class)
public abstract class MixinTileEntityBrewingStand extends MixinTileEntityLockable implements MinecraftInventoryAdapter, IMixinCustomNameable {

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public ReusableLens<?> generateLens(Fabric fabric, InventoryAdapter adapter) {
        return ReusableLens.getLens(BrewingStandInventoryLens.class, this, this::impl$generateBrewingSlotProvider, this::impl$generateBrewingRootLens);
    }

    @SuppressWarnings("unchecked")
    private SlotProvider impl$generateBrewingSlotProvider() {
        return new SlotCollection.Builder().add(5)
                .add(InputSlotAdapter.class, (i) -> new InputSlotLensImpl(i, (s) -> ((TileEntityBrewingStand) (Object) this).isItemValidForSlot(i, (ItemStack) s), t
                        -> ((TileEntityBrewingStand) (Object) this).isItemValidForSlot(i, (ItemStack) org.spongepowered.api.item.inventory.ItemStack.of(t, 1))))
                .add(InputSlotAdapter.class, (i) -> new InputSlotLensImpl(i, (s) -> ((TileEntityBrewingStand) (Object) this).isItemValidForSlot(i, (ItemStack) s), t
                        -> ((TileEntityBrewingStand) (Object) this).isItemValidForSlot(i, (ItemStack) org.spongepowered.api.item.inventory.ItemStack.of(t, 1))))
                .add(FilteringSlotAdapter.class, (i) -> new FilteringSlotLensImpl(i, (s) -> ((TileEntityBrewingStand) (Object) this).isItemValidForSlot(i, (ItemStack) s), t
                        -> ((TileEntityBrewingStand) (Object) this).isItemValidForSlot(i, (ItemStack) org.spongepowered.api.item.inventory.ItemStack.of(t, 1))))
                .add(FilteringSlotAdapter.class, (i) -> new FilteringSlotLensImpl(i, (s) -> ((TileEntityBrewingStand) (Object) this).isItemValidForSlot(i, (ItemStack) s), t
                        -> ((TileEntityBrewingStand) (Object) this).isItemValidForSlot(i, (ItemStack) org.spongepowered.api.item.inventory.ItemStack.of(t, 1))))
                .add(FilteringSlotAdapter.class, (i) -> new FilteringSlotLensImpl(i, (s) -> ((TileEntityBrewingStand) (Object) this).isItemValidForSlot(i, (ItemStack) s), t
                        -> ((TileEntityBrewingStand) (Object) this).isItemValidForSlot(i, (ItemStack) org.spongepowered.api.item.inventory.ItemStack.of(t, 1))))
                .build();
    }

    @SuppressWarnings("unchecked")
    private BrewingStandInventoryLens impl$generateBrewingRootLens(SlotProvider slots) {
        return new BrewingStandInventoryLens(this, slots);
    }

    @Override
    public void setCustomDisplayName(String customName) {
        ((TileEntityBrewingStand) (Object) this).setName(customName);
    }
}
