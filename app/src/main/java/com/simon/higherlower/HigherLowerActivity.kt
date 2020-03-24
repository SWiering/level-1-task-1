package com.simon.higherlower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_higher_lower.*

class HigherLowerActivity : AppCompatActivity() {

    private var currentThrow: Int = 1
    private var lastThrow: Int = 1

    private lateinit var lastThrowText: TextView
    private lateinit var imageContainer: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_lower)

        lastThrowText = findViewById(R.id.lastThrowNumber)
        imageContainer = findViewById(R.id.diceImage)
        initViews()
    }

    /**
     * Set the initial (UI) state of the game.
     */
    private fun initViews() {
        butthonHigher.setOnClickListener{onHigherClick()}
        buttonLower.setOnClickListener{onLowerClick()}
        buttonEquals.setOnClickListener{onEqualClick()}

        updateUI()
    }

    /**
     * Update the last throw text and the dice image resource drawable with the current throw.
     */
    private fun updateUI() {
        lastThrowText.text = getString(R.string.last_throw, lastThrow)

        val theImage = resources.getIdentifier("dice$currentThrow", "drawable", packageName)
        diceImage.setImageResource(theImage)
    }

    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onHigherClick() {
        rollDice()

        if(currentThrow <= lastThrow) onAnswerIncorrect() else onAnswerCorrect()
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onLowerClick() {
        rollDice()

        if(currentThrow >= lastThrow) onAnswerIncorrect() else onAnswerCorrect()
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onEqualClick() {
        rollDice()
        if(currentThrow == lastThrow) onAnswerCorrect() else onAnswerIncorrect()
    }

    /**
     * Displays a successful Toast message.
     */
    private fun onAnswerCorrect() {
        // TODO: Make this from strings
        Toast.makeText(this@HigherLowerActivity, R.string.correct, Toast.LENGTH_SHORT).show()
    }

    /**
     * Displays a incorrect Toast message.
     */
    private fun onAnswerIncorrect() {
        // TODO: Make this from strings
        Toast.makeText(this@HigherLowerActivity, R.string.incorrect, Toast.LENGTH_SHORT).show()
    }
}
