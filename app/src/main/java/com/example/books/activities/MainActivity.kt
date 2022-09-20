package com.example.books.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.books.R

class MainActivity : AppCompatActivity() {

    val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}