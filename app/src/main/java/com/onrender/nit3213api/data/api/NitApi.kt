package com.onrender.nit3213api.data.api


import com.onrender.nit3213api.data.model.DashboardResponse
import com.onrender.nit3213api.data.model.LoginRequest
import com.onrender.nit3213api.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NitApi {

    // Login for Footscray class location
    @POST("footscray/auth")
    suspend fun loginFootscray(
        @Body body: LoginRequest
    ): Response<LoginResponse>

    // Dashboard using the keypass returned from login
    @GET("dashboard/{keypass}")
    suspend fun getDashboard(@Path("keypass") keypass: String): Response<DashboardResponse>
}
