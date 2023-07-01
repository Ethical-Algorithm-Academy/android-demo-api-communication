package eu.jobernas.demoapicom.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class CommonHeaderInterceptor: Interceptor {

	/**
	 * Static Methods
	 */
	companion object {
		// Constants
		private val TAG = CommonHeaderInterceptor::class.java.simpleName
	}

	override fun intercept(chain: Interceptor.Chain): Response {
		Log.d(TAG, "intercept::executed")
		val request = chain.request()
		val newRequestBuilder = request.newBuilder()
			.addHeader(ApiConfig.Headers.API_KEY, ApiConfig.Headers.Values.API_KEY)
			.addHeader(ApiConfig.Headers.API_HOST, ApiConfig.Headers.Values.API_HOST)
		return chain.proceed(newRequestBuilder.build())
	}
}