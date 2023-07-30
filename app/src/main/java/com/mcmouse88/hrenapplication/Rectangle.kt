package com.mcmouse88.hrenapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes

class Rectangle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val rectangle = RectF()
    var backgroundColorRR: Int = 0

    private val paint = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        style = Paint.Style.FILL
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.Rectangle) {
            backgroundColorRR = getColor(R.styleable.Rectangle_backgroundColor, Color.RED)
        }
    }

    override fun onDraw(canvas: Canvas) {
        paint.color = backgroundColorRR
        rectangle.set(50f, 50f, 600f, 600f)
        canvas.drawRect(rectangle, paint)
    }
}