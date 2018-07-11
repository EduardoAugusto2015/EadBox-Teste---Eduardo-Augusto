package com.eadbox.eduardo.teste.view.activity.main

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eadbox.eduardo.teste.R
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.eadbox.eduardo.teste.viewmodel.MainViewModel
import android.databinding.DataBindingUtil
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import com.eadbox.eduardo.teste.databinding.ActivityMainBinding
import com.eadbox.eduardo.teste.model.entity.Courses
import com.eadbox.eduardo.teste.util.Constants
import com.eadbox.eduardo.teste.view.activity.main.adapter.MainAdapter
import com.ethanhua.skeleton.Skeleton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding  : ActivityMainBinding
    private lateinit var adapter  : MainAdapter
    private lateinit var rvCourse : RecyclerView
    private var page : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        bindView()
    }

    private fun bindView() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        setupRecyclerView()
        setObservers()

    }

    private fun setupRecyclerView() {

        rvCourse = binding.rvCourse
        rvCourse.setItemViewCacheSize(30)
        rvCourse.setHasFixedSize(true)
        rvCourse.itemAnimator = DefaultItemAnimator()
        rvCourse.layoutManager = LinearLayoutManager(this)

    }

    private fun setObservers() {

        viewModel.loadData(page)
        viewModel.courses.observe(this, Observer {
            courses ->
            LoadRecyclerView(courses)

        })

        viewModel.errorMessage.observe(this, Observer {
            errorMessage ->
            if (!TextUtils.isEmpty(errorMessage)) {
                Snackbar.make(binding.root, errorMessage!!, Snackbar.LENGTH_LONG).show()
            }
        })

    }

    private fun LoadRecyclerView(courses: List<Courses>?) {

        adapter = MainAdapter(courses!!)
        rvCourse.adapter = adapter

        val skeletonScreen = Skeleton
                .bind(binding.rvCourse)
                .adapter(adapter)
                .shimmer(true)
                .angle(10)
                .frozen(false)
                .duration(800)
                .count(10)
                .load(R.layout.list_courses_load)
                .show()

        binding.rvCourse.postDelayed({ skeletonScreen.hide()}, Constants.Skeleton.TIME_MILLIS)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

}
