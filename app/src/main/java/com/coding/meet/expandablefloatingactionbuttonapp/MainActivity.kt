package com.coding.meet.expandablefloatingactionbuttonapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var shareLL : View
    private lateinit var rateLL : View

    private var rotate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shareLL = findViewById(R.id.shareLL)
        rateLL = findViewById(R.id.rateLL)
        initShowOut(shareLL)
        initShowOut(rateLL)

        val shareFab = findViewById<FloatingActionButton>(R.id.shareFab)
        val rateFab = findViewById<FloatingActionButton>(R.id.rateFab)
        val settingFab = findViewById<FloatingActionButton>(R.id.settingFab)

        settingFab.setOnClickListener {
            toggleFabMode(it)
        }

        shareFab.setOnClickListener {
            Toast.makeText(this,"Share",Toast.LENGTH_LONG).show()
        }

        rateFab.setOnClickListener {
            Toast.makeText(this,"Rate Us",Toast.LENGTH_LONG).show()
        }

    }
    private fun initShowOut(v: View){
        v.apply {
            visibility = View.GONE
            translationY = height.toFloat()
            alpha = 0f
        }
    }
    private fun toggleFabMode(v: View) {
        rotate = rotateFab(v,!rotate)
        if (rotate){
            showIn(shareLL)
            showIn(rateLL)
        }else{
            showOut(shareLL)
            showOut(rateLL)
        }
    }

    private fun showOut(view: View) {
        view.apply {
            visibility = View.VISIBLE
            alpha = 1f
            translationY = 0f
            animate()
                .setDuration(200)
                .translationY(height.toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        visibility = View.GONE
                        super.onAnimationEnd(animation)
                    }
                })
                .alpha(0f)
                .start()
        }
    }

    private fun showIn(view: View) {
        view.apply {
            visibility = View.VISIBLE
            alpha = 0f
            translationY = height.toFloat()
            animate()
                .setDuration(200)
                .translationY(0f)
                .setListener(object : AnimatorListenerAdapter() {})
                .alpha(1f)
                .start()
        }
    }

    private fun rotateFab(v: View, rotate: Boolean): Boolean {
        v.animate()
            .setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {})
            .rotation(if (rotate) 180f else 0f)
        return rotate
    }
}