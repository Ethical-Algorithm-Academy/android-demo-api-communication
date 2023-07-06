package eu.jobernas.demoapicom.core

import android.util.Base64
import java.nio.charset.StandardCharsets
import kotlin.experimental.xor

/**
 * Secret utils
 *
 * @constructor Create empty Secret utils
 */
object SecretUtils {

    /**
     * Encode
     *
     * @param key
     * @param value
     * @return
     */
    fun encode(key: String, value: String): String = Base64.encodeToString(applySecret(key, value), Base64.DEFAULT)

    /**
     * Decode
     *
     * @param key
     * @param encoded
     * @return
     */
    fun decode(key: String, encoded: String): String {
        val bytes = Base64.decode(encoded, Base64.DEFAULT)
        return String(applySecret(key, String(bytes, StandardCharsets.UTF_8)), StandardCharsets.UTF_8)
    }

    /**
     * Apply secret
     *
     * @param key
     * @param value
     * @return
     */
    private fun applySecret(key: String, value: String): ByteArray {
        val keyByteArray = key.toByteArray(StandardCharsets.UTF_8)
        val resultBytes: MutableList<Byte> = mutableListOf()
        val bytes = value.toByteArray(StandardCharsets.UTF_8)
        bytes.forEachIndexed { index, byte ->
            resultBytes.add(keyByteArray[index % keyByteArray.size].xor(byte))
        }
        return resultBytes.toByteArray()
    }
}