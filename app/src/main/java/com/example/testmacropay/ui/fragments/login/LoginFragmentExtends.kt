package com.example.testmacropay.ui.fragments.login

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.testmacropay.R
import com.example.testmacropay.core.model.BodyCleamsModel
import com.example.testmacropay.ui.StatusRequest
import com.example.testmacropay.utils.DialogGenericFragment
import com.example.testmacropay.utils.filterEmoji
import java.util.regex.Pattern


fun LoginFragment.initElements() {
    mBinding.apply {

        dialog = DialogGenericFragment(R.layout.loading_fragment) { binding, dialog ->

        }

        edTxtPassword.filterEmoji()

        btnLogin.setOnClickListener {

            val email = edTxtEmail.text.toString().trim().trimIndent()
            val password = edTxtPassword.text.toString().trim().trimIndent()
            isOnback = false

            val validEmail = validEmail(email)

            if (validEmail && !password.isNullOrEmpty()){
                viewModel.requestLogin(email, password)
            }else{
                Toast.makeText(requireActivity(), "Campos Invalidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

fun LoginFragment.initObservable() {

    viewModel.responseData.observe(viewLifecycleOwner) {
        when (it.first) {
            StatusRequest.LOADING -> {
                dialog.show(requireActivity().supportFragmentManager, "dialog_loading")
            }
            StatusRequest.SUCCESS -> {
                if (!isOnback) {
                    it.second
                    viewModel.setModel(it.second ?: BodyCleamsModel("", "", "", ""))
                    dialog.dismiss()
                    Log.d("SUCCESS", "initObservable: ${it.second}")
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    isOnback = true
                }
            }
            StatusRequest.FAILURE -> {
                dialog.dismiss()
                Toast.makeText(requireContext(), "Algo salio mal", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

fun LoginFragment.validEmail(email: String): Boolean {
    val pattern: Pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}