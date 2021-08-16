package com.github.akiacode.copilot.settings

import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import com.sun.istack.NotNull
import javax.swing.JComponent
import javax.swing.JPanel


class AppSettingsComponent {

    private var myMainPanel: JPanel = FormBuilder.createFormBuilder().panel
    private val apiKey: JBTextField = JBTextField()

     init {
        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(JBLabel("Enter OpenAI Key (Codex): "), apiKey, 1, false)
                .addComponentFillVertically(JPanel(), 0)
                .panel
    }

    fun getPanel(): JPanel {
        return myMainPanel
    }
    @NotNull
    fun getApiKey(): String {
        return apiKey.text
    }
    fun setApiKey(@NotNull newtest: String) {
        apiKey.text = newtest
    }

    fun getPreferredFocusedComponent(): JComponent {
        return apiKey
    }
}