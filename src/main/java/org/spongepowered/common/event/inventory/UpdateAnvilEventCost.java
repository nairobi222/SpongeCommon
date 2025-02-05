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
package org.spongepowered.common.event.inventory;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.Queries;
import org.spongepowered.api.item.inventory.AnvilCost;
import org.spongepowered.common.data.util.DataQueries;

public class UpdateAnvilEventCost implements AnvilCost {

    private final int levelCost;
    private final int materialCost;

    public UpdateAnvilEventCost(int levelCost, int materialCost) {
        this.levelCost = levelCost;
        this.materialCost = materialCost;
    }

    public int getLevelCost() {
        return this.levelCost;
    }

    public int getMaterialCost() {
        return this.materialCost;
    }

    public AnvilCost withLevelCost(int levelCost) {
        return new UpdateAnvilEventCost(levelCost, this.materialCost);
    }

    public AnvilCost withMaterialCost(int materialCost) {
        return new UpdateAnvilEventCost(this.levelCost, materialCost);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Override
    public DataContainer toContainer() {
        return DataContainer.createNew().set(Queries.CONTENT_VERSION, this.getContentVersion())
                .set(DataQueries.Anvils.MATERIALCOST, this.getMaterialCost())
                .set(DataQueries.Anvils.LEVELCOST, this.getLevelCost());
    }
}
