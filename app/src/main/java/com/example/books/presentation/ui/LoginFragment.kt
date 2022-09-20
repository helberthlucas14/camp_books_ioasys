package com.example.books.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.books.databinding.FragmentLoginBinding
import com.example.books.presentation.viewmodel.LoginViewModel
import com.example.books.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by lazy { getViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentLoginBinding.inflate(inflater, container, false)
        .apply {
            _binding = this
        }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
        addObservers()
    }

    private fun setListener() {
        binding.enterButton.setOnClickListener {
            binding.run {
                loginViewModel.login(
                    textFieldEditTextEmail.text.toString(),
                    textFieldEditTextPassword.text.toString()
                )

                textFieldEditTextPassword.addTextChangedListener {
                    txtError.visibility = View.GONE
                }

                textFieldEditTextEmail.addTextChangedListener {
                    txtError.visibility = View.GONE
                }
            }
        }
    }

    private fun addObservers() {
        loginViewModel.loggedUserViewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Success -> OnSuccess()
                is ViewState.Error -> OnError(state.throwable.message)
                is ViewState.Loading -> OnLoading()
                else -> Unit
            }
        }
    }

    private fun OnSuccess() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToBookListFragment()
        )
    }

    private fun OnLoading() {
        binding.progressDialog.visibility = View.VISIBLE
    }

    private fun OnError(errorMsg: String?) {
        if (errorMsg.isNullOrEmpty().not())
            binding.txtError.text = errorMsg

        binding.txtError.visibility = View.VISIBLE
        binding.progressDialog.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loginViewModel.resetViewState()
        _binding = null
    }
}