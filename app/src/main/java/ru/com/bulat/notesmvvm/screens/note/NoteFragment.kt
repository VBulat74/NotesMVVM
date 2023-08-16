package ru.com.bulat.notesmvvm.screens.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import ru.com.bulat.notesmvvm.R
import ru.com.bulat.notesmvvm.databinding.FragmentNoteBinding
import ru.com.bulat.notesmvvm.model.AppNote
import ru.com.bulat.notesmvvm.utilits.APP_ACTIVITY

class NoteFragment : Fragment(), MenuProvider {

    private var _binding : FragmentNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel : NoteFragmentViewModel
    private lateinit var mCurrentNote : AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        mCurrentNote = arguments?.getSerializable("note") as AppNote

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // Inflate the layout for this fragment
        return mBinding.root
    }



    override fun onStart() {
        super.onStart()
        initialisation()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.btn_delete -> {
                mViewModel.delete(mCurrentNote){
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
        return true
    }

    //    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.note_action_menu, menu)
//
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.btn_delete -> {
//                mViewModel.delete(mCurrentNote){
//                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
//                }
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun initialisation() {
        mBinding.noteName.text = mCurrentNote.name
        mBinding.noteText.text = mCurrentNote.text

        mViewModel = ViewModelProvider(this)[NoteFragmentViewModel::class.java]

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}