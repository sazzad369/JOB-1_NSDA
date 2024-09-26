package com.example.job1_nsda

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.job1_nsda.Adapter.ProductAdapter
import com.example.job1_nsda.ViewModel.ProductViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductActivity : AppCompatActivity() {
    private lateinit var productViewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productAdapter = ProductAdapter(emptyList())

        val refreshbtn:FloatingActionButton = findViewById(R.id.refreshBtn)
        refreshbtn.setOnClickListener{
            startActivity(Intent(this@ProductActivity,ProductActivity::class.java))
            finish()
        }
        val recyclerView : RecyclerView = findViewById(R.id.productRV)
        recyclerView.layoutManager = LinearLayoutManager(this)

        productViewModel.product.observe(this, Observer { product ->
            productAdapter = ProductAdapter(product)
            recyclerView.adapter = productAdapter
        })
    }
}