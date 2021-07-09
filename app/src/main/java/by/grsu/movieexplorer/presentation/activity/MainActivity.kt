package by.grsu.movieexplorer.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.presentation.fragment.FavouritesFragment
import by.grsu.movieexplorer.presentation.fragment.MovieListFragment
import by.grsu.movieexplorer.util.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    TabSelectedCallback {

    private val fragmentManager by lazy { supportFragmentManager }
    private lateinit var bottomNavBar: BottomNavigationView
    private var selectedMovieList: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setInitialFragment()
        bottomNavBar = findViewById(R.id.bottom_nav_bar)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        bottomNavBar.setOnNavigationItemSelectedListener(this)
        bottomNavBar.setOnNavigationItemReselectedListener { }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val bundle = Bundle()
        bundle.putString(Constants.EXTRA_MOVIE_LIST_TYPE, selectedMovieList)
        val transaction = fragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.menu_item_home -> {
                transaction.replace(R.id.fragment_container, MovieListFragment.newInstance(bundle))
            }
            R.id.menu_item_favourites -> {
                transaction.addToBackStack(null)
                    .replace(R.id.fragment_container, FavouritesFragment.newInstance())
            }
            else -> {
                return false
            }
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
        return true
    }

    override fun onBackPressed() {
        when (fragmentManager.findFragmentById(R.id.fragment_container)) {
            is MovieListFragment -> {
                finish()
            }
            is FavouritesFragment -> {
                fragmentManager.popBackStack()
                bottomNavBar.selectedItemId = R.id.menu_item_home
            }
            else -> {
                fragmentManager.popBackStack()
            }
        }
    }

    private fun setInitialFragment() {
        val bundle = Bundle()
        bundle.putString(Constants.EXTRA_MOVIE_LIST_TYPE, getString(R.string.top_rated_movies))
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MovieListFragment.newInstance(bundle))
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    override fun getSelectedTab(tabName: String) {
        selectedMovieList = tabName
    }
}