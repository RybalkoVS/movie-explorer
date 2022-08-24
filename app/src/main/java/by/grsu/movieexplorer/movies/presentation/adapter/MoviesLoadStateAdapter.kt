package by.grsu.movieexplorer.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import by.grsu.movieexplorer.R

class MoviesLoadStateAdapter(
    private val onRetry: () -> Unit
) : LoadStateAdapter<MoviesLoadStateAdapter.MoviesLoadStateViewHolder>() {

    class MoviesLoadStateViewHolder(
        parent: ViewGroup,
        onRetry: () -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_load_state, parent, false)
    ) {
        private val progressBar = itemView.findViewById<ProgressBar>(R.id.progress_bar)
        private val retryButton = itemView.findViewById<Button>(R.id.button_retry).also {
            it.setOnClickListener {
                onRetry()
            }
        }

        fun bind(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
        }
    }

    override fun onBindViewHolder(holder: MoviesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MoviesLoadStateViewHolder {
        return MoviesLoadStateViewHolder(parent, onRetry)
    }
}