package net.oldbigbuddha.vocaloidranking.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import net.oldbigbuddha.vocaloidranking.R

class ProgressDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =  activity?.let {
        val builder = AlertDialog.Builder(it)
        val inflater: LayoutInflater = it.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_progress, null)
        this.isCancelable = false
        builder.setView(view)
            .create()
    } ?: throw IllegalStateException("Activity cannot be null")
}