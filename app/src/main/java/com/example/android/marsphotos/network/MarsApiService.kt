package com.example.android.marsphotos.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

// 3. Open network/MarsApiService.kt. Add the following constant for the base URL for the web service.
private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

// 4. Just below that constant, add a Retrofit builder to build and create a Retrofit object.
private val retrofit = Retrofit.Builder()
    // 5. Retrofit needs the base URI for the web service, and a converter factory to build a
    // web services API. The converter tells Retrofit what to do with the data it gets back from
    // the web service. In this case, you want Retrofit to fetch a JSON response from the web
    // service, and return it as a String. Retrofit has a ScalarsConverter that supports strings
    // and other primitive types, so you call addConverterFactory() on the builder with an
    // instance of ScalarsConverterFactory.
    .addConverterFactory(ScalarsConverterFactory.create())
    // 6. Add the base URI for the web service using baseUrl() method. Finally, call build() to create the Retrofit object.
    .baseUrl(BASE_URL)
    .build()

    // 7. Below the call to the Retrofit builder, define an interface called MarsApiService, that
// defines how Retrofit talks to the web server using HTTP requests.
    interface MarsApiService
    {
        @GET("photos")
        // 8. Inside the MarsApiService interface, add a function called getPhotos() to get the response string from the web service.
        // 14* In MarsApiService, make getPhotos() a suspend function. So that you can call this
        // method from within a coroutine.

        suspend fun getPhotos(): String
        // 9. Use the @GET annotation to tell Retrofit that this is GET request, and specify an endpoint,
    // for that web service method. In this case the endpoint is called photos. As mentioned in the
    // previous task, you will be using /photos endpoint in this codelab. ABOVE fun getPhotos()
        // 10. When the getPhotos() method is invoked, Retrofit appends the endpoint photos to the
    // base URL (which you defined in the Retrofit builder) used to start the request. Add a return
    // type of the function to String.
    }

// *11 Outside the MarsApiService interface declaration, define a public object called MarsApi to
// initialize the Retrofit service. This is the public singleton object that can be accessed from
// the rest of the app.

    object MarsApi {
        //*12. Inside the MarsApi object declaration, add a lazily initialized retrofit object property
// named retrofitService of the type MarsApiService. You make this lazy initialization, to make sure
// it is initialized at its first usage. You will fix the error in the next steps.
        val retrofitService : MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java) }
        //*13 Initialize the retrofitService variable using the retrofit.create() method with the
    // MarsApiService interface.
    }
