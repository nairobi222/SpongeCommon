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
package org.spongepowered.common.mixin.api.minecraft.entity.effect;

import com.google.common.collect.Lists;
import net.minecraft.entity.effect.EntityLightningBolt;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.manipulator.mutable.entity.ExpirableData;
import org.spongepowered.api.data.value.mutable.MutableBoundedValue;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.weather.Lightning;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.data.manipulator.mutable.entity.SpongeExpirableData;
import org.spongepowered.common.data.value.SpongeValueFactory;

import java.util.List;

@Mixin(EntityLightningBolt.class)
public abstract class MixinEntityLightningBolt_API extends MixinEntityWeatherEffect_API implements Lightning {

    private final List<Entity> spongeAPI$struckEntities = Lists.newArrayList();
    private final List<Transaction<BlockSnapshot>> spongeAPI$struckBlocks = Lists.newArrayList();
    private boolean spongeAPI$effect = false;

    @Shadow private int lightningState;

    @Override
    public boolean isEffect() {
        return this.spongeAPI$effect;
    }

    @Override
    public void setEffect(boolean effect) {
        this.spongeAPI$effect = effect;
        if (effect) {
            this.spongeAPI$struckBlocks.clear();
            this.spongeAPI$struckEntities.clear();
        }
    }

    @Override
    public ExpirableData getExpiringData() {
        return new SpongeExpirableData(this.lightningState, 2);
    }

    @Override
    public MutableBoundedValue<Integer> expireTicks() {
        return SpongeValueFactory.boundedBuilder(Keys.EXPIRATION_TICKS)
                .minimum((int) Short.MIN_VALUE)
                .maximum(2)
                .defaultValue(2)
                .actualValue(this.lightningState)
                .build();
    }

    @Override
    public void spongeApi$supplyVanillaManipulators(List<? super DataManipulator<?, ?>> manipulators) {
        super.spongeApi$supplyVanillaManipulators(manipulators);
        manipulators.add(getExpiringData());
    }
}
