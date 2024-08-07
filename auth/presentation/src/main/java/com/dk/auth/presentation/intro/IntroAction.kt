package com.dk.auth.presentation.intro

sealed interface IntroAction {
    data object OnSignInClicked: IntroAction
    data object OnSignUpClick: IntroAction
}