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
package org.spongepowered.common.mixin.core.entity.ai;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityEnderman;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.SpongeEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.common.SpongeImpl;
import org.spongepowered.common.event.ShouldFire;
import org.spongepowered.common.interfaces.entity.IMixinGriefer;

import java.util.Collections;

import javax.annotation.Nullable;

@Mixin(EntityEnderman.AIPlaceBlock.class)
public abstract class MixinEntityEndermanAIPlaceBlock extends EntityAIBase {

    @Shadow @Final private EntityEnderman enderman;

    /**
     * @author gabizou - April 13th, 2018
     *  @reason - Due to Forge's changes, there's no clear redirect or injection
     *  point where Sponge can add the griefer checks. The original redirect aimed
     *  at the gamerule check, but this can suffice for now.
     * @author gabizou - July 26th, 2018
     * @reason Adds sanity check for calling a change block event pre
     *
     * @param entityEnderman
     * @return
     */
    @Redirect(
        method = "shouldExecute",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/monster/EntityEnderman;getHeldBlockState()Lnet/minecraft/block/state/IBlockState;"
        )
    )
    @Nullable
    private IBlockState onCanGrief(EntityEnderman entityEnderman) {
        final IBlockState heldBlockState = entityEnderman.getHeldBlockState();
        if (ShouldFire.CHANGE_BLOCK_EVENT_PRE) {
            if (SpongeImpl.postEvent(SpongeEventFactory.createChangeBlockEventPre(Sponge.getCauseStackManager().getCurrentCause(), Collections.emptyList()))) {
                return null;
            }
        }
        return ((IMixinGriefer) this.enderman).canGrief() ? heldBlockState : null;
    }
}
