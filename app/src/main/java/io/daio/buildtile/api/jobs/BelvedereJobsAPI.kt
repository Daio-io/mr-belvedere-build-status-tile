package io.daio.buildtile.api.jobs

import io.daio.buildtile.Constants
import io.daio.buildtile.api.model.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BelvedereJobsAPI(private val apiKey: String) {

    private val api: JobsAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BELVEDERE_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        api = retrofit.create(JobsAPI::class.java)
    }

    fun jobs(success: ((List<Job>?) -> Unit)? = null, failure: ((Throwable?) -> Unit)? = null) {

        api.jobs(apiKey).enqueue(object: Callback<List<Job>> {

            override fun onFailure(call: Call<List<Job>>?, t: Throwable?) {
                failure?.invoke(t)
            }

            override fun onResponse(call: Call<List<Job>>?, response: Response<List<Job>>?) {
                success?.invoke(response?.body())
            }

        })

    }

}