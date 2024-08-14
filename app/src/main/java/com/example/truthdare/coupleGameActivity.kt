package com.example.truthdare

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import de.hdodenhof.circleimageview.CircleImageView
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

class coupleGameActivity : AppCompatActivity() {
    lateinit var displayQuestion : TextView
    private lateinit var questions: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_couple_game)


        var RunButton = findViewById<TextView>(R.id.Run)
        var animation = findViewById<LottieAnimationView>(R.id.myAnimation)
       displayQuestion = findViewById(R.id.qustion)
        var TruthButton = findViewById<TextView>(R.id.truthButton)
        var DareButton = findViewById<TextView>(R.id.dareButton)
        var emoji = findViewById<CircleImageView>(R.id.circleImageView)


        DareButton.isClickable = false
        TruthButton.isClickable=false
       animation.scaleX=2.0f
        animation.scaleY=2.0f


        var stopingPosition = 0.00f
      RunButton.setOnClickListener {
          emoji.isVisible=false

          displayQuestion.text=""
          animation.playAnimation()

          Handler(Looper.getMainLooper()).postDelayed({

              if (stopingPosition==0.00f) {
                  stopingPosition=0.50f
                  animation.progress = stopingPosition
                  animation.pauseAnimation()
              }
              else{
                  if (stopingPosition==0.50f){
                      stopingPosition=0.00f
                      animation.progress=stopingPosition
                      animation.pauseAnimation()
                  }
              }
              displayQuestion.text="Please Choose Truth or Dare"
              DareButton.isClickable=true
              TruthButton.isClickable=true
              TruthButton.setOnClickListener {
                  emoji.setImageResource(R.drawable.happy)
                  emoji.isVisible=true


                  questions = loadQuestionsForTruth()
                  showRandomQuestion()
              }
              DareButton.setOnClickListener {
                  emoji.setImageResource(R.drawable.devil)
                  emoji.isVisible=true
                  questions = loadQuestionsForDare()
                  showRandomQuestion()
              }



          },4000)
      }



    }
    private fun loadQuestionsForTruth(): MutableList<String> {
        val questionsList = mutableListOf<String>()


        val coupleTruthQuestion = "couple.txt"
        val reader = BufferedReader(InputStreamReader(assets.open(coupleTruthQuestion)))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let { questionsList.add(it) }
            }
            reader.close()
            return questionsList

        return TODO("Provide the return value")
    }

    private fun loadQuestionsForDare(): MutableList<String> {
        val questionsList = mutableListOf<String>()
        val friendDareFile = intent.getStringExtra("FRIEND_DARE")
        var coupleDareQuestion = "coupledare.txt"

            val reader = BufferedReader(InputStreamReader(assets.open(coupleDareQuestion)))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let { questionsList.add(it) }
            }
            reader.close()
            return questionsList

        return TODO("Provide the return value")
    }

    private fun showRandomQuestion() {
        if (questions.isNotEmpty()) {
            val randomIndex = Random.nextInt(questions.size)
            displayQuestion.text = questions[randomIndex]


        }
    }
}