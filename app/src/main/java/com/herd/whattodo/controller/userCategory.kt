package com.herd.whattodo.controller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.herd.whattodo.R
import kotlinx.android.synthetic.main.digital_activities.*
import java.util.*

class userCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_category)

        val sharedPrefs = getSharedPreferences("key", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPrefs.getString("THINGSLIST", null)
        val type = object : TypeToken<ArrayList<String>>(){}.type
        val storedArray:ArrayList<String> = gson.fromJson(json, type)

        decideBtn.setOnClickListener {
            //pick a random thing
            val random = Random()
            val randomThing = random.nextInt(storedArray.count())
            selectedThing.text = storedArray[randomThing]
            Log.d("Main", "this is a test log")

        }
    }
}