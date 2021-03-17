package com.example.demo.app

import com.example.demo.view.LoginView
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.*
import kotlin.reflect.KClass

class MyApp: App(LoginView::class, Styles::class){
    val themeController: ThemeController by inject()

    override fun start(stage: Stage) {
        super.start(stage)
        // Make sure we initialize the theme selection system on start
        themeController.start()
    }
}
class ThemeController : Controller() {
    // List of available themes
    val themes = SimpleListProperty<KClass<out Stylesheet>>(listOf(LightTheme::class, DarkTheme::class).observable())

    // Property holding the active theme
    val activeThemeProperty = SimpleObjectProperty<KClass<out Stylesheet>>()
    var activeTheme by activeThemeProperty

    fun start() {
        // Remove old theme, add new theme on change
        activeThemeProperty.addListener { _, oldTheme, newTheme ->
            oldTheme?.let { removeStylesheet(it) }
            newTheme?.let { importStylesheet(it) }
        }

        // Activate the first theme, triggering the listener above
        activeTheme = themes.first()
    }
}

// Two themes for completeness
class DarkTheme : Stylesheet() {
    init {
        root {
            backgroundColor += Color.rgb(37, 38, 36)
        }
    }
}

class LightTheme : Stylesheet() {
    init {
        root {
            backgroundColor += Color.rgb(241, 245, 237)
        }
    }
}