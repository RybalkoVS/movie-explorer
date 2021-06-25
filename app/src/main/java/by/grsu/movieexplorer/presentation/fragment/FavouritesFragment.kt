package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.grsu.movieexplorer.R
import org.koin.dsl.module

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    companion object{
        fun newInstance(): FavouritesFragment{
            val fragment = FavouritesFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }
}