package com.example.expandablerecyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.myRecyclerView.MyAdapter

class MainActivity : AppCompatActivity() {

	private val adapter: MyAdapter = MyAdapter {
		Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val exRecycleView = findViewById<RecyclerView>(R.id.exRecycle)
		exRecycleView.layoutManager = LinearLayoutManager(this)
		exRecycleView.adapter = adapter
		adapter.setData()
	}
}