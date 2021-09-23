package com.hardbobby.miniimdb.presentation.utils

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true

fun Long?.orZero() = this ?: 0L

fun Double?.orZero() = this ?: 0.0

fun Int?.orZero() = this ?: 0