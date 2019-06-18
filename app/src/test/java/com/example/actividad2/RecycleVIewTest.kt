package com.example.actividad2

import org.junit.Test


class RecycleVIewTest {


    @Test
    fun greeterSaysHello() {
        onView(withId(R.id.name_field)).perform(typeText("Steve"))
        onView(withId(R.id.greet_button)).perform(click())
        onView(withText("Hello Steve!")).check(matches(isDisplayed()))
    }
}






