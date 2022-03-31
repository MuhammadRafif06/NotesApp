package com.rafif.notesapp.presentation.utils

import androidx.appcompat.widget.AppCompatSpinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.rafif.notesapp.R
import com.rafif.notesapp.presentation.home.HomeFragmentDirections
import com.google.android.material.card.MaterialCardView
import com.rafif.notesapp.data.local.entity.Notes
import com.rafif.notesapp.data.local.entity.Priority

object BindingAdapters {

    @BindingAdapter("android:parsePriorityColor")
    @JvmStatic
    fun parsePriorityColor(cardView: MaterialCardView, priority: Priority){
        when (priority){
            Priority.HIGH -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.pink))
            Priority.MEDIUM -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
            Priority.LOW -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))
        }
    }

    @BindingAdapter("android:sendDataToDetail")
    @JvmStatic
    fun sendDataToDetail(view: ConstraintLayout, currentItem: Notes){
        view.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(currentItem)
            view.findNavController().navigate(action)
        }
    }

    @BindingAdapter("android:parsePriorityToInt")
    @JvmStatic
    fun parsePriorityToInt(view: AppCompatSpinner, priority: Priority) {
        when (priority) {
            Priority.HIGH -> {
                view.setSelection(0)
            }
            Priority.MEDIUM -> {
                view.setSelection(1)
            }
            Priority.LOW -> {
                view.setSelection(2)
            }
        }
    }

}