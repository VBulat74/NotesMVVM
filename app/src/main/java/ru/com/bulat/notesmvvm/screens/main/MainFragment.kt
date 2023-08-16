package ru.com.bulat.notesmvvm.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.com.bulat.notesmvvm.R
import ru.com.bulat.notesmvvm.databinding.FragmentMainBinding
import ru.com.bulat.notesmvvm.utilits.APP_ACTIVITY

class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel : MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialisation()
    }

    private fun initialisation() {
        mViewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        mBinding.btnAddNote.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}