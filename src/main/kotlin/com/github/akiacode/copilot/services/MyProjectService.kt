package com.github.akiacode.copilot.services

import com.github.akiacode.copilot.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
