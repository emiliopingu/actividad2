package com.example.actividad2
import android.app.AlarmManager
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.rule.ActivityTestRule
import com.example.actividad2.presentation.adapter.AdapterTareas
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import java.util.*


class MainActivityTestAndroid {



    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun clickBotonCrearTarea() {
        Espresso.onView(ViewMatchers.withId(R.id.bFormulario)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.bEliminar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun clickItem() {
            onView(withId(R.id.recycleViewTareas)).perform(RecyclerViewActions.actionOnItemAtPosition<AdapterTareas.viewHolder>(0, ViewActions.click()))
    }



    @Test
    fun AlarmManager() {
        lateinit var alarmManager: AlarmManager
        assertNotNull(alarmManager)
    }






    @Test
    fun empezarAlarma() {
        val c:Calendar= Calendar.getInstance()
        assertNotNull(c)
       val m= MainActivity()
        assertNotNull(m.empezarAlarma(c))


    }
}