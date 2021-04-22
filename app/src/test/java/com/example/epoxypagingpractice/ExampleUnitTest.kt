package com.example.epoxypagingpractice

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val a = "123"

        when(a){
            "123" -> print("yaa")
            "222" ->print("555")
            else -> print("454")
        }

    }
}