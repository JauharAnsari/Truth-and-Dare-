package com.example.truthdare

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.animation.content.ModifierContent
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Modifier
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var questions: List<String>
    lateinit var questionDisplay: TextView
    lateinit var friendTruthQuestions:String

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // var openActivity = findViewById<Button>(R.id.truthButton)


        var RunButton = findViewById<TextView>(R.id.Run)
        var animation = findViewById<LottieAnimationView>(R.id.myAnimation)
        questionDisplay = findViewById(R.id.qustion)
        var TruthButton = findViewById<TextView>(R.id.truthButton)
        var DareButton = findViewById<TextView>(R.id.dareButton)
        var emoji = findViewById<CircleImageView>(R.id.circleImageView2)

        TruthButton.isClickable=false
        DareButton.isClickable=false
        animation.scaleX=2.0f
        animation.scaleY=2.0f




 
        var MyPosition: Float = 0.0f
        RunButton.setOnClickListener {
         questionDisplay.text=""
            emoji.isVisible=false
            animation.playAnimation()
            animation.loop(true)
            Handler(Looper.getMainLooper()).postDelayed({



                animation.progress = MyPosition
                animation.pauseAnimation()


                MyPosition += 0.20f

                if (MyPosition > 0.75f) {
                    animation.reverseAnimationSpeed()
                    var Reverse = animation.reverseAnimationSpeed()
                    if (animation.reverseAnimationSpeed() == Reverse) {
                        MyPosition = 0.15f

                    }

                }
                questionDisplay.text="Please Choose Truth or Dare"

                TruthButton.isClickable=true
                DareButton.isClickable=true
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



            }, 4000)
        }



    }


    private fun loadQuestionsForTruth(): MutableList<String> {
        val questionsList = mutableListOf<String>()
        val friendDareFile = intent.getStringExtra("TRUTH")
        if (friendDareFile != null) {
            val reader = BufferedReader(InputStreamReader(assets.open(friendDareFile)))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let { questionsList.add(it) }
            }
            reader.close()
            return questionsList
        }
        return TODO("Provide the return value")
    }

    private fun showRandomQuestion() {
        if (questions.isNotEmpty()) {
            val randomIndex = Random.nextInt(questions.size)
            questionDisplay.text = questions[randomIndex]


        }
    }
    private fun loadQuestionsForDare(): MutableList<String> {
        val questionsList = mutableListOf<String>()
        val friendDareFile = intent.getStringExtra("DARE")

        if (friendDareFile != null) {
            val reader = BufferedReader(InputStreamReader(assets.open(friendDareFile)))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let { questionsList.add(it) }
            }
            reader.close()
            return questionsList
        }
        return TODO("Provide the return value")
    }
    }





