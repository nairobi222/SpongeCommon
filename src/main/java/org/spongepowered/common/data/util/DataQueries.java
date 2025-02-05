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
package org.spongepowered.common.data.util;

import static org.spongepowered.api.data.DataQuery.of;

import org.spongepowered.api.data.DataQuery;
import org.spongepowered.common.util.Constants;

@SuppressWarnings("DeprecatedIsStillUsed")
public final class DataQueries {

    public static final class GameProfile {

        public static final DataQuery SKIN_UUID = of("SkinUUID");
        // RepresentedPlayerData
        public static final DataQuery GAME_PROFILE_ID = of("Id");
        public static final DataQuery GAME_PROFILE_NAME = of("Name");
    }

    public static final class Block {

        // Blocks
        public static final DataQuery BLOCK_STATE = of("BlockState");
        public static final DataQuery BLOCK_EXTENDED_STATE = of("BlockExtendedState");
        public static final DataQuery BLOCK_TYPE = of("BlockType");
        public static final DataQuery BLOCK_STATE_UNSAFE_META = of("UnsafeMeta");
    }

    public static final class BlockEntity {

        // TileEntities
        public static final DataQuery TILE_TYPE = of("TileType");
        public static final DataQuery BREWING_TIME = of("BrewTime");
        public static final DataQuery LOCK_CODE = of("Lock");
        public static final DataQuery ITEM_CONTENTS = of("Contents");
        public static final DataQuery SLOT = of("SlotId");
        public static final DataQuery SLOT_ITEM = of("Item");
        public static final DataQuery NOTE_ID = of("Note");
        public static final DataQuery LOCKABLE_CONTAINER_CUSTOM_NAME = of("CustomName");
        // TileEntity names
        public static final DataQuery CUSTOM_NAME = of("CustomName");
        public static final DataQuery WORLD = of("world");

        public static final class CommandBlock {

            // Commands
            public static final DataQuery SUCCESS_COUNT = of("SuccessCount");
            public static final DataQuery DOES_TRACK_OUTPUT = of("DoesTrackOutput");
            public static final DataQuery STORED_COMMAND = of("StoredCommand");
            public static final DataQuery TRACKED_OUTPUT = of("TrackedOutput");
        }

        public static final class Beacon {

            // Beacons
            public static final DataQuery PRIMARY = of("primary");
            public static final DataQuery SECONDARY = of("secondary");
        }

        public static final class Banner {

            // Banners
            public static final DataQuery BASE = of("Base");
            public static final DataQuery PATTERNS = of("Patterns");
            // BannerPatterns
            public static final DataQuery SHAPE = of("BannerShapeId");
            public static final DataQuery COLOR = of("DyeColor");
        }

        public static final class Furnace {

            public static final DataQuery BURN_TIME = of("BurnTime");
            public static final DataQuery BURN_TIME_TOTAL = of("BurnTimeTotal");
            public static final DataQuery COOK_TIME = of("CookTime");
            public static final DataQuery COOK_TIME_TOTAL = of("CookTimeTotal");
        }

        public static final class Hopper {

            public static final DataQuery TRANSFER_COOLDOWN = of("TransferCooldown");
        }
    }

    public static final class Entity {

        // Entities
        public static final DataQuery CLASS = of("EntityClass");
        public static final DataQuery UUID = of("EntityUniqueId");
        public static final DataQuery TYPE = of("EntityType");
        public static final DataQuery ROTATION = of("Rotation");
        public static final DataQuery SCALE = of("Scale");
    }

    public static final class User {

        // User
        public static final DataQuery UUID = of("UUID");
        public static final DataQuery NAME = of("Name");
        public static final DataQuery SPAWNS = of("Spawns");
    }

    public static final class ItemStack {

        // ItemStacks
        public static final DataQuery COUNT = of("Count");
        public static final DataQuery TYPE = of("ItemType");
        public static final DataQuery DAMAGE_VALUE = of("UnsafeDamage");
    }

    public static final class Potions {

