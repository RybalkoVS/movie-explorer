package by.grsu.movieexplorer.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {

    private var binding: FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        fragmentHomeBinding.imageBtnTopRated.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding?.imageBtnTopRated -> {
                findNavController().navigate(R.id.action_homeFragment_to_movieListFragment)
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}