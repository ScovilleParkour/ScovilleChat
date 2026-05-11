package dev.meluhdy.scovilleCosmetics.core.hotbar.item

import dev.meluhdy.melodia.utils.TextUtils
import dev.meluhdy.scoville.Scoville
import dev.meluhdy.scoville.core.parkourer.ParkourerManager
import dev.meluhdy.scovilleCosmetics.core.hotbar.ScovilleHotbarItem
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CheckpointItem(p: Player, item: ItemStack, color: Char) : ScovilleHotbarItem(p, item, color, "item.hotbar.checkpoint.title") {

    override fun onClick(p: Player) {
        val parkourer = ParkourerManager.get(p) ?: return
        val course = parkourer.getPlayingCourse()
        if (course == null) {
            p.sendMessage(TextUtils.translate(Scoville.plugin, "chat.checkpoint.not_playing", p.locale()))
            return
        }
        parkourer.gotoCheckpoint(course)
    }

}