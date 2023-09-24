package com.sidharth.vidyakhoj.util

import android.graphics.Color
import kotlin.random.Random

object RandomColorGenerator {

    fun generateRandomColor(): Int {
        val red = Random.nextInt(200, 256)
        val green = Random.nextInt(200, 256)
        val blue = Random.nextInt(200, 256)
        return Color.rgb(red, green, blue)
    }
}