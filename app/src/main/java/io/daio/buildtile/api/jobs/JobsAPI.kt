package io.daio.buildtile.api.jobs

import io.daio.buildtile.api.model.Job
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface JobsAPI {

    @GET("/api/v1/jobs")
    fun jobs(@Query("api_token") apiToken: String?): Call<List<Job>>

    @GET("/api/v1/jobs/{jobId}")
    fun job(@Path("jobId") jobId: String?, @Query("api_token") apiToken: String?): Call<Job>
}