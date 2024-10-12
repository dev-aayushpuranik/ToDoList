package com.aayushpuranik.todolist.presentation.ui

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aayushpuranik.todolist.databinding.FragmentRegistrationBinding
import com.aayushpuranik.todolist.domain.model.Person
import com.aayushpuranik.todolist.presentation.viewModels.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.regex.Pattern

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private lateinit var registrationViewModel: RegistrationViewModel
    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        binding.registrationBtn.setOnClickListener {
            if(isValid()) {
                registrationViewModel.addContact(getPerson())
            }
        }

        return binding.root
    }

    private fun isValid(): Boolean {
        val isFirstNameValid = (binding.firstName.text.toString().trim().length > 0)
        val isLastNameValid = (binding.lastName.text.toString().trim().length > 0)
        val isPasswordNameValid = isPasswordValid()
        val isPhoneNumberValid = binding.phone.text.toString().length == 10 && PhoneNumberUtils.isGlobalPhoneNumber(binding.phone.text.toString().trim())
        val isEmailValid = isValidEmail(binding.email.text.toString().trim())
        return (isFirstNameValid && isLastNameValid && isPasswordNameValid && isPhoneNumberValid && isEmailValid)
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun isPasswordValid(): Boolean {
        return (binding.password.text.toString().trim().length == binding.confirmPassword.text.toString().trim().length)
                && (binding.password.text.toString().trim() == binding.confirmPassword.text.toString().trim())
    }

    private fun getPerson(): Person {
        return Person(
            Calendar.getInstance().timeInMillis,
            binding.firstName.text.toString().trim(),
            binding.lastName.text.toString().trim(),
            binding.phone.text.toString().trim(),
            binding.email.text.toString().trim(),
            binding.password.text.toString().trim()
        )
    }
}