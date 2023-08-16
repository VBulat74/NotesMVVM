package ru.com.bulat.notesmvvm.screens.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.com.bulat.notesmvvm.R
import ru.com.bulat.notesmvvm.databinding.FragmentStartBinding
import ru.com.bulat.notesmvvm.utilits.APP_ACTIVITY
import ru.com.bulat.notesmvvm.utilits.TYPE_ROOM


class StartFragment : Fragment() {

    private var _binding : FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel : StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)

        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialisation()
    }

    private fun initialisation() {
        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        mBinding.btnRoom.setOnClickListener {
            mViewModel.initDataBase(TYPE_ROOM){
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}