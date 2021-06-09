package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.grsu.movieexplorer.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image_btn_top_rated.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            image_btn_top_rated -> {
                findNavController().navigate(R.id.action_homeFragment_to_movieListFragment)
            }
        }
    }

}