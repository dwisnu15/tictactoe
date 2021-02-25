package com.enigma.dio.tictactoe.utils


var board = arrayOf(
    arrayOf(BoardState.BLANK, BoardState.BLANK, BoardState.BLANK),
    arrayOf(BoardState.BLANK, BoardState.BLANK, BoardState.BLANK),
    arrayOf(BoardState.BLANK, BoardState.BLANK, BoardState.BLANK)
)

class PropertyButton(val symbol: String, val status: Boolean)

fun checkBoardWin() : Boolean{
    //horizontal match
    //[x][x][x]
    //[?][?][?]
    //[?][?][?]
    for (i in 0..2) {
        if (
            (board[i][0] == board[i][1]) &&
            (board[i][0] == board[i][2]) &&
            (board[i][0] != BoardState.BLANK)
        ) return true
    }
    //vertical match
    //[x][?][?]
    //[x][?][?]
    //[x][?][?]
    for (i in 0..2) {
        if (
            (board[0][i] == board[1][i]) &&
            (board[0][i] == board[2][i]) &&
            (board[0][i] != BoardState.BLANK)
        ) {
            return true
        }
    }
    //diagonal right match
    //[x][?][?]
    //[?][x][?]
    //[?][?][x]
    if (
        (board[0][0] == board[1][1]) &&
        (board[0][0] == board[2][2]) &&
        (board[0][0] != BoardState.BLANK)
    ) {
        return true
    }
    //diagonal left match
    //[?][?][x]
    //[?][x][?]
    //[x][?][?]
    if (
        (board[0][2] == board[1][1]) &&
        (board[0][2] == board[2][0]) &&
        (board[0][2] != BoardState.BLANK)
    ) return true

    return false
}