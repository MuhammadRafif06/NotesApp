package com.rafif.notesapp.presentation.add

import ExtensionFunctions.setActionBar
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rafif.notesapp.R
import com.rafif.notesapp.data.local.entity.Notes
import com.rafif.notesapp.data.local.entity.Priority
import com.rafif.notesapp.databinding.FragmentAddBinding
import com.rafif.notesapp.presentation.NotesViewModel
import com.rafif.notesapp.presentation.utils.HelperFunctions.setPriorityColor
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding as FragmentAddBinding

    private val addViewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        binding.apply {
            toolbarAdd.setActionBar(requireActivity())
            spinnerPriorities.onItemSelectedListener = context?.let { setPriorityColor(it, priorityIndicator) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            insertNote()
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
            Toast.makeText(context, "Successfully Add Note..", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertNote() {
        binding.apply {
            val title = edtTitle.text.toString()
            val desc = edtDescription.text.toString()

            val calendar = Calendar.getInstance().time
            val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(calendar)
            val priority = spinnerPriorities.selectedItem.toString()

            val data = Notes(
                0,
                title,
                desc,
                date,
                parseToPriority(priority)
            )
            addViewModel.insertNotes(data)
        }
    }

    private fun parseToPriority(priority: String): Priority {
        val arrPriority = resources.getStringArray(R.array.priorities)
        return when (priority) {
            arrPriority[0] -> Priority.HIGH
            arrPriority[1] -> Priority.MEDIUM
            arrPriority[2] -> Priority.LOW
            else -> Priority.HIGH
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }*/

}