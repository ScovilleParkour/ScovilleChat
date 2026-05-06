package dev.meluhdy.scovilleChat

import dev.meluhdy.melodia.MelodiaPlugin
import dev.meluhdy.melodia.command.MelodiaCommand
import dev.meluhdy.melodia.manager.MelodiaSavingManager
import dev.meluhdy.melodia.utils.ConsoleLogger
import dev.meluhdy.melodia.utils.LoggingUtils
import dev.meluhdy.melodia.utils.TranslationFolder
import dev.meluhdy.scovilleChat.core.AnnouncementManager
import dev.meluhdy.scovilleChat.core.player.PlayerMessageSettingsManager
import dev.meluhdy.scovilleChat.core.tag.TagManager
import dev.meluhdy.scovilleChat.listener.MessageListener
import org.bukkit.event.Listener
import java.util.Locale

class ScovilleChat : MelodiaPlugin() {

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
        MessageListener
    )
    override val translationFolder: TranslationFolder = TranslationFolder("lang", Locale.of("en"))
    override val logger: ConsoleLogger = ConsoleLogger("ScovilleChat", LoggingUtils.ConsoleLevel.DEBUG)
    override val savingManagers: Array<MelodiaSavingManager<*>> = arrayOf(
        PlayerMessageSettingsManager,
        TagManager
    )

    override fun onEnable() {
        super.onEnable()

        AnnouncementManager.startTask()
    }

}
