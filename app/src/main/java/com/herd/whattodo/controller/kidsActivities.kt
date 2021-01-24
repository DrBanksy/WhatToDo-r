package com.herd.whattodo.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.herd.whattodo.R
import kotlinx.android.synthetic.main.digital_activities.*
import java.util.*

class kidsActivities : AppCompatActivity() {


    val thingsList = arrayListOf("Indoor treasure hunt", "Build a fort with blankets and pillows", "Play a board game", "Google 'How to draw...'", "Play a card game", "Make paper airplanes", "Do some colouring", "Lego building contest", "Indoor/Outdoor treasure hunt", "Paint faces", "Make an indoor obstacle course", "Make sock puppets", "Paint a picture of your favourite animal", "Bake something with a parent", "Jigsaw Puzzle", "Do a science experiment", "Attempt a brain teaser", "Draw on a plain white t-shirt")
    val items = thingsList.count()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kids_activities)
        println("number of items = $items")
        selectedThing.text = ""

        //if true is returned they are connected, if false is returned no internet
        val connection = isOnline(this)

        if(!connection) {
            //Toast.makeText(this, "No internet",Toast.LENGTH_LONG).show()
            val builder = AlertDialog.Builder(this)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setMessage("Please check your internet connection")
            builder.setPositiveButton("Close"){dialog, which ->
                finishAffinity()
            }
            //show the dialog
            val dialog: AlertDialog = builder.create()
            //prevents back button from skipping it
            dialog.setCancelable(false)
            //prevents touch from skipping it
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }

        val mAdView : AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        decideBtn.setOnClickListener {
            //pick a random thing
            val random = Random()
            val randomThing = random.nextInt(thingsList.count())
            selectedThing.text = thingsList[randomThing]
            Log.d("Main", "this is a test log")

        }
    }
}