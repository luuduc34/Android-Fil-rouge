package com.technifutur.filrouge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.technifutur.filrouge.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
    }
    private fun setupBottomNavigation() {
        val navController = Navigation.findNavController(this, binding.navHostFragment.id)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }
}