package me.tigrao.catalog.infra.key

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle

private const val FRAGMENT_KEY = "fragment:key"

object BundleKeyArgFactory {

    fun create(any: Any) = bundleOf(FRAGMENT_KEY to any)

    fun <T> get(bundle: Bundle) = bundle.get(FRAGMENT_KEY) as T

    fun <T> get(savedStateHandle: SavedStateHandle): T = savedStateHandle.get<T>(FRAGMENT_KEY)
        ?: throw IllegalArgumentException("Parameter should not be null here")

    fun <T> getOrNull(savedStateHandle: SavedStateHandle): T? =
        if (savedStateHandle.contains(FRAGMENT_KEY)) {
            savedStateHandle.get<T>(FRAGMENT_KEY)
        } else {
            null
        }
}
