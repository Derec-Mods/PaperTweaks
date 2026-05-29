/*
 * GNU General Public License v3
 *
 * PaperTweaks, a performant replacement for the VanillaTweaks datapacks.
 *
 * Copyright (C) 2021-2025 Machine_Maker
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

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.machinemaker.papertweaks.modules.ModuleLifecycle;
import org.apache.logging.log4j.Logger;

@Singleton
public final class ChunkLoaderLifecycle implements ModuleLifecycle {

    private final Logger logger;

    @Inject
    private ChunkLoaderLifecycle(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public void onEnable() {
        this.logger.info("Chunk Loader module enabled");
    }

    @Override
    public void onDisable() {
        this.logger.info("Chunk Loader module disabled");
    }
}
