package com.example.semicircroundsqview

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

val parts : Int = 4
val scGap : Float = 0.02f / parts
val sizeFactor : Float = 3.9f
val delay : Long = 20
val colors : Array<Int> = arrayOf(
    "#F44336",
    "#673AB7",
    "#2196F3",
    "#FF5722",
    "#009688"
).map {
    Color.parseColor(it)
}.toTypedArray()
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawSemiCircRoundSq(scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = Math.min(w, h) / sizeFactor
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, parts)
    val sf2 : Float = sf.divideScale(1, parts)
    val sf3 : Float = sf.divideScale(2, parts)
    save()
    translate(w / 2, h / 2)
    drawRect(RectF(-size * sf1, -size * sf1, size * sf1, size * sf1), paint)
    save()
    translate(0f, -size)
    drawArc(RectF(-size / 2, -size / 2, size / 2, size / 2), 180f, 180f * sf2, true, paint)
    restore()
    paint.color = backColor
    for (j in 0..1) {
        save()
        translate(size * 0.25f * (1 - 2 * j), -size * 1.25f)
        drawCircle(0f, 0f, size * 0.1f * sf3, paint)
        restore()
    }
    restore()
}

fun Canvas.drawSCRSNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    drawSemiCircRoundSq(scale, w, h, paint)
}

class SemiCircRoundSqView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}