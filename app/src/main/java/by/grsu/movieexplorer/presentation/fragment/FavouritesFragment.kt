package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import by.grsu.movieexplorer.R

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    companion object{
        fun newInstance(): FavouritesFragment{
            val fragment = FavouritesFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }
}