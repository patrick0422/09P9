package com.yang.a09p9.presentation.user

import androidx.navigation.fragment.findNavController
import com.yang.a09p9.R
import com.yang.a09p9.base.BaseFragment
import com.yang.a09p9.databinding.FragmentTermsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsFragment : BaseFragment<FragmentTermsBinding>(R.layout.fragment_terms) {

    override fun init() {
        binding.termsFragment = this
    }

    fun onConfirm() {
        findNavController().navigate(R.id.action_termsFragment_to_signUpFragment)
    }
}