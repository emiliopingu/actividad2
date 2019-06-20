package com.example.actividad2

import android.content.Intent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.example.actividad2.presentation.DescripcionTareaActivity


import org.junit.Rule
import org.junit.Test

class AdapterTest {
    @get:Rule
    val intentsTestRule = IntentsTestRule(DescripcionTareaActivity::class.java)

    @Test
    fun intentfuntion(){
        assertThat(intent).hasAction(Intent.ACTION_VIEW)
        assertThat(intent).categories().containsExactly(Intent.CATEGORY_BROWSABLE)
        assertThat(intent).extras().containsKey("tareaDescripcion")
        assertThat(intent).extras().containsKey("usuarioDescripcion")
        assertThat(intent).extras().containsKey("problemaDescripcion")
        assertThat(intent).extras().containsKey("lugarDescripcion")
        assertThat(intent).extras().containsKey("fechaDescripcion")
        

    }

}