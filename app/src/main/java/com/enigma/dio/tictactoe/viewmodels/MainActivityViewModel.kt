package com.enigma.dio.tictactoe.viewmodels

import androidx.lifecycle.ViewModel


class MainActivityViewModel : ViewModel() {

    var player1Name : String? = null
    var player2Name : String? = null

    fun setPlayers(p1 : String, p2: String) {
        player1Name = p1
        player2Name = p2
    }

}