package me.tigrao.catalog.infra.key

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BundleKeyArgFactoryTest {

    private val subject = BundleKeyArgFactory
    val savedStateHandle = mockk<SavedStateHandle>()

    @Test
    fun create_returnCorrectInstance() {
        val expected = mockk<Parcelable>()

        val bundle = subject.create(expected)

        assertEquals(expected, bundle["fragment:key"])
    }

    @Test
    fun get_returnCorrectInstance() {
        val expected = mockk<Parcelable>()

        val bundle = subject.create(expected)

        val result = subject.get<Parcelable>(bundle)

        assertEquals(expected, result)
    }

    @Test
    fun get_savedStateBundlePresent_returnInstance() {
        val savedStateHandle = mockk<SavedStateHandle>()
        val parcelable = mockk<Parcelable>()
        every { savedStateHandle.get<Parcelable>("fragment:key") } returns parcelable

        val result = subject.get<Parcelable>(savedStateHandle)

        assertEquals(parcelable, result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun get_savedStateBundleNotPresent_throwException() {
        val savedStateHandle = mockk<SavedStateHandle>()
        every { savedStateHandle.get<Parcelable>("fragment:key") } returns null

        subject.get<String>(savedStateHandle)
    }

    @Test
    fun getOrNull_withoutArgument_returnNull() {
        prepare(
            contains = true,
            argument = null,
        )

        val result = subject.getOrNull<String>(savedStateHandle)

        assertNull(result)
    }

    @Test
    fun getOrNull_withArgument_returnARg() {
        val argument = "argument"
        prepare(
            contains = true,
            argument = argument,
        )

        val result = subject.getOrNull<String>(savedStateHandle)

        assertEquals(argument, result)
    }

    fun prepare(
        contains: Boolean,
        argument: String?,
    ) {
        every { savedStateHandle.contains("fragment:key") } returns contains
        every { savedStateHandle.get<String>("fragment:key") } returns argument
    }
}
