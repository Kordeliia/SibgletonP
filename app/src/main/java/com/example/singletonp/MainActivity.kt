package com.example.singletonp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import com.example.singletonp.databinding.ActivityMainBinding
import com.example.singletonp.databinding.NavHeaderMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.let{
            it.title = getString(R.string.toolbar_title)
            it.subtitle = getString(R.string.toolbar_subtitle)
            it.setDisplayHomeAsUpEnabled(true)
        }
        val header = binding.navView.getHeaderView(0)
        val headerBinding = NavHeaderMainBinding.bind(header)
        headerBinding.tvName.text ="Laura AM"
        headerBinding.tvEmail.text = "lalvmun@hotmail.es"
        val drawerLayout = binding.drawerLayout
        binding.appBarMain.toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }
        drawerLayout.addDrawerListener(object: DrawerLayout.DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
               // Log.i(TAG, "onDrawerSlide")
            }

            override fun onDrawerOpened(drawerView: View) {
                Log.i(TAG, "onDrawerOpened")
            }

            override fun onDrawerClosed(drawerView: View) {
                Log.i(TAG, "onDrawerClosed")
            }

            override fun onDrawerStateChanged(newState: Int) {
                Log.i(TAG, "onDrawerStateChanged")
            }

        })
        binding.navView.setNavigationItemSelectedListener {menuItem ->
            var fragment : Fragment? = null
            when(menuItem.itemId){
                R.id.nav_home -> {
                    showMsg(R.string.nav_home_msg)
                    fragment = HomeFragment()
                }

                R.id.nav_gallery -> {
                    showMsg(R.string.nav_gallery_msg)
                    showMsg(R.string.nav_home_msg)
                    fragment = GalleryFragment()
                }
                R.id.nav_slideshow -> {
                    showMsg(R.string.nav_slideshow_msg)
                    showMsg(R.string.nav_home_msg)
                    fragment = SlideShowFragment()
                }
            }
            fragment?.let{
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment, it)
                transaction.addToBackStack(null)
                transaction.commit()
        }
            drawerLayout.close()
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun showMsg(resID: Int) {
        Toast.makeText(this, resID, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> showMsg(R.string.menu_settings)
            R.id.action_buscar -> showMsg(R.string.menu_search)
        }
        return super.onOptionsItemSelected(item)
    }
}