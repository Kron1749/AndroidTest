package com.app.androidtest

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceComparerTest {

    private lateinit var resourceComaper: ResourceComparer

    @Before
    fun setup(){
        resourceComaper = ResourceComparer()
    }


    @Test
    fun stringResourceSameAsGivenString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComaper.isEqual(context, R.string.app_name, "AndroidTest")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferrentAsGivenString_returnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComaper.isEqual(context, R.string.app_name, "Some random string")
        assertThat(result).isFalse()
    }

}