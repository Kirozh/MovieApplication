package com.example.kirozh.movieapplication

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kirozh.movieapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    private var mAdapter = MoviePagingAdapter()
    private lateinit var viewModel: MainViewModel
    private lateinit var progressBar: ProgressBar
    lateinit var binding: ActivityMainBinding
    var myReceiver = MovieBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        progressBar = binding.progressBar

        recyclerView = binding.recyclerView
        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            recyclerView.adapter = mAdapter.withLoadStateHeaderAndFooter(
                header = MovieLoadStateAdapter(),
                footer = MovieLoadStateAdapter()
            )

            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        registerReceiver(myReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        val retrofitService = RetrofitApi.getInstance()

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(retrofitService)
        ).get(MainViewModel::class.java)

        lifecycleScope.launch {
            viewModel.getVmPager().collectLatest {
                mAdapter.submitData(it)
            }
        }

        mAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                progressBar.visibility = View.VISIBLE
            }
            else {
                progressBar.visibility = View.GONE

                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

                if (error != null)
                    Toast.makeText(this, R.string.noInternetConnection, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(myReceiver)
    }
}