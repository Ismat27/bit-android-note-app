package com.example.noteapp.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.noteapp.databinding.FragmentPromptModalBinding

class PromptModalFragment(var title: String, var body: String, var onClickYes: () -> Unit) :
    DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val alertBuilder = AlertDialog.Builder(requireContext()).create()
        alertBuilder.setCanceledOnTouchOutside(false)

        val binding: FragmentPromptModalBinding = FragmentPromptModalBinding.inflate(layoutInflater)

        binding.apply {
            tvModalTitle.text = title
            tvModalBody.text = body

            btnYes.setOnClickListener {
                onClickYes()
            }

            btnNo.setOnClickListener {
                dismiss()
            }
        }

        alertBuilder.setView(binding.root)

        return alertBuilder
    }

}