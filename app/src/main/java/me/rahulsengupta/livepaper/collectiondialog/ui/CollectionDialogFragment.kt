package me.rahulsengupta.livepaper.collectiondialog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import me.rahulsengupta.livepaper.R

class CollectionDialogFragment : DialogFragment(), CollectionDialogPresenter.Listener {

    private lateinit var presenterContainer: CollectionDialogPresenter.Container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.collection_dialog_layout, container, false)
        presenterContainer = CollectionDialogPresenter.Container(root, this)
        arguments?.let {
            val safeArgs = CollectionDialogFragmentArgs.fromBundle(it)
            safeArgs.collectionDialogPayload?.let { CollectionDialogPresenter.present(presenterContainer, it) }
        }
        return root
    }

    override fun onDismissClicked() = dismiss()
}