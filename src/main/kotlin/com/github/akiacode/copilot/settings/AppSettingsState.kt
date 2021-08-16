package com.github.akiacode.copilot.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import com.sun.istack.NotNull


@State(
        name = "com.github.akiacode.copilot.AppSettingsState",
        storages = [Storage("CopilotConfigPlugin.xml")]
)
class AppSettingsState : PersistentStateComponent<AppSettingsState> {

    var apiKey: String = "Nope"

    override fun getState(): AppSettingsState? {
        return this
    }

    override fun loadState(@NotNull stats: AppSettingsState) {
        XmlSerializerUtil.copyBean(stats, this)
    }


    companion object {
        @JvmStatic
        fun getInstance(): AppSettingsState {
            return ApplicationManager.getApplication().getService(AppSettingsState::class.java)
        }
    }
}