        // Potions
        public static final DataQuery POTION_TYPE = of("PotionType");
        public static final DataQuery POTION_AMPLIFIER = of("Amplifier");
        public static final DataQuery POTION_SHOWS_PARTICLES = of("ShowsParticles");
        public static final DataQuery POTION_AMBIANCE = of("Ambiance");
        public static final DataQuery POTION_DURATION = of("Duration");
    }

    public static final class TradeOffer {

        // TradeOffers
        public static final DataQuery FIRST_QUERY = of("FirstItem");
        public static final DataQuery SECOND_QUERY = of("SecondItem");
        public static final DataQuery BUYING_QUERY = of("BuyingItem");
        public static final DataQuery EXPERIENCE_QUERY = of("GrantsExperience");
        public static final DataQuery MAX_QUERY = of("MaxUses");
        public static final DataQuery USES_QUERY = of("Uses");
    }

    public static final class FireWorks {

        // Firework Effects
        public static final DataQuery FIREWORK_SHAPE = of("Type");
        public static final DataQuery FIREWORK_COLORS = of("Colors");
        public static final DataQuery FIREWORK_FADE_COLORS = of("Fades");
        public static final DataQuery FIREWORK_TRAILS = of("Trails");
        public static final DataQuery FIREWORK_FLICKERS = of("Flickers");
    }

    public static final class Particles {

        // Particle Effects
        public static final DataQuery PARTICLE_TYPE = of("Type");
        public static final DataQuery PARTICLE_OPTIONS = of("Options");
        public static final DataQuery PARTICLE_OPTION_KEY = of("Option");
        public static final DataQuery PARTICLE_OPTION_VALUE = of("Value");
    }

    public static final class Fluids {

        // Fluids
        public static final DataQuery FLUID_TYPE = of("FluidType");
        public static final DataQuery FLUID_VOLUME = of("FluidVolume");
    }

    public static final class Sponge {

        // General DataQueries
        public static final DataQuery UNSAFE_NBT = of("UnsafeData");
        public static final DataQuery DATA_MANIPULATORS = of("Data");
        @Deprecated public static final DataQuery DATA_CLASS = of(NbtDataUtil.CUSTOM_DATA_CLASS);
        public static final DataQuery DATA_ID = of(NbtDataUtil.MANIPULATOR_ID);
        public static final DataQuery FAILED_SERIALIZED_DATA = of("DataUnableToDeserialize");
        public static final DataQuery INTERNAL_DATA = of(NbtDataUtil.CUSTOM_DATA);
        // Snapshots
        public static final DataQuery SNAPSHOT_WORLD_POSITION = of("Position");
        public static final DataQuery SNAPSHOT_TILE_DATA = of("TileEntityData");

        public static final class PlayerData {

            // SpongePlayerData
            public static final DataQuery PLAYER_DATA_JOIN = of("FirstJoin");
            public static final DataQuery PLAYER_DATA_LAST = of("LastPlayed");
        }

        public static final class VelocityData {

            // Velocity
            public static final DataQuery VELOCITY_X = of("X");
            public static final DataQuery VELOCITY_Y = of("Y");
            public static final DataQuery VELOCITY_Z = of("Z");
        }
    }

    public static final class DataSerializers {

        // Java API Queries for DataSerializers
        public static final DataQuery LOCAL_TIME_HOUR = of("LocalTimeHour");
        public static final DataQuery LOCAL_TIME_MINUTE = of("LocalTimeMinute");
        public static final DataQuery LOCAL_TIME_SECOND = of("LocalTimeSecond");
        public static final DataQuery LOCAL_TIME_NANO = of("LocalTimeNano");
        public static final DataQuery LOCAL_DATE_YEAR = of("LocalDateYear");
        public static final DataQuery LOCAL_DATE_MONTH = of("LocalDateMonth");
        public static final DataQuery LOCAL_DATE_DAY = of("LocalDateDay");
        public static final DataQuery ZONE_TIME_ID = of("ZoneDateTimeId");
        public static final DataQuery X_POS = of("x");
        public static final DataQuery Y_POS = of("y");
        public static final DataQuery Z_POS = of("z");
        public static final DataQuery W_POS = of("w");
    }

