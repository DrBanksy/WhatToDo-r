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

class OutdoorActivities : AppCompatActivity() {
    val thingsList = arrayListOf("Go for a run", "Go for a walk", "Go fishing", "Plant a garden", "Fly a kite", "Hang out with a friend", "Plan a picnic in the park", "Enjoy a hiking adventure", "Go to your local skatepark", "Have a BBQ", "Take a road trip", "Go to a driving range", "Take up a new skill", "Mow the lawn", "Go for a bike ride")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.outdoor_activities)

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