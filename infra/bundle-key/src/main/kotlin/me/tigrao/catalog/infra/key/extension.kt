package me.tigrao.catalog.infra.key

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.core.parameter.parametersOf

fun <T> Fragment.getArgs() = BundleKeyArgFactory.get<T>(requireArguments())

fun <T> SavedStateHandle.getArgs() = BundleKeyArgFactory.get<T>(this)

fun <T> SavedStateHandle.getArgsOrNull() = BundleKeyArgFactory.getOrNull<T>(this)

fun <T> Fragment.args() = lazy(LazyThreadSafetyMode.NONE) { getArgs<T>() }

inline fun <reified T : ViewModel> Fragment.viewModelState() =
    stateViewModel<T>(state = { arguments ?: Bundle() })
