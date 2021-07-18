package com.code.practice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.code.practice.App
import com.code.practice.database.enities.UserName
import com.code.practice.databinding.FragmentFirstBinding
import com.code.practice.ui.adapter.UserNameAdapter


class AddUserNameFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var userNameAdapter = UserNameAdapter()
    private val binding get() = _binding!!

    private val userNameViewModel: UserNameViewModel by viewModels {
        UserNameViewModelFactory((requireActivity().application as App).userNameRepo)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvNames.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = userNameAdapter.apply {
                callback = {
                    userNameViewModel.deleteUserName(it)
                }
            }
        }

        initViewModels()
        binding.btnSubmit.setOnClickListener {
            val name = binding.etInput.text.toString().trim()
            if (name.isNotEmpty()){
                userNameViewModel.insertUserName(UserName().apply { this.userName=name })
            }
        }

    }

    private fun initViewModels(){
        userNameViewModel.getAllUserNames()

        userNameViewModel.userNames.observe(viewLifecycleOwner, {
            it?.let {
                userNameAdapter.submitList(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}