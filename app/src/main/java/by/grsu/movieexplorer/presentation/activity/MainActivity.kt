package by.grsu.movieexplorer.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.transition.Transition
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.presentation.fragment.FavouritesFragment
import by.grsu.movieexplorer.presentation.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val fragmentManager by lazy { supportFragmentManager }
    private lateinit var bottomNavBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            setInitialFragment()
        }
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
                transaction.replace(R.id.fragment_container, HomeFragment.newInstance())
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
            HomeFragment() -> {
                finish()
            }
            FavouritesFragment() -> {
                fragmentManager.popBackStack()
                bottomNavBar.selectedItemId = R.id.menu_item_home
            }
            else -> {
                fragmentManager.popBackStack()
            }
        }
    }

    private fun setInitialFragment(){
        fragmentManager.beginTransaction()
            .add(R.id.fragment_container, HomeFragment.newInstance())
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

}