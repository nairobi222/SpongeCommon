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
package org.spongepowered.common.mixin.realtime.mixin;

import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.common.bridge.world.WorldBridge;
import org.spongepowered.common.mixin.realtime.IMixinRealTimeTicking;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase_RealTime extends MixinEntity_RealTime {

    @Shadow public int deathTime;
    @Shadow protected int idleTime;

    @Redirect(method = "onDeathUpdate", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;deathTime:I", opcode = Opcodes.PUTFIELD, ordinal = 0))
    private void adjustForRealTimeDeathTime(EntityLivingBase self, int vanillaNewDeathTime) {
        if (((WorldBridge) this.world).isFake()) {
            this.deathTime = vanillaNewDeathTime;
            return;
        }
        int ticks = (int) ((IMixinRealTimeTicking) self.getEntityWorld()).getRealTimeTicks();
        int newDeathTime = this.deathTime + ticks;
        // At tick 20, XP is dropped and the death animation finishes. The
        // entity is also removed from the world... except in the case of
        // players, which are not removed until they log out or click Respawn.
        // For players, then, let the death time pass 20 to avoid XP
        // multiplication - not just duplication, but *multiplication*.
        if (vanillaNewDeathTime <= 20 && newDeathTime > 20) {
            newDeathTime = 20;
        }
        this.deathTime = newDeathTime;
    }

}
