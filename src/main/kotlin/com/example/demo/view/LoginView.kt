package com.example.demo.view

import com.example.demo.controller.LoginController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class LoginView : View("Login") {
    val model = ViewModel()
    val username = model.bind { SimpleStringProperty() }
    val password = model.bind { SimpleStringProperty() }
    val loginController: LoginController by inject()

    override val root = vbox {
        setPrefSize(400.0, 600.0)
        style{
            backgroundColor += Color.BLANCHEDALMOND
        }

        fieldset("Enter Credentials Here") {
            hbox {
                alignment  = Pos.TOP_RIGHT
                button("light"){
                    //enter light mode
                }
                button("dark"){
                    //enter dark mode
                }
            }
            hbox {
                imageview{
                    image = Image("/userping.PNG")
                    setPrefSize(100.0, 100.0)
                    alignment = Pos.CENTER
                }
            }
            hbox {
                paddingTop = 10.0
                label("username")
                alignment = Pos.CENTER
                textfield(username).required()
            }
            hbox {
                paddingTop = 10.0
                alignment = Pos.CENTER
                label("password")
                passwordfield(password).required()
            }
            button("Log in") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        loginController.login(username.value, password.value)
                    }
                }
            }
        }
        label(loginController.statusProperty) {
            style {
                paddingTop = 10
                textFill = Color.RED
                fontWeight = FontWeight.BOLD
            }
        }
    }

    override fun onDock() {
        username.value = ""
        password.value = ""
        model.clearDecorators()
    }
}

