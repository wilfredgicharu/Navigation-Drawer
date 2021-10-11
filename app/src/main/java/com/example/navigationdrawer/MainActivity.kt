package com.example.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
// the function of setting these in AppBarConfiguration is that these primary
//destinations will not display an up arrow when they are selected as they are at
//the top level
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.toolbar)) //configures the toolbar used in the app

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController= navHostFragment.navController  //retrieving the navHostFragment

      //adding the menu item you want to display in the drawer
        appBarConfiguration= AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_recent, R.id.nav_favorites, R.id.nav_archive, R.id.nav_bin
            ), findViewById(R.id.drawer_layout) //the drawer layout is the container for the nav_view, the main app bar and its included content
        )
        setupActionBarWithNavController(navController,appBarConfiguration)
        findViewById<NavigationView>(R.id.nav_view)?.setupWithNavController(navController)
//This sets up the app bar with the navigation graph so that any changes that are
//made to the destinations are reflected in the app bar:
    }


    // it handles the function when pressing the upbutton for the second destination and ensuring it goes back to
    //the parent primary destination
    override fun onSupportNavigateUp(): Boolean {
            val navController= findNavController(R.id.nav_host_fragment)
            return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }
 //oncreateotion selects the menu to add to the app bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
// onoptionitemselected handles what to do when the item is selected.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
    }

    }
