package com.enigma.dio.tictactoe

import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.enigma.dio.tictactoe.fragments.BoardFragment
import com.enigma.dio.tictactoe.fragments.BoardMenuFragment
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var player1 : TextInputEditText
    private lateinit var player2 : TextInputEditText

    companion object {
        const val PLAYER_ONE = "Player one"
        const val PLAYER_TWO = "Player two"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        player1 = findViewById(R.id.inputPlayer1)
        player2 = findViewById(R.id.inputPlayer2)
    }

    fun toTBoard(view : View) {
        if (player1.text.toString().isBlank() || player2.text.toString().isBlank()) {
            Toast.makeText(this, "Both player's name must not be empty",
                Toast.LENGTH_SHORT).show();
        }
        else {
            val intent = Intent(this, TBoardActivity::class.java)
            intent.putExtra(PLAYER_ONE, player1.text.toString())
            intent.putExtra(PLAYER_TWO, player2.text.toString())
            startActivity(intent)
        }

    }


}