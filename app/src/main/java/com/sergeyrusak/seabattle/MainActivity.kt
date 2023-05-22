package com.sergeyrusak.seabattle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {
    lateinit var gameView: GameView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameView = findViewById<GameView>(R.id.gameView)
    }

    fun onClick(view: View) {
        gameView.invalidate()
    }
}