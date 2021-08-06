package com.inretailapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inretailapp.R
import com.inretailapp.R.layout.activity_home
import com.inretailapp.app.providers.DataStoreProvider
import com.inretailapp.app.util.startNewActivity
import com.inretailapp.databinding.HomeActivityBinding
import com.inretailapp.ui.login.AuthActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin

class HomeActivity : AppCompatActivity() {

    private var dataStore: DataStoreProvider = getKoin().get()
    private lateinit var binding: HomeActivityBinding

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, activity_home)
        binding.apply {
            home        = this@HomeActivity
            viewModel   = viewModel
            setSupportActionBar(menuAppbar)
        }
        supportActionBar?.setDisplayShowHomeEnabled(false)
        configurarWidgets()

    }

    private fun configurarWidgets() {
        setupDrawerLayout()
        setupNavigationMenu()
        binding.btnCerrarSesion.setOnClickListener { performLogout() }
    }

    fun performLogout() = lifecycleScope.launch {
        dataStore.clear()
        startNewActivity(AuthActivity::class.java)
    }

    private fun showBottomNav(bottomNav: BottomNavigationView, isHomeView: Boolean = false) {
        if (isHomeView) {
            bottomNav.visibility = View.GONE
        } else {
            bottomNav.visibility = View.VISIBLE
        }
    }

    private fun setupNavigationMenu() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setDisplayShowTitleEnabled(true)
            when (destination.id) {
                R.id.menuFragment -> {
                    showBottomNav(binding.bottomNav, true)
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                }
                else -> {
                    showBottomNav(binding.bottomNav)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }

    private fun setupDrawerLayout() {
        val containerController = supportFragmentManager.findFragmentById(R.id.fragment_container)?.findNavController()
        containerController?.let { binding.bottomNav.setupWithNavController(it) }

        binding.navDrawer.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}