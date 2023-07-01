package eu.jobernas.demoapicom.api

object AppKeys {

    init {
        System.loadLibrary("app-keys")
    }

    /**
     * Get api keys
     *
     * @return
     */
    external fun getApiKey(): String

}