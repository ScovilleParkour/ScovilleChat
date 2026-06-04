package dev.meluhdy.scovilleCosmetics

import dev.meluhdy.melodia.MelodiaPlugin
import dev.meluhdy.melodia.command.MelodiaCommand
import dev.meluhdy.melodia.manager.MelodiaSavingManager
import dev.meluhdy.melodia.utils.ConsoleLogger
import dev.meluhdy.melodia.utils.LoggingUtils
import dev.meluhdy.melodia.utils.TranslationFolder
import dev.meluhdy.scovilleCosmetics.core.chat.AnnouncementManager
import dev.meluhdy.scovilleCosmetics.core.chat.tag.TagManager
import dev.meluhdy.scovilleCosmetics.core.player.PlayerCosmeticsManager
import dev.meluhdy.scovilleCosmetics.listener.HotbarListener
import dev.meluhdy.scovilleCosmetics.listener.MessageListener
import dev.meluhdy.scovilleCosmetics.listener.TagListener
import org.bukkit.event.Listener
import java.util.Locale

class ScovilleCosmetics : MelodiaPlugin() {

    companion object {
        lateinit var plugin: MelodiaPlugin
    }

    init {
        plugin = this
    }

    override val melodiaCommands: Array<MelodiaCommand> = arrayOf()
    override val resourceFiles: Array<String> = arrayOf(
        "lang/en.properties",
        "lang/de.properties",
        "lang/ja.properties",
        "lang/pl.properties"
    )
    override val listeners: Array<Listener> = arrayOf(
        MessageListener,
        HotbarListener,
        TagListener
    )
    override val translationFolder: TranslationFolder = TranslationFolder("lang", Locale.of("en"))
    override val logger: ConsoleLogger = ConsoleLogger("ScovilleCosmetics", LoggingUtils.ConsoleLevel.DEBUG)
    override val savingManagers: Array<MelodiaSavingManager<*>> = arrayOf(
        PlayerCosmeticsManager,
        TagManager
    )

    override fun onEnable() {
        super.onEnable()

        AnnouncementManager.startTask()
    }

}
