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
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import me.machinemaker.papertweaks.annotations.ModuleInfo;
import me.machinemaker.papertweaks.modules.ModuleBase;
import me.machinemaker.papertweaks.modules.ModuleCommand;
import me.machinemaker.papertweaks.modules.ModuleConfig;
import me.machinemaker.papertweaks.modules.ModuleLifecycle;
import me.machinemaker.papertweaks.modules.ModuleListener;
import me.machinemaker.papertweaks.modules.ModuleRecipe;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

@ModuleInfo(
    name = "Chunk Loader",
    description = "Make a loadstone by using a Nether Star on a lodestone",
    configPath = "chunk-loaders"
)
public final class ChunkLoaderModule extends ModuleBase {

    @Override
    protected Class<? extends ModuleLifecycle> lifecycle() {
        return Lifecycle.class;
    }

    @Override
    protected Collection<Class<? extends ModuleListener>> listeners() {
        return Collections.singleton(ChunkLoaderListener.class);
    }

    static class Lifecycle extends ModuleLifecycle {

        private final Logger logger;

        @Inject
        private Lifecycle(final JavaPlugin plugin, final Set<ModuleCommand> commands, final Set<ModuleListener> listeners, final Set<ModuleConfig> configs, final Set<ModuleRecipe<?>> moduleRecipes, final Logger logger) {
            super(plugin, commands, listeners, configs, moduleRecipes);
            this.logger = logger;
        }

        @Override
        public void onEnable() {
            this.logger.info("Chunk Loader module enabled");
        }

        @Override
        public void onDisable(final boolean isShutdown) {
            this.logger.info("Chunk Loader module disabled");
        }
    }
}
