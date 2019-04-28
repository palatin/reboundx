package com.palatin.reboundx

import android.view.View
import com.facebook.rebound.*
import java.lang.ref.WeakReference

open class ReboundController(view: View) {

    constructor(view: View, tension: Double, friction : Double) : this(view) {
        springConfig = SpringConfig.fromOrigamiTensionAndFriction(tension, friction)
    }

    protected val mView = WeakReference(view)
    var startValue: Double = 0.0
    var endValue: Double = 1.0
    protected var springConfig: SpringConfig = SpringConfig.defaultConfig
    var listener: ReboundListener?  = null
    var restSpeedThreshold: Double? = null
    var restDisplacementThreshold: Double? = null

    companion object {
        private val springSystem: SpringSystem by lazy { SpringSystem.create() }
    }


    fun translateX(): ReboundAnimator {
        return ReboundAnimator(initSpring().also {
            setSpringListener(it) {
                mView.get()?.translationX = it.toFloat()
            }
        })
    }

    fun translateY(): ReboundAnimator {
        return ReboundAnimator(initSpring().also {
            setSpringListener(it) {
                mView.get()?.translationY = it.toFloat()
            }
        })
    }


    fun translate(): ReboundAnimator {
        return ReboundAnimator(initSpring().also {
            setSpringListener(it) {
                mView.get()?.translationX = it.toFloat()
                mView.get()?.translationY = it.toFloat()
            }
        })
    }

    fun scaleX(): ReboundAnimator {
        return ReboundAnimator(initSpring().also {
            setSpringListener(it) {
                mView.get()?.scaleX = it.toFloat()
            }
        })
    }

    fun scaleY(): ReboundAnimator {
        return ReboundAnimator(initSpring().also {
            setSpringListener(it) {
                mView.get()?.scaleY = it.toFloat()
            }
        })
    }

    fun scale(): ReboundAnimator {
        return ReboundAnimator(initSpring().also {
            setSpringListener(it) {
                mView.get()?.scaleX = it.toFloat()
                mView.get()?.scaleY = it.toFloat()
            }
        })
    }

    fun alpha(): ReboundAnimator {
        return ReboundAnimator(initSpring().also {
            setSpringListener(it) {
                mView.get()?.alpha = it.toFloat()
            }
        })
    }


    fun rotateX(): ReboundAnimator {
        return ReboundAnimator(initSpring().also {
            setSpringListener(it) {
                mView.get()?.rotationX = it.toFloat()
            }
        })
    }

    fun rotateY(): ReboundAnimator {
        return ReboundAnimator(initSpring().also {
            setSpringListener(it) {
                mView.get()?.rotationY = it.toFloat()
            }
        })
    }


    fun rotate(): ReboundAnimator {
        return ReboundAnimator(initSpring().also {
            it.restSpeedThreshold = restSpeedThreshold ?: 2.0
            it.restDisplacementThreshold = restDisplacementThreshold ?: 1.0
            setSpringListener(it) {
                mView.get()?.rotation = it.toFloat()
            }
        })
    }

    fun setStartValue(value: Double): ReboundController {
        this.startValue = value
        return this
    }

    fun setEndValue(value: Double): ReboundController {
        this.endValue = value
        return this
    }

    fun setRestSpeedThreshold(value: Double): ReboundController {
        restSpeedThreshold = value
        return this
    }

    fun setRestDisplacementThreshold(value: Double): ReboundController {
        restDisplacementThreshold = value
        return this
    }

    fun setReboundListener(listener: ReboundListener): ReboundController {
        this.listener = listener
        return this
    }

    private fun initSpring(): Spring {
        val spring = springSystem.createSpring()
        spring.restSpeedThreshold = restSpeedThreshold ?:  spring.restSpeedThreshold
        spring.restDisplacementThreshold = restDisplacementThreshold ?: spring.restDisplacementThreshold
        spring.springConfig = springConfig
        return spring
    }

    private fun setSpringListener(spring: Spring, action: (Double) -> Unit) {
        spring.addListener(object : SimpleSpringListener() {
            override fun onSpringUpdate(spring: Spring) {
                stopIfViewDoesNotExists(spring)
                action.invoke(spring.currentValue)
            }

            override fun onSpringActivate(spring: Spring?) {
                listener?.onStart()
            }

            override fun onSpringAtRest(spring: Spring?) {
                listener?.onEnd()
            }
        })
    }

    private inline fun stopIfViewDoesNotExists(spring: Spring) {
        if(mView.get() == null) {
            spring.removeAllListeners()
            spring.destroy()
            return
        }
    }

    interface ReboundListener {
        fun onStart() {}
        fun onEnd() {}
    }

    inner class ReboundAnimator(private val spring: Spring) {


        fun start() {
            spring.currentValue = startValue
            spring.endValue = endValue
        }

        fun stop() {
            spring.currentValue = spring.endValue
        }

        fun controller(): ReboundController = this@ReboundController
    }
}

fun View.rebound(): ReboundController {
    return ReboundController(this)
}

fun View.rebound(tension: Double, friction : Double): ReboundController {
    return ReboundController(this, tension, friction)
}