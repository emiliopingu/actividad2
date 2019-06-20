package com.example.actividad2

import android.content.Intent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.example.actividad2.data.Task
import com.example.actividad2.presentation.DescripcionTareaActivity
import com.example.actividad2.presentation.adapter.AdapterTareas


import org.junit.Rule
import org.junit.Test

class AdapterTest {
    @get:Rule
    val intentsTestAdapterTareas = IntentsTestRule(AdapterTareas::class.java)
    @Test
    fun intentfuntion(){
        val i:Intent= Intent(intentsTestAdapterTareas,DescripcionTareaActivity::class.java)
        intentsTestAdapterTareas.launchActivity(i)

    }

}