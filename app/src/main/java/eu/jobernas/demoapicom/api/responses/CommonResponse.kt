package eu.jobernas.demoapicom.api.responses

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommonResponse<U>(private val onResponse: OnResponse<U>?):
    Callback<U> {

    interface OnResponse<A> {
        fun onSuccess(response: A?)
        fun onError(t: Throwable)
    }

    override fun onResponse(call: Call<U>, response: Response<U>) {
        val code = response.code()
        val body = response.body()
        val errorBody = response.errorBody()
        when {
            body != null && code == 200 -> {
                onResponse?.onSuccess(body)
            }
            errorBody != null -> {
                // Error Response Decode
                val errorMessage = "" //TODO handleErrorMessage(request, errorBody)
                onResponse?.onError(Throwable(errorMessage))
            }
            else -> onResponse?.onError(Throwable("Some Unknown Error happen"))
        }
    }

    override fun onFailure(call: Call<U>, t: Throwable) {
        onResponse?.onError(Throwable("API Error", cause = t))
    }
}