package dev.meluhdy.scovilleCosmetics.core.hotbar.item

import dev.meluhdy.scovilleCosmetics.core.hotbar.ScovilleHotbarItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class OptionsItem(p: Player, item: ItemStack, color: Char) : ScovilleHotbarItem(p, item, color, "item.hotbar.options.title") {

    override fun onClick(p: Player) {
        p.sendMessage("Options")
    }

}