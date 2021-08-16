package com.github.akiacode.copilot.editor

import com.github.akiacode.copilot.settings.AppSettingsState
import com.google.gson.Gson
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.ui.Messages
import org.jetbrains.annotations.NotNull
import java.net.HttpURLConnection
import java.net.URL

class EditorCopilotAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project = e.getRequiredData(CommonDataKeys.PROJECT)
        val document = editor.document

        val primaryCaret = editor.caretModel.primaryCaret

        WriteCommandAction.runWriteCommandAction(project) {
            val url = URL("https://api.openai.com/v1/engines/davinci-codex/completions")

            val jsonObject = HashMap<String, Any>()
            jsonObject["prompt"] = document.text
            jsonObject["max_tokens"] = 182
            jsonObject["temperature"] = 0
            jsonObject["top_p"] = 1.0
            jsonObject["frequency_penalty"] = 0.0
            jsonObject["presence_penalty"] = 0.0
            jsonObject["stop"] = "#####"

            val gsonObj = Gson()

            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "POST"
                addRequestProperty("Authorization", "Bearer ${AppSettingsState.getInstance().apiKey}")
                addRequestProperty("Content-Type", "application/json")
                doOutput = true
                outputStream.write(gsonObj.toJson(jsonObject).toByteArray())
                outputStream.flush()
                val anytype = HashMap<String, List<Map<String, String>>>()
                val responseJson = gsonObj.fromJson(inputStream.bufferedReader().readText(), anytype.javaClass)

                if (responseJson["choices"] == null || responseJson["choices"]!![0]["text"] == null) {
                    Messages.showErrorDialog(errorStream.toString(), "Copilot: Error Message")
                } else {
                    responseJson["choices"]!![0]["text"]?.let {
                        document.insertString(primaryCaret.offset, it)
                    }
                }
            }
        }

        primaryCaret.removeSelection()
    }

    override fun update(@NotNull e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)

        e.presentation.isEnabledAndVisible = project != null && editor != null
    }
}