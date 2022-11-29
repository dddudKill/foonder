package com.example.homework_2.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.homework_2.R
import com.example.homework_2.objects.Gif
import com.example.homework_2.ui.main.gif.GifViewHolder
import org.w3c.dom.Text

private const val ARG_PARAM1 = "gifId"

class ItemFragment : Fragment() {
    private var gifId: String = ""
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gifId = it.getString(ARG_PARAM1) as String
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.item_title)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            val gif = viewModel.getGif(id = gifId)
            title.text = gif.gifTitle()
            GifViewHolder(view).bind(gif) { Unit }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(gifId: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, gifId)
                }
            }
    }
}