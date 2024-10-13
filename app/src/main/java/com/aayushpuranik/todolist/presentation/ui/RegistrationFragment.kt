package com.aayushpuranik.todolist.presentation.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.aayushpuranik.todolist.databinding.FragmentRegistrationBinding
import com.aayushpuranik.todolist.domain.model.Person
import com.aayushpuranik.todolist.presentation.UIState
import com.aayushpuranik.todolist.presentation.viewModels.RegistrationViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private lateinit var registrationViewModel: RegistrationViewModel
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var alertDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        alertDialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Something went wrong")
            .setMessage(registrationViewModel.dataLiveData.value.toString())
            .create()

        progressDialog = ProgressDialog(requireActivity()).also {
            it.setTitle("Loading...")
        }


        registrationViewModel.dataLiveData.observe(viewLifecycleOwner) {
            when (registrationViewModel.dataLiveData.value) {
                is UIState.Error -> {
                    progressDialog.dismiss()
                    alertDialog.show()
                }

                is UIState.Loading -> {
                    progressDialog.show()
                }

                is UIState.Success -> {
                    progressDialog.dismiss()
                    Toast.makeText(
                        requireContext(),
                        "User have been saved successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    progressDialog.dismiss()
                }
            }
        }


        registrationViewModel.nameErrorLiveData.observe(viewLifecycleOwner) {
            binding.nameErrorTV.text = registrationViewModel.nameErrorLiveData.value
        }
        registrationViewModel.emailErrorLiveData.observe(viewLifecycleOwner) {
            binding.emailErrorTV.text = registrationViewModel.emailErrorLiveData.value
        }
        registrationViewModel.passwordErrorLiveData.observe(viewLifecycleOwner) {
            binding.passwordErrorTV.text = registrationViewModel.passwordErrorLiveData.value
        }
        registrationViewModel.confirmPasswordErrorLiveData.observe(viewLifecycleOwner) {
            binding.confirmpasswordErrorTV.text =
                registrationViewModel.confirmPasswordErrorLiveData.value
        }

        checkForTextChanged(binding.name) { resetErrorValuesToEmpty(registrationViewModel.nameErrorLiveData) }
        checkForTextChanged(binding.email) { resetErrorValuesToEmpty(registrationViewModel.emailErrorLiveData) }
        checkForTextChanged(binding.password) { resetErrorValuesToEmpty(registrationViewModel.passwordErrorLiveData) }
        checkForTextChanged(binding.confirmPassword) { resetErrorValuesToEmpty(registrationViewModel.confirmPasswordErrorLiveData) }

        binding.registrationBtn.setOnClickListener {
            registrationViewModel.addContact(
                getPerson(),
                binding.confirmPassword.text.toString().trim()
            )
        }
        return binding.root
    }

    private fun getPerson(): Person {
        return Person(
            Calendar.getInstance().timeInMillis,
            binding.name.text.toString().trim(),
            binding.email.text.toString().trim(),
            binding.password.text.toString().trim(),
        )
    }

    private fun checkForTextChanged(textView: TextView, onTextChanged: (Boolean) -> Unit) {
        textView.doOnTextChanged { text, start, before, count ->
            onTextChanged(textView.text.toString().trim().length > 0)
        }
    }

    private fun resetErrorValuesToEmpty(mutableLiveData: MutableLiveData<String>) {
        mutableLiveData.value = ""
    }
}