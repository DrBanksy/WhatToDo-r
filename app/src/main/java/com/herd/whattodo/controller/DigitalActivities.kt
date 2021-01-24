package com.herd.whattodo.controller

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.herd.whattodo.R
import kotlinx.android.synthetic.main.digital_activities.*
import java.util.*

class DigitalActivities : AppCompatActivity() {

    val thingsList = arrayListOf("Netflix", " Watch a stream on Twitch","Watch a TED Talk", "Watch a movie", "Listen to music", "Play online chess", "Tour the earth using Google Earth", "Listen to a podcast", "Start a blog/journal", "Explore Reddit", "Enroll in an online course", "Try out Duolingo")
    //private lateinit var mInterstitialAd: InterstitialAd


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.digital_activities)


        //selectedThing.text = thingsList.random()
        selectedThing.text = ""

        //checking to see if the user has an internet connection
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


//        mInterstitialAd = InterstitialAd(this)
//        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"

        Log.d("test123", "testing")

        val mAdView : AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
//        mAdView.visibility = View.INVISIBLE



        decideBtn.setOnClickListener {
            //pick a random thing
            val random = Random()

            val randomThing = random.nextInt(thingsList.count())
            selectedThing.text = thingsList[randomThing]
            Log.d("Main", "this is a test log")

        }



//            mInterstitialAd.loadAd(AdRequest.Builder().build())
//
//            if (mInterstitialAd.isLoaded) {
//                mInterstitialAd.show()
//                Log.d("test123", "test")
//            } else {
//                Log.d("test123", "The interstitial wasn't loaded yet.")
//            }






    }

}

public fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    //so if its not equal to null then its either connected to wifi or data
    if(connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            }
        }
    }
    return false
}

