import com.github.gradle.node.npm.proxy.ProxySettings
import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("com.github.node-gradle.node") version "7.0.1"
}

node {
    version.set("18.17.1")
    npmVersion.set("")
    yarnVersion.set("")
    npmInstallCommand.set("install")
    distBaseUrl.set("https://nodejs.org/dist")
    download.set(false)
    workDir.set(file("${project.projectDir}/.cache/nodejs"))
    npmWorkDir.set(file("${project.projectDir}/.cache/npm"))
    yarnWorkDir.set(file("${project.projectDir}/.cache/yarn"))
    nodeProjectDir.set(file("${project.projectDir}"))
    nodeProxySettings.set(ProxySettings.SMART)
}

tasks.npmInstall {
    nodeModulesOutputFilter {
        exclude("notExistingFile")
    }
}

val buildTask = tasks.register<NpmTask>("buildWebapp") {
    args.set(listOf("run", "build"))
    dependsOn(tasks.npmInstall)
    inputs.dir(project.fileTree("src").exclude("**/*.spec.ts"))
    inputs.dir("node_modules")
    outputs.dir("${project.buildDir}/webapp")
}

val startTask = tasks.register<NpmTask>("launchApp") {
    args.set(listOf("run", "dev"))
    dependsOn(tasks.npmInstall)
}

