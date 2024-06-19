package cl.talentodigital.alkewallet.data.network.api

import cl.talentodigital.alkewallet.data.model.LoginRequest
import cl.talentodigital.alkewallet.data.model.LoginResponse
import cl.talentodigital.alkewallet.data.model.RegisterRequest
import cl.talentodigital.alkewallet.data.model.RegisterResponse
import cl.talentodigital.alkewallet.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    //Login
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    //Registro
    @Headers("Content-Type: application/json")
    @POST("users")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    //Datos de Usuario logeado
    @GET("auth/me")
    suspend fun getUserData(@Header("Authorization") token: String): Response<UserResponse>






}