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
package me.machinemaker.papertweaks.modules.utilities.killemptyminecarts;

import org.incendo.cloud.Command;
import me.machinemaker.papertweaks.cloud.dispatchers.CommandDispatcher;
import me.machinemaker.papertweaks.modules.ModuleCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Minecart;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.Component.translatable;
import static net.kyori.adventure.text.format.NamedTextColor.RED;
import static net.kyori.adventure.text.format.NamedTextColor.YELLOW;

/**
 * Inspired by Derec-Mods/Unmount (https://github.com/Derec-Mods/Unmount)
 */
@ModuleCommand.Info(value = "killcarts", descriptionKey = "modules.kill-empty-minecarts.commands.root", help = false, infoOnRoot = false)
class Commands extends ModuleCommand {

    @Override
    protected void registerCommands() {
        final Command.Builder<CommandDispatcher> builder = this.builder();

        this.register(builder
            .permission(this.modulePermission("vanillatweaks.killcarts"))
            .handler(this.sync(context -> {
                int count = 0;
                for (final World world : Bukkit.getWorlds()) {
                    for (final Minecart cart : world.getEntitiesByClass(Minecart.class)) {
                        if (cart.getPassengers().isEmpty()) {
                            count++;
                            cart.remove();
                        }
                    }
                }
                context.sender().sendMessage(translatable("modules.kill-empty-minecarts.removed", count > 0 ? YELLOW : RED, text(count)));
            }))
        );
    }
}
