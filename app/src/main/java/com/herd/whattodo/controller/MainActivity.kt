package com.herd.whattodo.controller

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.reflect.TypeToken
import com.herd.whattodo.R
import com.herd.whattodo.adapters.CategoryAdapter
import com.herd.whattodo.services.DataService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var adapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val myPrefs = getSharedPreferences("key", 0)
        val bCheck = myPrefs.getBoolean("ButtonReceiverState", false)

        if(bCheck == true) {
            val play = findViewById<Button>(R.id.myCategoryBtn)
//                play.visibility = View.VISIBLE
            play.background.alpha = 200
        } else {
            val play = findViewById<Button>(R.id.myCategoryBtn)
            play.background.alpha = 100
        }
        //checking if user is connected to the internet
        val connection = isOnline(this)
        if(!connection) {
            //Toast.makeText(this, "No internet",Toast.LENGTH_LONG).show()
            val builder = AlertDialog.Builder(this)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setMessage("Please check your internet connection")
            builder.setPositiveButton("Close"){dialog, which ->
                finish()
            }
            //show the dialog
            val dialog: AlertDialog = builder.create()
            //prevents back button from skipping it
            dialog.setCancelable(false)
            //prevents touch from skipping it
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }

        customList.setOnClickListener {
            val intent = Intent(this, CustomList::class.java)
            startActivity(intent)
        }
        
        myCategoryBtn.setOnClickListener {
            //checking if there is anything stored in SharedPreferences using a boolean variable
            //false == null
            val myPrefs = getSharedPreferences("key", 0)
            val bCheck = myPrefs.getBoolean("ButtonReceiverState", false)

            if(bCheck == true) {
                val intent = Intent(this, userCategory::class.java)
                startActivity(intent)
//                var play = findViewById<Button>(R.id.myCategoryBtn)
//                play.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Need to make a category first!", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        adapter = CategoryAdapter(this, DataService.topics){category->
            if(category.name == "Outdoor Activities")
            {
                val intent = Intent(this, OutdoorActivities::class.java)
                startActivity(intent)
            } else if (category.name == "Digital Activities") {
                val intent = Intent(this, DigitalActivities::class.java)
                startActivity(intent)
            } else if (category.name == "Activities for kids") {
                val intent = Intent(this, kidsActivities::class.java)
                startActivity(intent)
            } else if (category.name == "Indoor Games") {
                val intent = Intent(this, IndoorGames::class.java)
                startActivity(intent)
            }

        }

        category_recycler_view.adapter = adapter

        //returns an integer number 1 is portrait and 2 is landscape
        var spanCount = 2
        val orientation = resources.configuration.orientation
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //so if its landscape we want three columns
            spanCount = 3
        }

        val screenSize = resources.configuration.screenWidthDp
        if(screenSize > 720) {
            spanCount = 2
        }

        //setting layout manager
        val viewManager = GridLayoutManager(this, spanCount)
        category_recycler_view.layoutManager = viewManager
        category_recycler_view.setHasFixedSize(true)


    }

    override fun onResume() {
        super.onResume()
        val myPrefs = getSharedPreferences("key", 0)
        val bCheck = myPrefs.getBoolean("ButtonReceiverState", false)

        if(bCheck == true) {
            val play = findViewById<Button>(R.id.myCategoryBtn)

            play.background.alpha = 200
        } else {
            val play = findViewById<Button>(R.id.myCategoryBtn)
            play.background.alpha = 100
        }
    }
}

