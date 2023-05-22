package com.sergeyrusak.seabattle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GameView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var paint: Paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null){
            return
        }
        var strokeWidth = 6f
        val padding = 100f
        paint.strokeWidth = strokeWidth
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE

        paint.textSize = 60f
        paint.textAlign = Paint.Align.CENTER

        val startX = padding
        val startY = (height - width) / 2f + padding
        val endX = startX + width - 2 * padding
        val endY = startY + width - 2 * padding

        canvas.drawRect(startX, startY, endX, endY, paint)

        for (i in 0 until 10){
            paint.strokeWidth = 2f
            paint.color = Color.BLUE
            val newX = startX + (endX - startX) / 10f * i + strokeWidth / 2
            val newY = startY + (endY - startY) / 10f * i + strokeWidth / 2
            canvas.drawLine(startX, newY, endX, newY, paint)
            canvas.drawLine(newX, startY, newX, endY, paint)
            paint.color = Color.WHITE
            paint.strokeWidth = 4f
            canvas.drawText("${i + 1}", newX + (endX - startX) / 30f, startY - (endY - startY) / 20f, paint)
            if (('А' + i).toChar() == 'Й'){
                canvas.drawText("${('А' + i + 1).toChar()}", startX - (endX - startX+10) / 15f, newY + (endY - startY) / 13f, paint)
            }
            else{
                canvas.drawText("${('А' + i).toChar()}", startX - (endX - startX+10) / 15f, newY + (endY - startY) / 13f, paint)
            }

        }
        val gameField = GameField()
        gameField.generate()

        strokeWidth = 12f
        paint.strokeWidth = strokeWidth
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        for (i in 0 until 10){
            for (j in 0 until 10){
                val cellXStart = startX + (endX - startX) / 10f * i //+ strokeWidth / 2
                val cellYStart = startY + (endY - startY) / 10f * j //+ strokeWidth / 2
                val cellXEnd = startX + (endX - startX) / 10f * (i + 1) //+ strokeWidth / 2
                val cellYEnd = startY + (endY - startY) / 10f * (j + 1) //+ strokeWidth / 2

                when(gameField.field[j][i]){
                    1 -> {
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,1)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,2)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,4)
                    }
                    2 -> {
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,1)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,2)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,3)
                    }
                    3 -> {
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,2)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,3)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,4)
                    }
                    4 -> {
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,1)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,3)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,4)
                    }
                    5 -> {
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,2)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,4)
                    }
                    6 -> {
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,1)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,3)
                    }
                    7 -> {
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,1)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,2)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,3)
                        draw(canvas, cellXStart, cellYStart, cellXEnd, cellYEnd, strokeWidth,4)
                    }
                }
            }
        }
    }

    fun draw(canvas: Canvas, cellXStart: Float, cellYStart: Float, cellXEnd: Float,
              cellYEnd: Float, strokeWidth: Float, type:Int){
        when(type){
       1-> canvas.drawLine(cellXStart - strokeWidth / 2, cellYStart, cellXEnd + strokeWidth / 2, cellYStart, paint)

       2-> canvas.drawLine(cellXEnd, cellYStart - strokeWidth / 2, cellXEnd, cellYEnd + strokeWidth / 2, paint)

       3-> canvas.drawLine(cellXStart - strokeWidth / 2, cellYEnd, cellXEnd + strokeWidth / 2, cellYEnd, paint)

       4-> canvas.drawLine(cellXStart, cellYStart - strokeWidth / 2, cellXStart, cellYEnd + strokeWidth / 2, paint)
    }
    }

}