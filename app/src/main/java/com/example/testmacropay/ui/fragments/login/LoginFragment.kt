package com.example.testmacropay.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.auth0.android.jwt.JWT
import com.example.testmacropay.core.api.ApiService
import com.example.testmacropay.databinding.FragmentLoginBinding
import com.example.testmacropay.databinding.LoadingFragmentBinding
import com.example.testmacropay.utils.DialogGenericFragment
import com.example.testmacropay.viewmodel.MainViewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    val mBinding get() = _binding!!
    var isOnback = false

    lateinit var dialog : DialogGenericFragment<LoadingFragmentBinding>

    val viewModel by activityViewModels<MainViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
        initObservable()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}