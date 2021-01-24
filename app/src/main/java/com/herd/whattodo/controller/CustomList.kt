package com.herd.whattodo.controller

import android.content.Context
import android.content.SharedPreferences
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.method.TextKeyListener.clear
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.herd.whattodo.R
import kotlinx.android.synthetic.main.activity_custom_list.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

class CustomList : AppCompatActivity() {

    val thingsList by lazy { mutableListOf<String>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list)

        val mAdView : AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        addItemBtn.setOnClickListener { 
            if(item_text_field.text.toString() == "") {
                Toast.makeText(this, "Must enter text first", Toast.LENGTH_SHORT).show()
            } else {
                thingsList.add(item_text_field.text.toString())
                println(thingsList)
                item_text_field.text.clear()
                Toast.makeText(this, "Activity has been added", Toast.LENGTH_SHORT).show()
            }
        }

        resetBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setMessage("Are you sure you want to reset")
            builder.setPositiveButton("Yes"){dialog, which ->
                thingsList.clear()
                println(thingsList)
                val preferences = getSharedPreferences("key", 0)
                preferences.edit().remove("THINGSLIST").commit()
                preferences.edit().remove("ButtonReceiverState").commit()

                Toast.makeText(this, "All items removed from list", Toast.LENGTH_SHORT).show()

            }
            builder.setNegativeButton("No"){dialog, which ->
                println(thingsList)
            }

            //show the dialog
            val dialog: AlertDialog = builder.create()
            //prevents back button from skipping it
            //dialog.setCancelable(false)
            //prevents touch from skipping it
            //dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }

        saveBtn.setOnClickListener {
            if(thingsList.count() > 0) {
                //sharedpref shit here
                val gson = Gson()
                val json = gson.toJson(thingsList)//converting list to json
                val settings = getSharedPreferences("key", Context.MODE_PRIVATE)
                val editor = settings.edit()
                editor.putString("THINGSLIST", json)
                editor.putBoolean("ButtonReceiverState", true)
                editor.apply()
                Toast.makeText(this, "Your category has been saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "There is nothing to save", Toast.LENGTH_SHORT).show()
            }
        }



    }


}



