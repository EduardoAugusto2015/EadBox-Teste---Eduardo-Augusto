package com.eadbox.eduardo.teste.model.network

import com.eadbox.eduardo.teste.model.entity.Courses
import com.eadbox.eduardo.teste.model.network.parameter.CoursesParam
import com.eadbox.eduardo.teste.util.Constants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.Endpoint.LOADCOURSES)
    fun loadCourses(@Query("page") params: CoursesParam): Observable<List<Courses>>

    companion object Factory {

        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.Endpoint.BASE_URL)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }

}
