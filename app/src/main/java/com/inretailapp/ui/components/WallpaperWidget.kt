package com.inretailapp.ui.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class WallpaperWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var paint       : Paint = Paint()

    private val triangle01 : Path  = Path()
    private val triangle02 : Path  = Path()
    private val triangle03 : Path  = Path()
    private val triangle04 : Path  = Path()
    private val triangle05 : Path  = Path()
    private val triangle06 : Path  = Path()
    private val triangle07 : Path  = Path()

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.rgb(33, 69, 89)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val widthScreen   = width.toFloat()
        val heightScreen  = height.toFloat()

        paint.color = Color.parseColor("#1A3C4C")
        canvas?.drawRect(0f, 0f, widthScreen, heightScreen, paint)

        triangle07.moveTo(0f, heightScreen * 0.20f)
        triangle07.lineTo(0f, heightScreen * 0.55f)
        triangle07.lineTo(widthScreen, heightScreen)
        triangle07.close()
        paint.color = Color.rgb(48, 157, 169)
        canvas?.drawPath(triangle07, paint)

        triangle06.moveTo(widthScreen, heightScreen * 0.03f)
        triangle06.lineTo(0f, heightScreen * 0.55f)
        triangle06.lineTo(widthScreen, heightScreen)
        triangle06.close()
        paint.color = Color.rgb(33, 69, 89)
        canvas?.drawPath(triangle06, paint)

        triangle05.moveTo(widthScreen, heightScreen * 0.15f)
        triangle05.lineTo(0f, heightScreen * 0.75f)
        triangle05.lineTo(widthScreen, heightScreen)
        triangle05.close()
        paint.color = Color.rgb(113, 188, 196)
        canvas?.drawPath(triangle05, paint)

        triangle04.moveTo(widthScreen, heightScreen * 0.30f)
        triangle04.lineTo(0f, heightScreen * 0.85f)
        triangle04.lineTo(widthScreen, heightScreen)
        triangle04.close()
        paint.color = Color.rgb(134, 221, 232)
        canvas?.drawPath(triangle04, paint)

        triangle03.moveTo(widthScreen, heightScreen * 0.50f)
        triangle03.lineTo(0f, heightScreen)
        triangle03.lineTo(widthScreen, heightScreen)
        triangle03.close()
        paint.color = Color.rgb(240, 103, 49)
        canvas?.drawPath(triangle03, paint)


        triangle02.moveTo(0f, heightScreen * 0.55f)
        triangle02.lineTo(0f, heightScreen)
        triangle02.lineTo(widthScreen + 50f, heightScreen)
        triangle02.close()
        paint.color = Color.rgb(217, 93, 43)
        canvas?.drawPath(triangle02, paint)

        triangle01.moveTo(0f, heightScreen)
        triangle01.lineTo(0f, heightScreen * 0.60f)
        triangle01.lineTo(widthScreen * 0.95f, heightScreen)
        triangle01.close()
        paint.color = Color.rgb(184, 228, 227)
        canvas?.drawPath(triangle01, paint)

    }
}