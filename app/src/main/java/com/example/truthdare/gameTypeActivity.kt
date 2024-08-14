package com.example.truthdare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.widget.TextView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class gameTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_type)
        var friendAnimation = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        var coupleAnimation = findViewById<LottieAnimationView>(R.id.coupleAnimation)
        var groupAnimation = findViewById<LottieAnimationView>(R.id.groupAnimation)

        CoroutineScope(Dispatchers.Main).launch {
            friendAnimation.playAnimation()
            coupleAnimation.playAnimation()
            groupAnimation.playAnimation()
        }

        var friendButton = findViewById<TextView>(R.id.button)
        var coupleButton = findViewById<TextView>(R.id.coupleButton)
        var groupButton = findViewById<TextView>(R.id.groupButton)


 groupButton.setOnClickListener {

     var groupTruth = "grouptruth.txt"
     var groupDare = "groupdare.txt"
     var i = Intent(this,MainActivity::class.java)
     i.putExtra("TRUTH",groupTruth)
     i.putExtra("DARE",groupDare)
     startActivity(i)

 }

        friendButton.setOnClickListener {

            var friendTruthQuestions = "friendquestiontext.txt"
            var friendDareQuestion = "frienddare.txt"
            var i = Intent(this,MainActivity::class.java)
            i.putExtra("TRUTH",friendTruthQuestions)
            i.putExtra("DARE",friendDareQuestion)
            startActivity(i)

        }
        coupleButton.setOnClickListener {
            startActivity(Intent(this,coupleGameActivity::class.java))
        }




    }
}