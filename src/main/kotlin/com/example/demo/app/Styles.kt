package com.example.demo.app

import javafx.scene.text.FontWeight
import tornadofx.*
import tornadofx.Stylesheet.Companion.box

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
        button{
            backgroundRadius = multi(box(100.percent))
        }
    }
}