package cl.talentodigital.alkewallet.data.network.api

import cl.talentodigital.alkewallet.data.model.AccountRequest
import cl.talentodigital.alkewallet.data.model.AccountResponse
import cl.talentodigital.alkewallet.data.model.LoginRequest
import cl.talentodigital.alkewallet.data.model.LoginResponse
import cl.talentodigital.alkewallet.data.model.PaymentRequest
import cl.talentodigital.alkewallet.data.model.PaymentResponse
import cl.talentodigital.alkewallet.data.model.RegisterRequest
import cl.talentodigital.alkewallet.data.model.RegisterResponse
import cl.talentodigital.alkewallet.data.model.TransactionsResponse
import cl.talentodigital.alkewallet.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

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

    //Datos de la cuenta
    @GET("accounts/me")
    suspend fun getAccount(@Header("Authorization") authHeader: String): Response<List<AccountResponse>>


    @POST("accounts")
    suspend fun createAccount(
        @Header("Authorization") authHeader: String,
        @Body accountRequest: AccountRequest
    ): Response<AccountResponse>

    @GET("transactions")
    suspend fun getTransactions(@Header("Authorization") authHeader: String): Response<TransactionsResponse>

    @POST("accounts/{accountId}")
    suspend fun sendPayment(
        @Header("Authorization") authHeader: String,
        @Path("accountId") accountId: Int,
        @Body paymentRequest: PaymentRequest
    ): Response<PaymentResponse>






}