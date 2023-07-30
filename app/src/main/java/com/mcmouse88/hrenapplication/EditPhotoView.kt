package com.mcmouse88.hrenapplication

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatImageView

class EditPhotoView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : AppCompatImageView(context, attributeSet) {

    private val path = Path()

    val rect = Rect(0, 0, 0, 0)
    val rectangleRect = RectF(0f, 0f, 0f, 0f)
    val squareRect = RectF(0f, 0f, 0f, 0f)

    private val backGroundColor = resources.getColor(R.color.bg_overlay, null)

    val paint = Paint().apply {
        color = backGroundColor
        style = Paint.Style.FILL
        isAntiAlias = true
        isDither = true
    }

    val paintText = Paint().apply {
        color = Color.WHITE
        textSize = 14f.toDp()
        textAlign = Paint.Align.CENTER
    }

    val paint2 = Paint().apply {
        color = Color.GREEN
    }

    private val roundCornerRight = resources.getDimension(R.dimen.roundCornerRight)
    private val roundCornerLeft = resources.getDimension(R.dimen.roundCornerLeft)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawRoundedSquare(canvas)
    }

    private fun drawCircularArea(canvas: Canvas) {
        path.reset()
        path.addCircle(
            width / 2f,
            height / 2f,
            (width - 32f.toDp()) / 2f,
            Path.Direction.CCW
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canvas.clipOutPath(path)
        } else {
            canvas.clipPath(path, Region.Op.DIFFERENCE)
        }
        rect.set(0, 0, width, height)
        canvas.drawRect(rect, paint)
    }

    private fun drawRoundedRectangle(canvas: Canvas) {
        path.reset()
        rectangleRect.set(16f.toDp(),height / 2 + 92f.toDp(), width - 16f.toDp(), height / 2 - 92f.toDp())
        path.addRoundRect(
            rectangleRect,
            16f.toDp(),
            16f.toDp(),
            Path.Direction.CCW
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canvas.clipOutPath(path)
        } else {
            canvas.clipPath(path, Region.Op.DIFFERENCE)
        }

        rect.set(0, 0, width, height)
        canvas.drawRect(rect, paint)
    }

    private fun drawRoundedSquare(canvas: Canvas) {
        path.reset()
        val squareSize = width - 32f.toDp()
        squareRect.set(16f.toDp(), (height / 2) - (squareSize / 2), width - 16f.toDp(), (height / 2) + (squareSize / 2))
        path.addRoundRect(
            squareRect,
            16f.toDp(),
            16f.toDp(),
            Path.Direction.CCW
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canvas.clipOutPath(path)
        } else {
            canvas.clipPath(path, Region.Op.DIFFERENCE)
        }

        rect.set(0, 0, width, height)
        canvas.drawRect(rect, paint)
    }

    private fun Float.toDp(): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            context.resources.displayMetrics
        )
    }
}