package com.example.actividad2

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.intent.rule.IntentsTestRule
import com.example.actividad2.presentation.DescripcionTareaActivity
import com.example.actividad2.presentation.adapter.AdapterTareas
import org.junit.Rule
import org.junit.Test


class AdapterTest {
   // @get:Rule
   // val intentsTestAdapterTareas = IntentsTestRule(AdapterTareas::class.java)
    @Test
    fun intentfuntion(){
      //  val i:Intent= Intent(intentsTestAdapterTareas,DescripcionTareaActivity::class.java)
       // intentsTestAdapterTareas.launchActivity(i)
    }

    @Test
    fun recycleView(){
        onView(withId(R.id.recycleViewTareas))
            .perform(RecyclerViewActions.actionOnItemAtPosition<AdapterTareas.viewHolder>(0, click()))
    }


}