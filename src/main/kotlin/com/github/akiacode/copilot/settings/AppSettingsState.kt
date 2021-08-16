package com.github.akiacode.copilot.config

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage


@State(
        name = "com.github.akiacode.copilot.AppSettingsState",
        storages = [Storage("CopilotConfigPlugin.xml")]
)
class AppSettingsState : PersistentStateComponent<AppSettingsState> {
    override fun getState(): AppSettingsState? {
        TODO("Not yet implemented")
    }

    override fun loadState(state: AppSettingsState) {
        TODO("Not yet implemented")
    }
}