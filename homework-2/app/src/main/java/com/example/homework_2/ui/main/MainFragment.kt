package com.example.homework_2.ui.main

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.homework_2.R
import com.example.homework_2.objects.Gif
import com.example.homework_2.ui.main.gif.GifListAdapter
import com.example.homework_2.ui.main.load_state.GifsLoadStateAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModels<MainViewModel>()
    private val gifListAdapter = GifListAdapter { gif: Gif -> gifItemClicked(gif) }
    private val loadStateAdapter = GifsLoadStateAdapter { gifListAdapter.retry() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnRetry = view.findViewById<Button>(R.id.btn_retry)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)

        val columns = resources.getInteger(R.integer.columns)
        view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = StaggeredGridLayoutManager(columns, RecyclerView.VERTICAL)
            adapter = gifListAdapter.apply {
                stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }.withLoadStateFooter(footer = loadStateAdapter)
        }

        btnRetry.setOnClickListener {
            gifListAdapter.retry()
        }

        gifListAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                btnRetry.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        btnRetry.visibility = View.VISIBLE
                        loadState.refresh as LoadState.Error
                    }
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(context, it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.IO) {
                viewModel.getGifs().buffer().collectLatest {
                    gifListAdapter.submitData(it)
                }
            }
        }
    }

    private fun gifItemClicked(gif : Gif) {
        activity?.supportFragmentManager?.let {
            val transaction = it.beginTransaction()
            transaction
                .replace(R.id.fragmentContainer, ItemFragment.newInstance(gif.gifId()))
                .addToBackStack(null)
                .commit()
        }
    }
}
