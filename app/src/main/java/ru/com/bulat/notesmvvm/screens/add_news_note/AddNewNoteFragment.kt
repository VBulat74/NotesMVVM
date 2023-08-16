package ru.com.bulat.notesmvvm.screens.add_news_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.com.bulat.notesmvvm.R
import ru.com.bulat.notesmvvm.R.*
import ru.com.bulat.notesmvvm.databinding.FragmentAddNewNoteBinding
import ru.com.bulat.notesmvvm.model.AppNote
import ru.com.bulat.notesmvvm.utilits.APP_ACTIVITY
import ru.com.bulat.notesmvvm.utilits.showToast

class AddNewNoteFragment : Fragment() {

    private var _binding : FragmentAddNewNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNewNoteFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewNoteBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        iinitialisation()
    }

    private fun iinitialisation() {
        mViewModel = ViewModelProvider(this)[AddNewNoteFragmentViewModel::class.java]
        mBinding.btnAddNote.setOnClickListener {
            val name = mBinding.inputNameNote.text.toString()
            val text = mBinding.inputTextNote.text.toString()

            if (name.isEmpty()){
                showToast(getString(string.name_is_empty))
            } else{
                val note = AppNote(
                    id = 0,
                    name = name,
                    text = text,
                )
                mViewModel.insert(note){
                     APP_ACTIVITY.navController.navigate(R.id.action_add_newNoteFragment_to_mainFragment)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}