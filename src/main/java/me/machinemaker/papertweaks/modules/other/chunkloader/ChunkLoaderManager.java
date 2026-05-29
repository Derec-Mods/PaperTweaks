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
import org.bukkit.Location;
import org.bukkit.Sound;

@Singleton
public final class ChunkLoaderManager {

    private final Set<Location> chunkLoaders = new HashSet<>();

    public boolean isChunkLoader(Location location) {
        return this.chunkLoaders.contains(location);
    }

    public void addChunkLoader(Location location) {
        this.chunkLoaders.add(location);
        location.getChunk().setForceLoaded(true);
        location.getWorld().playSound(location, Sound.BLOCK_CONDUIT_ACTIVATE, 1.0f, 1.0f);
    }

    public void removeChunkLoader(Location location) {
        this.chunkLoaders.remove(location);
        location.getChunk().setForceLoaded(false);
        location.getWorld().playSound(location, Sound.BLOCK_CONDUIT_DEACTIVATE, 1.0f, 1.0f);
    }

    public Set<Location> getChunkLoaders() {
        return Collections.unmodifiableSet(this.chunkLoaders);
    }
}
