package dev.meluhdy.scovilleChat.core.modifiers

import dev.meluhdy.scoville.core.course.courses.RankupCourse.Rank
import dev.meluhdy.scoville.core.parkourer.ParkourerManager
import dev.meluhdy.scoville.misc.track.RankTrack
import dev.meluhdy.scovilleChat.core.ChatModifier
import org.bukkit.entity.Player

object RankModifier : ChatModifier(ChatModifiers.RANKUP) {

    fun makeTag(outer: String, inner: String, rank: Rank) = "<$outer>«<$inner>${rank.displayName}</$inner>»</$outer>"

    override fun getString(player: Player): String {
        val parkourer = ParkourerManager.get(player) ?: return ""
        val rank = RankTrack.fromPlayer(parkourer.getPlayer()!!)

        return when (rank) {
            Rank.UNKNOWN -> makeTag("dark_purple", "light_purple", Rank.UNKNOWN)
            Rank.BELL -> makeTag("dark_green", "dark_green", Rank.BELL)
            Rank.PEPPERONCINI -> makeTag("green", "dark_green", Rank.PEPPERONCINI)
            Rank.ANAHEIM -> makeTag("green", "dark_green", Rank.ANAHEIM)
            Rank.POBLANO -> makeTag("green", "green", Rank.POBLANO)
            Rank.GUAJILLO -> makeTag("yellow", "green", Rank.GUAJILLO)
            Rank.JALAPENO -> makeTag("green", "yellow", Rank.JALAPENO)
            Rank.SERRANO -> makeTag("yellow", "yellow", Rank.SERRANO)
            Rank.MANZANO -> makeTag("gold", "yellow", Rank.MANZANO)
            Rank.CAYENNE -> makeTag("yellow", "gold", Rank.CAYENNE)
            Rank.THAI -> makeTag("gold", "gold", Rank.THAI)
            Rank.DATIL -> makeTag("red", "gold", Rank.DATIL)
        } + " "
    }

}