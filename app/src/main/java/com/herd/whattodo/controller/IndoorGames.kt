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

class IndoorGames : AppCompatActivity() {

    val thingsList = arrayListOf("Hangman", "Indoor bowling", "Balancing beam", "Puzzles", "Card games", "Board game", "A lie and Two Truths", "Who am I?", "Pictionary", "Charades", "Play a video game", "Cards against humanity", "Twister", "Never Have I Ever", "Have a mini baking contest", "Complete a Jigsaw puzzle", "Lego competition", "Hide and Seek")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indoor_games)

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