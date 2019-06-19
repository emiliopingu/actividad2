package com.example.actividad2

import android.support.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.rule.ActivityTestRule
import com.example.actividad2.presentation.DescripcionTareaActivity
import org.junit.Rule
import org.junit.Test

class DescripcionTestAndroid {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<DescripcionTareaActivity>(DescripcionTareaActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun textInfo(){

        Espresso.onView(ViewMatchers.withId(R.id.tvNombreDescripcion)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvUsuario)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvLugarDescripcion)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvFechaDescripcion)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvDescripcionTarea)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}