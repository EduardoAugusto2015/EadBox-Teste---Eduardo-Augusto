package com.eadbox.eduardo.teste.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.eadbox.eduardo.teste.R
import com.eadbox.eduardo.teste.model.entity.Courses
import com.eadbox.eduardo.teste.model.network.ApiServiceProvider
import com.eadbox.eduardo.teste.model.network.parameter.CoursesParam
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : BaseViewModel(application) {

    val courses = MutableLiveData<List<Courses>>()

    fun loadData(page: Int) {

        ApiServiceProvider.apiService
                .loadCourses(coursesParam(page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    response ->
                    courses.value = response
                }, {
                    error ->

                    Log.i("teste_error", error.message.toString())
                    errorMessage.value = getApplication<Application>().getString(R.string.course_error_load)
                })

    }

    private fun coursesParam(page: Int): CoursesParam {
        return CoursesParam(page)
    }

}
