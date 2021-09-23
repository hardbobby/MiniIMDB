package com.hardbobby.data.common

/**
 * Created by Bobby.Hardian on 23/09/2021.
 */
object Keys {
    init {
        System.loadLibrary("native-lib")
    }
    external fun apiKey(): String
    external fun baseUrl(): String
}