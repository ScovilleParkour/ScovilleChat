package dev.meluhdy.scovilleCosmetics.listener

import dev.meluhdy.melodia.utils.TextUtils
import dev.meluhdy.scoville.core.course.courses.UserCourse
import dev.meluhdy.scoville.event.event.PlateEvent
import dev.meluhdy.scoville.misc.track.RankTrack.isPermission
import dev.meluhdy.scoville.misc.track.RankTrack.setPermission
import dev.meluhdy.scovilleCosmetics.ScovilleCosmetics
import dev.meluhdy.scovilleCosmetics.core.chat.tag.ChatTag
import dev.meluhdy.scovilleCosmetics.core.chat.tag.TagManager
import dev.meluhdy.scovilleCosmetics.misc.getScovilleTag
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.block.Block
import org.bukkit.block.Sign
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

object TagListener : Listener {

    @EventHandler
    fun onTagSign(e: PlayerInteractEvent) {
        val player = e.player
        val block = e.clickedBlock ?: return
        if (e.action != Action.RIGHT_CLICK_BLOCK) return
        if (block.state !is Sign) return

        player.redeemTag(block)
    }

    @EventHandler
    fun onTagPlate(e: PlayerInteractEvent) {
        val player = e.player
        val block = e.clickedBlock ?: return
        if (e.action != Action.PHYSICAL) return
        if (block.type != Material.HEAVY_WEIGHTED_PRESSURE_PLATE) return

        player.redeemTag(block)
    }

    @EventHandler
    fun onCourseTag(e: PlateEvent) {
        val player = e.player
        val course = e.course
        if (course !is UserCourse || course.tag == null) return
        val tag = course.tag?.let { TagManager.get(it) } ?: return

        player.redeemTag(tag)
    }

    private fun Player.redeemTag(block: Block) {
        this.redeemTag(block.getScovilleTag() ?: return)
    }

    private fun Player.redeemTag(tag: ChatTag) {
        if (this.isPermission(tag.permission)) return

        this.setPermission(tag.permission)
        TextUtils.broadcastChat(ScovilleCosmetics.plugin, "chat.tag.unlock", this.name, tag.tag)
        this.playSound(this.location, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f)
    }

}