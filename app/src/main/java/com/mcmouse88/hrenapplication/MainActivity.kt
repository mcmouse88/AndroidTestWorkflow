package com.mcmouse88.hrenapplication

import android.graphics.Matrix
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.translationMatrix

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.iv_pic)

        val values = FloatArray(9)
        // values[Matrix.MSCALE_X] = 0.5f
        // values[Matrix.MSCALE_Y] = 0.5f
        // values[Matrix.MTRANS_X] = 500f
        // values[Matrix.MTRANS_Y] = 500f
        val matrix = Matrix().apply {
            // setValues(values)
           setScale(1.5f, 1.5f)
            translationMatrix(50f, 50f)
        }
        imageView.imageMatrix = matrix
    }
}

