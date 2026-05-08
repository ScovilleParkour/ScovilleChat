package dev.meluhdy.scovilleCosmetics.listener

import dev.meluhdy.melodia.utils.ItemUtils
import dev.meluhdy.scovilleCosmetics.core.hotbar.item.CheckpointItem
import dev.meluhdy.scovilleCosmetics.core.hotbar.item.MenuItem
import dev.meluhdy.scovilleCosmetics.core.hotbar.item.OptionsItem
import dev.meluhdy.scovilleCosmetics.core.hotbar.item.ShieldItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.mvplugins.multiverse.core.event.MVTeleportDestinationEvent

object HotbarListener : Listener {

    fun updateHotbar(p: Player) {
        // TODO: Add customization

        p.inventory.setItem(1, CheckpointItem(p, ItemStack(Material.MAGMA_CREAM), 'a'))
        p.inventory.setItem(4, MenuItem(p, ItemStack(Material.NETHER_STAR), 'f'))
        p.inventory.setItem(7, OptionsItem(p, ItemUtils.createSkull(p.uniqueId), '7'))
        p.inventory.setItem(8, ShieldItem(p, '6'))
    }

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        updateHotbar(e.player)
    }

    @EventHandler
    fun onWorld(e: MVTeleportDestinationEvent) {
        if (e.teleportee !is Player) return
        val dest = e.destination.getLocation(e.teleportee)
        if (dest.isEmpty || e.from.world.uid != dest.get().world.uid) {
            this.updateHotbar(e.teleportee as Player)
        }
    }

}