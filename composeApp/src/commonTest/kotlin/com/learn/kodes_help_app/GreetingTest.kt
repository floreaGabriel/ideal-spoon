package com.learn.kodes_help_app

import kotlin.test.Test
import kotlin.test.assertTrue

class GreetingTest {
    
    @Test
    fun testGreetingContainsHello() {
        val greeting = Greeting()
        val result = greeting.greet()
        println("[DEBUG_LOG] Greeting result: $result")
        assertTrue(result.contains("Hello"), "Greeting should contain 'Hello'")
    }
}