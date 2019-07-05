package com.diagnosabanding

import android.animation.Animator

import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.BounceInterpolator
import com.diagnosabanding.diagnosis.DataChild
import org.jetbrains.anko.startActivity
class SplashScreen : AppCompatActivity() {

    private val DURATION : Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        startAnimation()
    }

    private fun startAnimation() {
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.duration = DURATION

        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                startActivity<DataChild>()
                finish()
            }
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}

        })
        valueAnimator.start()
    }
}
