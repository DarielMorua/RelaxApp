package com.example.relaxapp.views.mainmenu

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainMenuViewModel : ViewModel() {

    var selectedEmoji by mutableStateOf<String?>(null)
        private set

    fun onEmojiSelected(emoji: String) {
        selectedEmoji = emoji
    }
}
