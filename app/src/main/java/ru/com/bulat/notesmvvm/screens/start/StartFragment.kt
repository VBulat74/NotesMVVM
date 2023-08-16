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
import ru.com.bulat.notesmvvm.utilits.EMAIL
import ru.com.bulat.notesmvvm.utilits.PASSWORD
import ru.com.bulat.notesmvvm.utilits.TYPE_FIREBASE
import ru.com.bulat.notesmvvm.utilits.TYPE_ROOM
import ru.com.bulat.notesmvvm.utilits.showToast


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
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
        mBinding.btnFirebase.setOnClickListener {
            mBinding.inputEmail.visibility = View.VISIBLE
            mBinding.inputPassword.visibility = View.VISIBLE
            mBinding.btnLogin.visibility = View.VISIBLE
            mBinding.btnLogin.setOnClickListener {
                val input_email = mBinding.inputEmail.text.toString()
                val input_password = mBinding.inputEmail.text.toString()

                if (input_email.isNotEmpty() && input_password.isNotEmpty()) {
                    EMAIL = input_email
                    PASSWORD = input_password
                    mViewModel.initDataBase(TYPE_FIREBASE){
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                    }
                } else {
                    showToast(getString(R.string.enter_login_and_password))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}