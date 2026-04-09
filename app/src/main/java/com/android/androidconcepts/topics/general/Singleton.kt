package com.android.androidconcepts.topics.general

class Singleton private constructor() {

    companion object {
        @Volatile
        private var instance: Singleton? = null

        fun getInstance(): Singleton {
            return instance ?: synchronized(this) {
                instance ?: Singleton().also { instance = it }
            }
        }
    }

    fun showMessage(){
        println("Thread safe Singleton class")
    }
}

fun main() {
    val obj = Singleton.getInstance()
    obj.showMessage()
}


/*
Why this is thread-safe?
    @Volatile → ensures visibility across threads
    synchronized → only one thread creates instance
    Double-check → avoids unnecessary locking (performance optimized)

Thread Safety Modes (Important for Interview)
    val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) // default, safe
    val instance by lazy(LazyThreadSafetyMode.PUBLICATION)  // multiple threads allowed initially
    val instance by lazy(LazyThreadSafetyMode.NONE)         // NOT thread-safe
 */
