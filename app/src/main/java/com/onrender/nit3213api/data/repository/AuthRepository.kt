package com.onrender.nit3213api.data.repository

class AuthRepository @Inject constructor(private val api: NitApi) {
    suspend fun login(location: String, username: String, password: String): Result<String> {
        return try {
            val resp = api.login(location, LoginRequest(username, password))
            if (resp.isSuccessful) {
                val body = resp.body()
                if (body != null) Result.success(body.keypass)
                else Result.failure(Exception("Empty response"))
            } else {
                Result.failure(Exception("Login failed: ${resp.code()}"))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Network error: please check your internet connection"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getDashboard(keypass: String): Result<DashboardResponse> {
        return try {
            val resp = api.getDashboard(keypass)
            if (resp.isSuccessful) {
                resp.body()?.let { Result.success(it) } ?: Result.failure(Exception("Empty body"))
            } else {
                Result.failure(Exception("Dashboard failed: ${resp.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
