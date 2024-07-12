package com.vunh.android.demoahamove.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.vunh.android.demoahamove.databinding.ActivityMainBinding
import com.vunh.android.demoahamove.presentation.adapter.PopularAdapter
import com.vunh.android.demoahamove.presentation.adapter.PopularPagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeAdapter()
        initializeViewModel()
    }

    private fun initializeAdapter() {
//        val popularAdapter = PopularAdapter()
//        binding.maintenanceListRv.apply {
//            adapter = popularAdapter
//            layoutManager = LinearLayoutManager(this@MainActivity)
//        }
//        viewModel.popularList.observe(this) {
//            it?.apply {
//                popularAdapter.submitList(it)
//            }
//        }

        val popularPagingAdapter = PopularPagingAdapter { item ->
            Toast.makeText(this@MainActivity, item?.name, Toast.LENGTH_SHORT).show()
        }
        binding.maintenanceListRv.apply {
            adapter = popularPagingAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            /* LoadStateHeaderAndFooter */
        }
        viewModel.populars.observe(this) {
            popularPagingAdapter.submitData(this.lifecycle, it)
        }
    }

    private fun initializeViewModel() {
        viewModel.accountGoogle.observe(this) {
            it?.let {
                binding.lineAccount.visibility = View.VISIBLE
                binding.tvPopularLabel.visibility = View.VISIBLE
                Glide.with(binding.root.context)
                    .load(it.avatarUrl)
                    .into(binding.imgAvatar)
                binding.tvName.text = it.name
                binding.tvDescription.text = it.description
                binding.tvFollowers.text = it.followers.toString()
                binding.tvLocation.text = it.location
                binding.tvBlog.text = it.blog
            }
        }
        viewModel.showLoading.observe(this) {
            if (it)
                binding.loadingSpinner.visibility = View.VISIBLE
            else
                binding.loadingSpinner.visibility = View.GONE
        }
        viewModel.showError.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        viewModel.showPopularListStorage.observe(this) {
            if (it) {
                val popularAdapter = PopularAdapter()
                binding.maintenanceListRv.apply {
                    adapter = popularAdapter
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }
                viewModel.popularList.observe(this) { list ->
                    list?.apply {
                        popularAdapter.submitList(list)
                    }
                }
            }
        }
    }
}