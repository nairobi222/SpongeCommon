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
package org.spongepowered.common.event.tracking.phase.plugin;

import org.spongepowered.common.event.tracking.IPhaseState;
import org.spongepowered.common.event.tracking.phase.TrackingPhase;

public final class PluginPhase extends TrackingPhase {

    public static final class State {

        public static final IPhaseState<BasicPluginContext> BLOCK_WORKER = new BlockWorkerPhaseState();
        public static final IPhaseState<BasicPluginContext> CUSTOM_SPAWN = new BasicPluginState();
        public static final IPhaseState<BasicPluginContext> SCHEDULED_TASK = new ScheduledTaskPhaseState();
        public static final IPhaseState<BasicPluginContext> TELEPORT = new BasicPluginState();

        private State() {
        }
    }

    public static final class Listener {

        public static final IPhaseState<EventListenerPhaseContext> GENERAL_LISTENER = new EventListenerPhaseState();
        public static final IPhaseState<WorldTickContext> PRE_WORLD_TICK_LISTENER = new WorldTickPhaseState("PreWorldTickState");
        public static final IPhaseState<WorldTickContext> POST_WORLD_TICK_LISTENER = new WorldTickPhaseState("PostWorldTickState");
        public static final IPhaseState<ServerTickContext> PRE_SERVER_TICK_LISTENER = new ServerTickPhaseState("PreServerTickState");
        public static final IPhaseState<ServerTickContext> POST_SERVER_TICK_LISTENER = new ServerTickPhaseState("PostServerTickState");
        public static final IPhaseState<EventListenerPhaseContext> EVENT_BUS = new EventListenerPhaseState();

        private Listener() {
        }

    }

    public static PluginPhase getInstance() {
        return Holder.INSTANCE;
    }

    private PluginPhase() {
    }

    private static final class Holder {

        static final PluginPhase INSTANCE = new PluginPhase();
    }

}
