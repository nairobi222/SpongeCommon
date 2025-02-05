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
package org.spongepowered.common.event.tracking.phase.packet.inventory;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.CauseStackManager;
import org.spongepowered.api.event.SpongeEventFactory;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.cause.entity.spawn.SpawnTypes;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.transaction.SlotTransaction;
import org.spongepowered.common.bridge.OwnershipTrackedBridge;
import org.spongepowered.common.event.tracking.phase.packet.PacketConstants;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

public final class DropItemOutsideWindowState extends BasicInventoryPacketState {

    public DropItemOutsideWindowState(int stateid) {
        super(stateid);
    }

    @Override
    public boolean doesCaptureEntityDrops(InventoryPacketContext context) {
        return true;
    }

    @Override
    public void populateContext(EntityPlayerMP playerMP, Packet<?> packet, InventoryPacketContext context) {
        super.populateContext(playerMP, packet, context);
    }

    @Override
    public ClickInventoryEvent createInventoryEvent(EntityPlayerMP playerMP, Container openContainer, Transaction<ItemStackSnapshot> transaction,
            List<SlotTransaction> slotTransactions, List<Entity> capturedEntities, int usedButton, @Nullable Slot slot) {
        try (CauseStackManager.StackFrame frame = Sponge.getCauseStackManager().pushCauseFrame()) {
            frame.addContext(EventContextKeys.SPAWN_TYPE, SpawnTypes.DROPPED_ITEM);

            for (Entity currentEntity : capturedEntities) {
                if (currentEntity instanceof OwnershipTrackedBridge) {
                    ((OwnershipTrackedBridge) currentEntity).tracked$setOwnerReference((Player) playerMP);
                } else {
                    currentEntity.setCreator(playerMP.getUniqueID());
                }
            }
            if (usedButton == PacketConstants.PACKET_BUTTON_PRIMARY_ID) {
                return SpongeEventFactory.createClickInventoryEventDropOutsidePrimary(frame.getCurrentCause(), transaction, capturedEntities,
                        Optional.ofNullable(slot), openContainer, slotTransactions);
            } else {
                return SpongeEventFactory.createClickInventoryEventDropOutsideSecondary(frame.getCurrentCause(), transaction, capturedEntities,
                        Optional.ofNullable(slot), openContainer, slotTransactions);
            }
        }
    }

    @Override
    public boolean ignoresItemPreMerging() {
        return true;
    }
}
