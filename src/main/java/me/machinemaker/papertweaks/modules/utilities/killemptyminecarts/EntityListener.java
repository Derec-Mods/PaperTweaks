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

import com.google.inject.Inject;
import me.machinemaker.papertweaks.modules.ModuleListener;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Inspired by Derec-Mods/Unmount (https://github.com/Derec-Mods/Unmount)
 */
class EntityListener implements ModuleListener {

    private final Config config;

    @Inject
    EntityListener(final Config config) {
        this.config = config;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onVehicleExit(final VehicleExitEvent event) {
        if (!this.config.autoBreakOnDismount) return;
        if (!(event.getExited() instanceof Player) || !(event.getVehicle() instanceof Minecart cart)) return;

        Material dropMat = Material.MINECART;
        switch (cart.getType().name()) {
            case "CHEST_MINECART":
            case "MINECART_CHEST":
                dropMat = Material.CHEST_MINECART;
                break;
            case "FURNACE_MINECART":
            case "MINECART_FURNACE":
                dropMat = Material.FURNACE_MINECART;
                break;
            case "HOPPER_MINECART":
            case "MINECART_HOPPER":
                dropMat = Material.HOPPER_MINECART;
                break;
            case "TNT_MINECART":
            case "MINECART_TNT":
                dropMat = Material.TNT_MINECART;
                break;
            case "COMMAND_BLOCK_MINECART":
            case "MINECART_COMMAND":
                dropMat = Material.COMMAND_BLOCK_MINECART;
                break;
            case "SPAWNER_MINECART":
            case "MINECART_MOB_SPAWNER":
                dropMat = Material.SPAWNER_MINECART;
                break;
        }

        cart.getWorld().dropItemNaturally(cart.getLocation(), new ItemStack(dropMat));
        cart.remove();
    }
}
