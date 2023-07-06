package eu.jobernas.demoapicom

import com.google.common.truth.Truth.assertThat
import eu.jobernas.demoapicom.core.SecretUtils
import org.junit.Test

class SecretTests {

    companion object {
        private val TAG = SecretTests::class.java.simpleName
        private const val testValue: String = "Hello World"

        private const val secretOne: String = "Password1@"
        private const val secretTwo: String = "1920310239"
    }

    @Test
    fun testWithOneSecret() {
        println("$TAG::testWithOneSecret: $testValue")
        val afterFirstSecret = SecretUtils.encode(secretOne, testValue)
        println("$TAG::afterFirstSecret: $afterFirstSecret")
        val decodedFirstSecret = SecretUtils.decode(secretOne, afterFirstSecret)
        println("$TAG::decodedFirstSecret: $decodedFirstSecret")
        assertThat(decodedFirstSecret).isEqualTo(testValue)
    }

    @Test
    fun testWithTwoSecret() {
        println("$TAG::testWithTwoSecret: $testValue")
        val afterFirstSecret = SecretUtils.encode(secretOne, testValue)
        println("$TAG::afterFirstSecret: $afterFirstSecret")
        val afterSecondSecret = SecretUtils.encode(secretTwo, afterFirstSecret)
        println("$TAG::afterSecondSecret: $afterSecondSecret")
        val decodedSecondSecret = SecretUtils.decode(secretTwo, afterSecondSecret)
        println("$TAG::decodedSecondSecret: $decodedSecondSecret")
        val decodedFirstSecret = SecretUtils.decode(secretOne, decodedSecondSecret)
        println("$TAG::decodedFirstSecret: $decodedFirstSecret")
        assertThat(decodedFirstSecret).isEqualTo(testValue)
    }
}