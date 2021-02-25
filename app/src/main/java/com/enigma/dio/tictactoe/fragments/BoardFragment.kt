package com.enigma.dio.tictactoe.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.enigma.dio.tictactoe.R
import com.enigma.dio.tictactoe.databinding.FragmentBoardBinding
import com.enigma.dio.tictactoe.databinding.FragmentRegistrationBinding
import com.enigma.dio.tictactoe.utils.BoardState
import com.enigma.dio.tictactoe.utils.PropertyButton
import com.enigma.dio.tictactoe.utils.board
import com.enigma.dio.tictactoe.utils.checkBoardWin
import com.enigma.dio.tictactoe.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_board.*


class BoardFragment : Fragment() {

    private lateinit var sharedViewModel: MainActivityViewModel
    private lateinit var binding: FragmentBoardBinding

    //the tictactoe board's button
    private lateinit var buttons: Array<Array<Button>>

    //player's name
    private lateinit var player1Name: String
    private lateinit var player2Name: String

    private var player1Turn = true

    private var roundCount = 0
    private var player1Points = 0
    private var player2Points = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentBoardBinding.inflate(layoutInflater)
        binding.apply {
            player1Name = sharedViewModel.player1Name.toString()
            player2Name = sharedViewModel.player2Name.toString()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //updateScore()
        //set button onclick
         buttons = Array(3) { row ->
            Array(3) { column ->
                initButton(row, column)
            }
        }
        //hide the game status
        gameStatus.isVisible = false

        //set the players score textview
        updateScore()

        //set current player turn
        setTurnText()
        //set the reset button onclick
        button_reset.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Game Reset",
                Toast.LENGTH_SHORT
            ).show()
            resetBoard()
        }
    }

    private fun initButton(row: Int, column: Int): Button {
        val btn: Button = requireView().findViewById(
            resources.getIdentifier(
                "b$row$column", "id",
                requireActivity().packageName
            )
        )
        btn.setOnClickListener {
            handleClickBoard(it, row, column)
        }
        return btn
    }

    private fun handleClickBoard(view: View, row: Int, column: Int) {
        val btn = view as Button
        writeBoard(btn, row, column)
    }

    private fun writeBoard(btn: Button, row: Int, column: Int) {
        btn.setClickedButtonProp(row, column)
        roundCount++
        if (checkBoardWin()) {
            showWinner(player1Turn)
        } else if (roundCount == 9) {
            draw()
        } else {
            switchPlayer()
        }
        Log.d("Round count", "$roundCount")
    }

    private fun switchPlayer() {
        player1Turn = !player1Turn
        setTurnText()
    }

    private fun setTurnText() {
        if (player1Turn) {
            playerTurn.text = "$player1Name Turn"
        } else {
            playerTurn.text = "$player2Name Turn"
        }
    }

    private fun draw() {
        gameStatus.text = "It's a draw!"
        gameStatus.isVisible = true
    }

    //
    private fun showWinner(player1win: Boolean) {
        if (player1win) {
            player1Points++
            gameStatus.text = "$player1Name Wins"
        } else {
            player2Points++
            gameStatus.text = "$player2Name Wins"
        }
        gameStatus.isVisible = true
        updateScore()
    }

    //update players score textview
    private fun updateScore() {
        p1_win_count.text = "$player1Name : $player1Points"
        p2_win_count.text = "$player2Name : $player2Points"
    }

    //ext function for board's cell button
    private fun Button.setClickedButtonProp(row: Int, column: Int) {
        if (player1Turn) {
            this.text = BoardState.X.toString()
            board[row][column] = BoardState.X
        } else {
            this.text = BoardState.O.toString()
            board[row][column] = BoardState.O
        }
        this.isClickable = false
    }

    //reset the board state
    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = BoardState.BLANK
                buttons[i][j].text = ""
            }
        }
        roundCount = 0
        player1Turn = true
        setTurnText()
        enableAllBoardButton()
        gameStatus.isVisible = false
    }

    //self explanatory
    private fun enableAllBoardButton() {
        buttons.forEach { row ->
            row.forEach { column ->
                column.isClickable = true
            }
        }
    }

    private fun initViewModel() {
        sharedViewModel =
            ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() = BoardFragment()

    }
}