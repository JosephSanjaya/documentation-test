/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.test.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableBoolean
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.blankj.utilcode.util.ToastUtils
import com.finansialku.documentation.core.domain.University
import com.finansialku.documentation.core.presentation.UniversityObserver
import com.finansialku.documentation.core.presentation.UniversityViewModel
import com.finansialku.documentation.test.R
import com.finansialku.documentation.test.adapter.UniversityAdapter
import com.finansialku.documentation.test.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity :
    AppCompatActivity(R.layout.activity_main),
    UniversityObserver.Interfaces,
    SwipeRefreshLayout.OnRefreshListener {
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel by viewModel<UniversityViewModel>()
    private val list by lazy {
        mutableListOf<University?>()
    }
    private val adapter by lazy {
        UniversityAdapter(list)
    }
    private val isLoading = ObservableBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setupAdapter()
        lifecycle.addObserver(UniversityObserver(this, viewModel, this))
    }

    override fun onStart() {
        super.onStart()
        onRefresh()
    }

    private fun ActivityMainBinding.setupAdapter() {
        isLoading = this@MainActivity.isLoading
        rvContent.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener(this@MainActivity)
    }

    override fun onUniversityFetching() {
        isLoading.set(true)
        super.onUniversityFetching()
    }

    override fun onUniversityFetchSuccess(data: List<University?>) {
        isLoading.set(false)
        binding.swipeRefreshLayout.isRefreshing = false
        adapter.setNewInstance(data.toMutableList())
        super.onUniversityFetchSuccess(data)
    }

    override fun onUniversityFetchFailed(e: Throwable?) {
        isLoading.set(false)
        binding.swipeRefreshLayout.isRefreshing = false
        super.onUniversityFetchFailed(e)
        ToastUtils.showShort(e?.message.toString())
    }

    override fun onRefresh() {
        viewModel.fetchUniversities("Indonesia")
    }
}
