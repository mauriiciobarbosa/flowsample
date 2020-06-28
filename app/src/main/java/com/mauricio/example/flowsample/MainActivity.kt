package com.mauricio.example.flowsample

import android.os.Bundle
import android.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenStarted {
            ('D'..'Z').asFlow()
                .onStart {
                    delay(1000)
                }
                .onEach {
                    delay(500)
                }
                .collect {
                    val newText = text_c.text.toString() + it
                    text_c.text = newText
                    TransitionManager.beginDelayedTransition(root)
                }
        }
    }
}