package com.enigma.dio.tictactoe.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.enigma.dio.tictactoe.R

class LoadingDialog {

    companion object {
        fun build(context : Context) : AlertDialog {
            //create layout inflater to display the dialog
            val inflater = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null)
            //create the dialog (cancelable)
            val dialog = AlertDialog.Builder(context).setView(inflater).setCancelable(true).create()
            //set the dialog background
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            return dialog
        }
    }
}