package be.timdesmet.hogent_mobielplus_project.ui

import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import be.timdesmet.hogent_mobielplus_project.R
import be.timdesmet.hogent_mobielplus_project.navigation.BottomNavigationBehavior
import be.timdesmet.hogent_mobielplus_project.ui.viewmodels.MainViewModel
import be.timdesmet.hogent_mobielplus_project.ui.viewmodels.shop.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // testing
    private lateinit var mainViewModel: MainViewModel
    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // ViewModel
        val vmf = MainViewModelFactory(application)
        mainViewModel = ViewModelProviders.of(this, vmf).get(MainViewModel::class.java)

        // Bottom toolbar
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Sidebar drawer
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // Bottom toolbar
        bottomNavigationView = findViewById(R.id.navigation)
        val bottomNavigationViewTemp = bottomNavigationView
        bottomNavigationViewTemp!!.setOnNavigationItemSelectedListener(
            mOnNavigationItemSelectedListener
        )

        val layoutParams: CoordinatorLayout.LayoutParams = bottomNavigationView!!.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationBehavior(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var fragment: Fragment
            when (item.itemId) {
                R.id.navigationMyServices -> return@OnNavigationItemSelectedListener true
                R.id.navigationMyAccount -> return@OnNavigationItemSelectedListener true
                R.id.navigationStore -> return@OnNavigationItemSelectedListener true
                R.id.navigationSearch -> return@OnNavigationItemSelectedListener true
                R.id.navigationMenu -> {
                    val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
                    drawer.openDrawer(GravityCompat.START)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.navigationMyServices -> return true
            R.id.navigationMyAccount -> return true
            R.id.navigationStore -> return true
            R.id.navigationSearch -> return true
            R.id.nav_logout -> {
                AlertDialog.Builder(this)
                    .setTitle("Signing out")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("YES") {dialog, wich ->
                        mainViewModel.logout()
                        startActivity(Intent(this, AuthActivity::class.java))
                    }
                    .setNegativeButton("No") {dialog, which -> }
                    .create().show()
            }
        }
        return false
    }

    fun changeStatusBar(mode: Int, window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.contentStatusBar)
            if (mode == 1) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    internal class MainViewModelFactory(val app: Application) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) return MainViewModel(app) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
