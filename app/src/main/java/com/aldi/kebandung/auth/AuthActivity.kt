package com.aldi.kebandung.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.aldi.kebandung.R
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setupAuthNavigation()
    }

    private fun setupAuthNavigation() {
        val navHostFragment = nav_auth_fragment as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.auth_nav)
        val intent = intent
        val loginMethod = intent.getStringExtra("loginMethod")
        if (loginMethod == "register") {
            graph.startDestination = R.id.authFragment
           // graph.startDestination = R.id.loginFragment
        } else {
          //  graph.startDestination = R.id.registerFragment
        }
        navHostFragment.navController.graph = graph
    }
}
