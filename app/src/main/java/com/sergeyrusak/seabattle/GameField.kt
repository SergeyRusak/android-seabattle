package com.sergeyrusak.seabattle

import android.util.Log
import kotlin.random.Random

class GameField {
    var field = Array<Array<Int>>(10) {Array<Int>(10){0} }

    private fun checkAround(x: Int, y: Int, length: Int, orient: Int): Boolean{
        var startX = x - 1
        var startY = y - 1
        var endX = 0
        var endY = 0
        if (orient == 0){
            endX = x + 1
            endY = y + length
        }
        else{
            endX = x + length
            endY = y + 1
        }

        for (i in startX .. endX){
            for (j in startY.. endY){
                if (i >= 0 && j >= 0 && i < 10 && j < 10 && field[j][i] != 0){
                    return false
                }
            }
        }
        return true
    }

    private fun putship(type: Int){
        var ships = 0
        while (ships < 5-type){
            val x = kotlin.math.abs(Random.nextInt() % 10)
            val y = kotlin.math.abs(Random.nextInt() % 10)
            val dir = kotlin.math.abs(Random.nextInt() % 2)
            if (dir == 0){
                if (y >= 10+1-type){
                    continue
                }
                if (!checkAround(x, y, type, dir)){
                    continue
                }
                when(type){
                    4->{
                        field[y][x] = 1
                        field[y + 1][x] = 5
                        field[y + 2][x] = 5
                        field[y + 3][x] = 3}
                    3->{
                        field[y][x] = 1
                        field[y + 1][x] = 5
                        field[y + 2][x] = 3
                    }
                    2->{
                        field[y][x] = 1
                        field[y + 1][x] = 3
                    }
                    1->{field[y][x] = 7}
                }
                ships++
            }
            else{
                if (x >= 10+1-type){
                    continue
                }
                if (!checkAround(x, y, type, dir)){
                    continue
                }
                when(type){
                    4->{
                        field[y][x] = 4
                        field[y][x + 1] = 6
                        field[y][x + 2] = 6
                        field[y][x + 3] = 2
                    }
                    3->{
                        field[y][x] = 4
                        field[y][x + 1] = 6
                        field[y][x + 2] = 2
                    }
                    2->{
                        field[y][x] = 4
                        field[y][x + 1] = 2
                    }
                    1->{field[y][x] = 7}
                }
                ships++
            }
        }
    }


    fun generate(){
        field = Array<Array<Int>>(10) {Array<Int>(10){0} }
        putship(4)
        putship(3)
        putship(2)
        putship(1)
        for (i in 0 until 10){
            var str = ""
            for (j in 0 until 10){
                str += "${field[i][j]} "
            }
            Log.d("mylog", str)
        }
    }
}