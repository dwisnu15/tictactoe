package com.enigma.dio.tictactoe

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class TBoardActivity : AppCompatActivity() {

    private lateinit var buttons: Array<Array<Button>>

    private lateinit var player1Name: String
    private lateinit var player2Name: String

    private lateinit var player1TotalScore: TextView
    private lateinit var player2TotalScore: TextView

    private lateinit var currentTurn: TextView

    private var player1Turn = true

    private var newGameBtnVisible = false
    private var roundCount = 0
    private var player1Points = 0
    private var player2Points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_t_board)

        //get players name from mainactivity-sent names
        player1Name = intent.getStringExtra(MainActivity.PLAYER_ONE).toString()
        player2Name = intent.getStringExtra(MainActivity.PLAYER_TWO).toString()

        //set whose turn
        currentTurn = findViewById(R.id.playerTurn)
        setTurn(player1Turn)

        //set total score on xml
        player1TotalScore = findViewById(R.id.p1_win_count)
        player2TotalScore = findViewById(R.id.p2_win_count)
        updateScore()

        buttons = Array(3) { row ->
            Array(3) { column ->
                initButton(row, column)

            }
        }

        val btnReset: Button = findViewById(R.id.button_reset)
        btnReset.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Game Reset",
                Toast.LENGTH_SHORT
            ).show()
            resetBoard()
        }

        val newGame : Button = findViewById(R.id.newGame)
        newGame.isVisible = newGameBtnVisible
        newGame.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Starting a new game...",
                Toast.LENGTH_SHORT
            ).show()
            resetBoard()
        }

    }

    //b00 - b22
    private fun initButton(row: Int, column: Int): Button {
        val btn: Button =
            findViewById(resources.getIdentifier("b$row$column", "id", packageName))
        btn.setOnClickListener {
            onBtnClick(btn)
        }
        return btn;
    }

    private fun onBtnClick(btn: Button) {
        if (btn.text != "") {
            return
        }
        if (player1Turn) {
            btn.text = "X"
            btn.isClickable = false
        } else {
            btn.text = "0"
            btn.isClickable = false
        }

        if (checkWin()) {
            if (player1Turn) {
                win(1)
            } else {
                win(2)
            }
        } else if (roundCount == 9) {
            draw()
        } else {
            player1Turn = !player1Turn
            Log.i("Player turn", "$player1Turn")
            setTurn(player1Turn)
        }

        roundCount++
    }

    private fun win(player: Int) {
        if (player == 1) {
            player1Points += 1
        } else {
            player2Points += 1
        }
        Toast.makeText(
            applicationContext,
            "Player $player won",
            Toast.LENGTH_SHORT
        ).show()
        updateScore()
        disableAllButton()
        setNewGameBtnVisibility(true)
    }

    private fun updateScore() {
        player1TotalScore.text = "$player1Name : $player1Points"
        player2TotalScore.text = "$player2Name : $player2Points"
    }

    private fun draw() {
        Toast.makeText(
            applicationContext,
            "Match Draw",
            Toast.LENGTH_SHORT
        ).show()
        disableAllButton()
        setNewGameBtnVisibility(true)
    }

    //
    //
    // [[1, 2, 3], [1, 2, 3], [1, 2, 3]]
    private fun checkWin(): Boolean {
        val fields = Array(3) { r ->
            Array(3) { c ->
                buttons[r][c].text
            }
        }
        //horizontal match
        ///[x][x][x]        [?][?][?]
        ///[?][?][?]    /   [x][x][x] , etc
        ///[?][?][?]        [?][?][?]
        for (i in 0..2) {
            if (
                (fields[i][0] == fields[i][1]) &&
                (fields[i][0] == fields[i][2]) &&
                (fields[i][0] != "")
            ) return true
        }
        //vertical match
        //[x][?][?]
        //[x][?][?]
        //[x][?][?]
        for (i in 0..2) {
            if (
                (fields[0][i] == fields[1][i]) &&
                (fields[0][i] == fields[2][i]) &&
                (fields[0][i] != "")
            ) {
                return true
            }
        }
        //diagonal right match
        //[x][?][?]
        //[?][x][?]
        //[?][?][x]
        if (
            (fields[0][0] == fields[1][1]) &&
            (fields[0][0] == fields[2][2]) &&
            (fields[0][0] != "")
        ) {
            return true
        }
        //diagonal left match
        //[?][?][x]
        //[?][x][?]
        //[x][?][?]
        if (
            (fields[0][2] == fields[1][1]) &&
            (fields[0][2] == fields[2][0]) &&
            (fields[0][2] != "")
        ) return true

        return false
    }

    //for resetting the board text/marks
    fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].text = ""
            }
        }
        roundCount = 0
        player1Turn = true
        setNewGameBtnVisibility(false)
        enableAllButton()
    }

    private fun setTurn(player1turn: Boolean) {
        if (player1turn) {
            currentTurn.text = "$player1Name Turn"
        } else {
            currentTurn.text = "$player2Name Turn"
        }
    }

    private fun enableAllButton() {
        buttons.forEach { row ->
            row.forEach { column ->
                column.isClickable = true

            }
        }
    }

    private fun disableAllButton() {
        buttons.forEach { row ->
            row.forEach { column ->
                column.isClickable = false

            }
        }
    }

    private fun setNewGameBtnVisibility(visible : Boolean) {
        newGameBtnVisible = visible
    }

//    private fun resetScore() {
//        player1Points = 0
//        player2Points = 0
//    }
}