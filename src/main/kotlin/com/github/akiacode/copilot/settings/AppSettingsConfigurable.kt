package com.github.akiacode.copilot.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

class AppSettingsConfigurable : Configurable {

    private var mySettingsComponent: AppSettingsComponent = AppSettingsComponent()

    override fun createComponent(): JComponent {
        mySettingsComponent = AppSettingsComponent()

        return mySettingsComponent.getPanel()
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return mySettingsComponent.getPreferredFocusedComponent()
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.getInstance()
        return mySettingsComponent.getApiKey() != settings.apiKey
    }

    override fun apply() {
        val settings = AppSettingsState.getInstance()
        settings.apiKey = mySettingsComponent.getApiKey()
    }

    override fun reset() {
        val settings = AppSettingsState.getInstance()
        mySettingsComponent.setApiKey(settings.apiKey)
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    override fun getDisplayName(): String {
        return "Copilot: OpenAI API KEY"
    }
}