package dev.meluhdy.scovilleCosmetics.core.hotbar.item

import dev.meluhdy.scovilleCosmetics.core.hotbar.ScovilleHotbarItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ShieldItem(p: Player, color: Char) : ScovilleHotbarItem(p, ItemStack(Material.SHIELD, 1), color, "item.hotbar.shield.title") {

    override fun onClick(p: Player) {}

}