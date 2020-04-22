package com.example.firebaseauthwithmvvm.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.constants.Childs
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController: NavController = findNavController(R.id.nav_host_fragment)

        //move to specific fragment
        if (intent.getIntExtra(Childs.fragmentNumber.name, 0) == 2) {
            Log.e("TEST1.1", "HERE1")
            val navHost = nav_host_fragment as NavHostFragment
            val graph = navHost.navController
                .navInflater.inflate(R.navigation.app_navigation)
            graph.startDestination = R.id.navigation_orders
            navHost.navController.graph = graph
            NavigationUI.setupActionBarWithNavController(
                this, navHost.navController
            )

        } else {

            Log.e("TEST1.1", "HERE2")
        }


        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_store, R.id.navigation_orders, R.id.navigation_finished_orders)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


//        val navHost = nav_host_fragment as NavHostFragment
//        val graph = navHost.navController
//            .navInflater.inflate(R.navigation.app_navigation)
//        graph.startDestination = R.id.navigation_store
//
//        navHost.navController.graph = graph
//
//        NavigationUI.setupActionBarWithNavController(this, navHost.navController)


    }
}
