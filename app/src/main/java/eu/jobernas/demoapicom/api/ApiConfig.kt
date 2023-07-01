package eu.jobernas.demoapicom.api

object ApiConfig {
    const val url: String = "https://flight-radar1.p.rapidapi.com"

    object Connection {
        const val CONNECT_TIMEOUT = 30L
        const val READ_TIMEOUT = 30L
        const val WRITE_TIMEOUT = 60L
    }

    object Headers {
        const val API_KEY = "X-RapidAPI-Key"
        const val API_HOST = "X-RapidAPI-Host"

        object Values {
            // TODO: This value here is not secure, dont store in Git or leave it like this in app
            val API_KEY = AppKeys.getApiKey()
            const val API_HOST = "flight-radar1.p.rapidapi.com"
        }
    }
}