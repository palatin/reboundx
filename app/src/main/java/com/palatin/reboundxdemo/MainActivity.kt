package com.palatin.reboundxdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.palatin.reboundx.ReboundController
import com.palatin.reboundx.rebound
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val scaleAnimator = scale.rebound(10.0, 2.0)
            .setStartValue(0.6)
            .setEndValue(1.0)
            .setReboundListener(object : ReboundController.ReboundListener {
                override fun onEnd() {
                    scale.performClick()
                }
            })
            .scale()
        scale.setOnClickListener {
            scaleAnimator.start()
        }
        val alphaAnimator = alpha.rebound(0.2, 50.0).setStartValue(0.0)
            .setEndValue(1.0)
            .setReboundListener(object : ReboundController.ReboundListener {
                override fun onEnd() {
                    alpha.performClick()
                }
            })
            .alpha()
        alpha.setOnClickListener {
            alphaAnimator.start()
        }

        val rotationAnimator = rotation.rebound(1.0, 1.0)
            .setRestSpeedThreshold(2.0)
            .setRestDisplacementThreshold(1.0)
            .setReboundListener(object : ReboundController.ReboundListener {
                override fun onStart() {
                    rotation.isClickable = false
                }
                override fun onEnd() {
                    rotation.isClickable = true
                    rotation.performClick()
                }
            }).rotate()
        rotation.setOnClickListener {
            rotationAnimator.apply {
                controller().setStartValue(rotation.rotation.toDouble())
                    .setEndValue(rotation.rotation.toDouble() + 90.0)
            }.start()
        }
        scale.performClick()
        alpha.performClick()
        rotation.performClick()

    }
}
