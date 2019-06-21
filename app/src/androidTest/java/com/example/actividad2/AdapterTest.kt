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
import android.provider.ContactsContract
import androidx.test.espresso.intent.Intents.intending
import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.net.Uri
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import android.R
import android.support.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.rule.ActivityTestRule


class AdapterTest {
   // @get:Rule
   // val intentsTestAdapterTareas = IntentsTestRule(AdapterTareas::class.java)
    @Rule
   @JvmField
   var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun intentfuntion(){
      //  val i:Intent= Intent(intentsTestAdapterTareas,DescripcionTareaActivity::class.java)
       // intentsTestAdapterTareas.launchActivity(i)
    }

    @Test
    fun recycleView(){
       onView(withId(com.example.actividad2.R.id.recycleViewTareas)).perform(RecyclerViewActions.actionOnItemAtPosition<AdapterTareas.viewHolder>(0, click()))
    }

    @Before
    fun stubContactIntent() {
        val intent = Intent()
        val result = ActivityResult(Activity.RESULT_OK, intent)
        intending(
            allOf(
                hasData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI),
                hasAction(Intent.ACTION_PICK)
            )
        ).respondWith(result)
    }

    @Test
    fun Intents() {
        androidx.test.espresso.Espresso.onView(withId(com.example.actividad2.R.id.bFormularioEnviar)).perform(ViewActions.click())
        intended(allOf(hasAction(Intent.ACTION_DIAL), hasData("tareaDescripcion")))
        intended(allOf(hasAction(Intent.ACTION_DIAL), hasData("usuarioDescripcion")))
        intended(allOf(hasAction(Intent.ACTION_DIAL), hasData("problemaDescripcion")))
        intended(allOf(hasAction(Intent.ACTION_DIAL), hasData("lugarDescripcion")))
        intended(allOf(hasAction(Intent.ACTION_DIAL), hasData("fechaDescripcion")))

    }
}