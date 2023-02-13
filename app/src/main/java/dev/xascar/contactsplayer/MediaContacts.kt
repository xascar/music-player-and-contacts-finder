package dev.xascar.contactsplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.xascar.contactsplayer.databinding.ActivityMediaContactsBinding

class MediaContacts : AppCompatActivity() {

    private lateinit var binding: ActivityMediaContactsBinding
    private var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMediaContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        supportActionBar?.hide();//Ocultar ActivityBar anterior

//        toolbar = findViewById<Toolbar>(R.id.toolbar);
//        setSupportActionBar(toolbar); //NO PROBLEM !!!!



        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_media_contacts)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_music, R.id.nav_contacts
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        navView.setOnItemSelectedListener {item ->
//                NavigationUI.onNavDestinationSelected(
//                    item,
//                    navController
//                )
//                when (item.itemId){
//                    R.id.nav_music -> {}
//                    R.id.nav_contacts -> {}
//                }
//                true
//            }


    }
}