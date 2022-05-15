package com.example.news

import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.DomainNewsResponse
import com.example.news.adapter.NewsRecyclerViewAdapter
import com.example.news.base.BaseActivity
import com.example.news.databinding.ActivityMainBinding
import com.example.news.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun init() {
        binding.activity = this
//        binding.textViewTodayDate.text = mainViewModel.apiCallResult.publishedAt
        mainViewModel.getNews("709b04335e404b30a64045caf1d2dfde")
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        mainViewModel.news.observe(this) {
            (binding.recyclerviewNews.adapter as NewsRecyclerViewAdapter?)?.setNews(mainViewModel.apiCallResult.value!!)
            Log.d("성공", "subscribeToLiveData mainViewModel.apiCallResult: ${mainViewModel.apiCallResult.value}")
            initRecycler()
        }
    }

    private fun initRecycler() {
        binding.recyclerviewNews.adapter = NewsRecyclerViewAdapter()
        binding.recyclerviewNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}