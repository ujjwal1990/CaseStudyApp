package com.kc.casestudyapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.kc.casestudyapp.ui.MainActivity
import com.kc.casestudyapp.ui.casestudy.CaseStudyListItemsViewHolder
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CaseStudiesListFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.loader_view)).perform(WaitUntilGoneAction(3000L))
        onView(withId(R.id.case_study_list_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_ListItems() {
        var itemCount = 0
        activityRule.scenario.onActivity { activity ->
            val rv = activity.findViewById<RecyclerView>(R.id.case_study_list_view)
            itemCount = rv.adapter?.itemCount!!
        }
        onView(withId(R.id.case_study_list_view)).perform(
            RecyclerViewActions.scrollToPosition<CaseStudyListItemsViewHolder>(itemCount - 1)
        ).check(matches(anything()))

        //checking 1st item
        onView(withId(R.id.case_study_list_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CaseStudyListItemsViewHolder>(
                0, ViewActions.click()
            )
        )
        val firstItemText = "Testing Tube brakes, with TfL Decelerator"
        onView(withText(firstItemText)).check(matches(isDisplayed()))

        //checking 1st item
        onView(withId(R.id.case_study_list_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CaseStudyListItemsViewHolder>(
                itemCount - 1, ViewActions.click()
            )
        )
        val lastItemText = "Reimagining financial services in a post-plastic world"
        onView(withText(lastItemText)).check(matches(isDisplayed()))
    }
}
