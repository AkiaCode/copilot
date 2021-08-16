package com.github.akiacode.copilot.config

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class AppSettingsConfigurable : Configurable {
    override fun createComponent(): JComponent? {
        TODO("Not yet implemented")
    }

    override fun isModified(): Boolean {
        TODO("Not yet implemented")
    }

    override fun apply() {
        TODO("Not yet implemented")
    }

    override fun getDisplayName(): String {
        return "Copilot: OpenAI API KEY"
    }
}