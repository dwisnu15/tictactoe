package com.enigma.dio.tictactoe.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enigma.dio.tictactoe.R
import com.enigma.dio.tictactoe.databinding.FragmentRegistrationBinding
import com.enigma.dio.tictactoe.utils.LoadingDialog
import com.enigma.dio.tictactoe.utils.ResourceStatus
import com.enigma.dio.tictactoe.viewmodels.MainActivityViewModel
import com.enigma.dio.tictactoe.viewmodels.RegistrationFragmentViewModel


class RegistrationFragment : Fragment() {

    //the sharedviewmodel from mainactivity
    lateinit var sharedViewModel : MainActivityViewModel

    //this fragment's viewmodel
    lateinit var viewModel: RegistrationFragmentViewModel

    //this fragment viewbinding
    lateinit var binding: FragmentRegistrationBinding

    //dialog for loading state of data validation
    lateinit var loadingDialog: AlertDialog

    lateinit var p1Name : String
    lateinit var p2Name : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        loadingDialog = LoadingDialog.build(requireContext())

        binding = FragmentRegistrationBinding.inflate(layoutInflater)

        binding.apply {
            bPlay.setOnClickListener {
                p1Name = inputPlayer1.text.toString()
                p2Name = inputPlayer2.text.toString()
                viewModel.inputNamesValidation(p1Name, p2Name)
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initViewModel() {
        sharedViewModel=
            ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        viewModel =
            ViewModelProvider(this).get(RegistrationFragmentViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.allNameValid.observe(this) {
            //check the allNameValid status
            when(it.status) {
                ResourceStatus.SUCCESS -> {
                    //hide the loading dialog
                    loadingDialog.hide()
                    //set the players name
                    sharedViewModel.setPlayers(p1Name, p2Name)
                    //navigate to board fragment
                    findNavController().navigate(R.id.action_registrationFragment2_to_boardFragment)
                }

                ResourceStatus.FAILURE -> {
                    //hide the loading dialog
                    loadingDialog.hide()
                    //show toast with error message
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                ResourceStatus.LOADING -> {
                    //display the loading dialog
                    loadingDialog.show()
                }

            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RegistrationFragment()
    }
}