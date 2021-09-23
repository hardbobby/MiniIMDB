package com.hardbobby.miniimdb.presentation.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hardbobby.miniimdb.presentation.common.Event

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */

inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit
) {
    observe(owner, Observer { it?.getContentIfNotHandled()?.let(onEventUnhandledContent) })
}

/**
 * Shortcut for posting event on MutableLiveData<Event>
 */
fun <T> MutableLiveData<Event<T>>.postEvent(
    event: T
) {
    postValue(Event(event))
}

typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>
typealias LiveEvent<T> = LiveData<Event<T>>