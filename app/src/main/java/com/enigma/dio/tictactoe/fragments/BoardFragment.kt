package com.enigma.dio.tictactoe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.enigma.dio.tictactoe.R
import com.enigma.dio.tictactoe.interfaces.TGame

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BoardFragment : Fragment() {

    private lateinit var boardHandler : TGame

    private lateinit var player1TotalScore: TextView
    private lateinit var player2TotalScore: TextView

    private lateinit var currentTurn: TextView

    private lateinit var buttons: Array<Array<Button>>

    private lateinit var player1Name: String
    private lateinit var player2Name: String

    private var player1Turn = true
    private var newGameBtnVisible = false

    private var roundCount = 0
    private var player1Points = 0
    private var player2Points = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        boardHandler = activity as TGame


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance(player1 : String, player2 : String) = BoardFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, player1)
                putString(ARG_PARAM2, player2)
            }
        }
    }
}
