package by.grsu.movieexplorer.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.presentation.fragment.FavouritesFragment
import by.grsu.movieexplorer.presentation.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val fragmentManager by lazy { supportFragmentManager }
    private val homeFragment by inject<HomeFragment>()
    private val favouritesFragment by inject<FavouritesFragment>()
    private lateinit var bottomNavBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager.beginTransaction()
            .add(R.id.fragment_container, homeFragment)
            .addToBackStack(null)
            .commit()
        bottomNavBar = findViewById(R.id.bottom_nav_bar)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        bottomNavBar.setOnNavigationItemSelectedListener(this)
        //bottom_nav_bar.setOnNavigationItemReselectedListener { }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val transaction = fragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.menu_item_home -> {
                transaction.replace(R.id.fragment_container, homeFragment)
            }
            R.id.menu_item_favourites -> {
                transaction.addToBackStack(null)
                    .replace(R.id.fragment_container, favouritesFragment)
            }
            else -> {
                return false
            }
        }
        transaction.commit()
        return true
    }

    override fun onBackPressed() {
        when (fragmentManager.findFragmentById(R.id.fragment_container)) {
            homeFragment -> {
                finish()
            }
            favouritesFragment -> {
                fragmentManager.popBackStack()
                bottomNavBar.selectedItemId = R.id.menu_item_home
            }
            else -> {
                fragmentManager.popBackStack()
            }
        }
    }

}