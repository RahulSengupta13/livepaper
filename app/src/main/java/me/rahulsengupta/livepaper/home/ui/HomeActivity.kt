package me.rahulsengupta.livepaper.home.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import me.rahulsengupta.livepaper.R

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        bottomNavigation.setupWithNavController(findNavController(R.id.nav_host_fragment))
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.homeFragment -> {
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.searchFragment -> {
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.settingsFragment -> {
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }
}
