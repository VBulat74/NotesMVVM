package ru.com.bulat.notesmvvm.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.com.bulat.notesmvvm.R
import ru.com.bulat.notesmvvm.databinding.FragmentMainBinding
import ru.com.bulat.notesmvvm.model.AppNote
import ru.com.bulat.notesmvvm.utilits.APP_ACTIVITY

class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel : MainFragmentViewModel
    private lateinit var mRecycleView : RecyclerView
    private lateinit var mAdapter : MainAdapter
    private lateinit var mObserverList : Observer<List<AppNote>>

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
        mAdapter = MainAdapter()
        mRecycleView = mBinding.recicleView
        mRecycleView.adapter = mAdapter

        mObserverList = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }

        mViewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        mViewModel.allNotes.observe(viewLifecycleOwner, mObserverList)

        mBinding.btnAddNote.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mViewModel.allNotes.removeObserver(mObserverList)
        mRecycleView.adapter = null
    }
}