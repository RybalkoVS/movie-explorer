package by.grsu.movieexplorer.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.presentation.fragment.FavouritesFragment
import by.grsu.movieexplorer.presentation.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val fragmentManager by lazy { supportFragmentManager }
    private val homeFragment by lazy { HomeFragment() }
    private val favouritesFragment by lazy { FavouritesFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager.beginTransaction()
            .add(R.id.fragment_container, homeFragment)
            .addToBackStack(null)
            .commit()
        setupBottomNavigation()
    }

    private fun setupBottomNavigation(){
        bottom_nav_bar.setOnNavigationItemSelectedListener(this)
        bottom_nav_bar.setOnNavigationItemReselectedListener {  }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val transaction = fragmentManager.beginTransaction()
        when(item.itemId){
            R.id.menu_item_home -> {
                transaction
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, homeFragment)
            }
            R.id.menu_item_favourites -> {
                transaction
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, favouritesFragment)
            }
            else -> {
                return false
            }
        }
        transaction.commit()
        return true
    }

}