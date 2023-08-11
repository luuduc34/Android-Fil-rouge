package com.technifutur.filrouge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.res.ResourcesCompat
import com.technifutur.filrouge.databinding.ActivityAuthenticationBinding
import com.technifutur.filrouge.databinding.ActivityHomeBinding
import java.util.regex.Pattern

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTextWatchers()
    }
    private fun setupTextWatchers() {
        binding.emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches()
                toggleEmailValidity(isValidEmail)
                updateConnectButtonState()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val isValidPassword = PASSWORD_REGEX.matcher(s).matches()
                togglePasswordValidity(isValidPassword)
                updateConnectButtonState()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
    private fun toggleEmailValidity(isValid: Boolean) {
        if(isValid) {
            binding.emailLayout.isErrorEnabled = false
            binding.emailEditText.setTextColor(
                ResourcesCompat.getColor(resources, R.color.text_input_valid_text_color,
                theme))
        } else {
            binding.emailLayout.error = getString(R.string.email_error_hint)
            binding.emailLayout.isErrorEnabled = true
            binding.emailEditText.setTextColor(ResourcesCompat.getColor(resources, R.color.black, theme))
        }
    }
    private fun togglePasswordValidity(isValid: Boolean) {
        if(isValid) {
            binding.passwordLayout.isErrorEnabled = false
            binding.passwordEditText.setTextColor(
                ResourcesCompat.getColor(resources, R.color.text_input_valid_text_color,
                    theme))
        } else {
            binding.passwordLayout.error = getString(R.string.password_error_hint)
            binding.passwordLayout.isErrorEnabled = true
            binding.passwordEditText.setTextColor(ResourcesCompat.getColor(resources, R.color.black, theme))
        }
    }
    companion object {
        val PASSWORD_REGEX: Pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    }
    private fun updateConnectButtonState() {
        val isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailEditText.text).matches()
        val isValidPassword = PASSWORD_REGEX.matcher(binding.passwordEditText.text).matches()

        if (isValidEmail && isValidPassword) {
            binding.connectButton.isEnabled = true
            binding.connectButton.setBackgroundResource(R.drawable.input_rounded_background2)
        } else {
            binding.connectButton.isEnabled = false
            binding.connectButton.setBackgroundResource(R.drawable.input_rounded_background3)
        }
    }
}
