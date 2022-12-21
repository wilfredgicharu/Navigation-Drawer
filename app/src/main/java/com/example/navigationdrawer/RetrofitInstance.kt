package com.example.navigationdrawer

//api call
object RetrofitInstance {
    val api: TodoApi by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(TodoApi::class.java)
    }
}