    public static final class Anvils {

        // UpdateAnvilEventCost
        public static final DataQuery MATERIALCOST = DataQuery.of("materialcost");
        public static final DataQuery LEVELCOST = DataQuery.of("levelcost");
    }

    private DataQueries() {
    }

    public static final class TileEntityArchetype {

        public static final DataQuery TILE_TYPE = of("TileEntityType");
        public static final DataQuery BLOCK_STATE = of("BlockState");
        public static final DataQuery TILE_DATA = of("TileEntityData");

        private TileEntityArchetype() {
        }
    }

    public static final class Compatibility {

        public static final class Forge {
            public static final DataQuery ROOT = of(Constants.Forge.FORGE_DATA);
        }

    }

    public static final class General {

        public static final DataQuery SPONGE_ROOT = of(NbtDataUtil.SPONGE_DATA);

        public static final DataQuery CUSTOM_MANIPULATOR_LIST = of(NbtDataUtil.CUSTOM_MANIPULATOR_TAG_LIST);
        public static final DataQuery WORLD_CUSTOM_SETTINGS = DataQuery.of("customSettings");
    }

    public static final class EntityArchetype {

        public static final DataQuery ENTITY_TYPE = of("EntityType");
        public static final DataQuery ENTITY_DATA = of("EntityData");

        private EntityArchetype() {
        }

    }
    
    public static final class Schematic {


        public static final DataQuery NAME = of("Name");

        public static final class Versions {
            public static final DataQuery V1_TILE_ENTITY_DATA = of("TileEntities");
            public static final DataQuery V1_TILE_ENTITY_ID = of("id");
        }

        /**
         * The NBT structure of the legacy Schematic format used by MCEdit and WorldEdit etc.
         *
         * It's no longer updated due to the
         */
        public static final class Legacy {

            public static final DataQuery X_POS = of("x");
            public static final DataQuery Y_POS = of("y");
            public static final DataQuery Z_POS = of("z");
            public static final DataQuery MATERIALS = of("Materials");
            public static final DataQuery WE_OFFSET_X = of("WEOffsetX");
            public static final DataQuery WE_OFFSET_Y = of("WEOffsetY");
            public static final DataQuery WE_OFFSET_Z = of("WEOffsetZ");
            public static final DataQuery BLOCKS = of("Blocks");
            public static final DataQuery BLOCK_DATA = of("Data");
            public static final DataQuery ADD_BLOCKS = of("AddBlocks");
            public static final DataQuery TILE_ENTITIES = of("TileEntities");
            public static final DataQuery ENTITIES = of("Entities");
            public static final DataQuery ENTITY_ID = of("id");
        }
        
        public static final DataQuery VERSION = of("Version");
        public static final DataQuery DATA_VERSION = of("DataVersion");
        public static final DataQuery METADATA = of("Metadata");
        public static final DataQuery REQUIRED_MODS = of(org.spongepowered.api.world.schematic.Schematic.METADATA_REQUIRED_MODS);

        public static final DataQuery WIDTH = of("Width");
        public static final DataQuery HEIGHT = of("Height");
        public static final DataQuery LENGTH = of("Length");

        public static final DataQuery OFFSET = of("Offset");
        public static final DataQuery PALETTE = of("Palette");
        public static final DataQuery PALETTE_MAX = of("PaletteMax");
        public static final DataQuery BLOCK_DATA = of("BlockData");
        public static final DataQuery BIOME_DATA = of("BiomeData");

        public static final DataQuery BLOCKENTITY_DATA = of("BlockEntities");
        public static final DataQuery BLOCKENTITY_ID = of("Id");
        public static final DataQuery BLOCKENTITY_POS = of("Pos");

        public static final DataQuery ENTITIES = of("Entities");
        public static final DataQuery ENTITIES_ID = of("Id");
        public static final DataQuery ENTITIES_POS = of("Pos");

        public static final DataQuery BIOME_PALETTE = of("BiomePalette");
        public static final DataQuery BIOME_PALETTE_MAX = of("BiomePaletteMax");

        private Schematic() {
        }
    }
}
