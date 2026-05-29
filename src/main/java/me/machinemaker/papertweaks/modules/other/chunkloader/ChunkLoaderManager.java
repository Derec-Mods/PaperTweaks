/*
 * GNU General Public License v3
 *
 * PaperTweaks, a performant replacement for the VanillaTweaks datapacks.
 *
 * Copyright (C) 2021-2026 Machine_Maker
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package me.machinemaker.papertweaks.modules.other.chunkloader;

import com.google.inject.Singleton;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import me.machinemaker.papertweaks.pdc.DataTypes;
import me.machinemaker.papertweaks.utils.Keys;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;

@Singleton
public final class ChunkLoaderManager {

    private static final NamespacedKey CHUNK_LOADER_KEY = Keys.key("chunk-loader");
    private final Set<Location> chunkLoaders = new HashSet<>();

    public boolean isChunkLoader(Location location) {
        if (this.chunkLoaders.contains(location)) {
            return true;
        }

        Block block = location.getBlock();
        if (this.hasChunkLoaderMarker(block)) {
            this.chunkLoaders.add(location);
            return true;
        }

        return false;
    }

    public void addChunkLoader(Location location) {
        this.chunkLoaders.add(location);
        this.setChunkLoaderMarker(location.getBlock(), true);
        location.getChunk().setForceLoaded(true);
        location.getWorld().playSound(location, Sound.BLOCK_CONDUIT_ACTIVATE, 1.0f, 1.0f);
    }

    public void removeChunkLoader(Location location) {
        this.chunkLoaders.remove(location);
        this.setChunkLoaderMarker(location.getBlock(), false);
        location.getChunk().setForceLoaded(false);
        location.getWorld().playSound(location, Sound.BLOCK_CONDUIT_DEACTIVATE, 1.0f, 1.0f);
    }

    public Set<Location> getChunkLoaders() {
        return Collections.unmodifiableSet(this.chunkLoaders);
    }

    private boolean hasChunkLoaderMarker(Block block) {
        if (block.getType() != Material.LODESTONE) {
            return false;
        }

        BlockState state = block.getState();
        if (!(state instanceof TileState tileState)) {
            return false;
        }

        return tileState.getPersistentDataContainer().has(CHUNK_LOADER_KEY, DataTypes.BOOLEAN);
    }

    private void setChunkLoaderMarker(Block block, boolean enabled) {
        if (block.getType() != Material.LODESTONE) {
            return;
        }

        BlockState state = block.getState();
        if (!(state instanceof TileState tileState)) {
            return;
        }

        if (enabled) {
            tileState.getPersistentDataContainer().set(CHUNK_LOADER_KEY, DataTypes.BOOLEAN, true);
        } else {
            tileState.getPersistentDataContainer().remove(CHUNK_LOADER_KEY);
        }

        tileState.update(true, false);
    }
}
