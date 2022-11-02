package com.example.expandablerecyclerview.myRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.R

class MyAdapter(private val clicked: ClickRecyclerView) :
	RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	private val listData: MutableList<RecyclerViewData> = mutableListOf()

	fun setData() {
		initList()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return when (viewType) {
			PARENT_TYPE -> {
				ParentViewHolder(
					LayoutInflater.from(parent.context).inflate(R.layout.parent_row, parent, false)
				)
			}
			else -> {
				ChildViewHolder(
					LayoutInflater.from(parent.context).inflate(R.layout.child_row, parent, false)
				)
			}
		}
	}

	override fun getItemViewType(position: Int): Int {
		return listData[position].type
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		when (getItemViewType(position)) {
			PARENT_TYPE -> {
				val dataDisplay = listData[position] as MyParentData
				(holder as ParentViewHolder).bind(dataDisplay)
			}
			CHILD_TYPE -> {
				val dataDisplay = listData[position] as MyChildData
				(holder as ChildViewHolder).bind(dataDisplay)
			}
		}
	}

	override fun getItemCount(): Int = listData.size

	inner class ParentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		fun bind(data: MyParentData) {
			val tvTitleParent = itemView.findViewById<TextView>(R.id.parent_Title)
			val ivImageParent = itemView.findViewById<ImageView>(R.id.down_iv)
			tvTitleParent.text = data.title
			ivImageParent.setOnClickListener {
				if (!data.isExpanded && !data.subList.isNullOrEmpty()) {
					data.isExpanded = true
					showSublist(data.subList, adapterPosition)
				} else if (data.isExpanded && !data.subList.isNullOrEmpty()) {
					data.isExpanded = false
					hideSublist(data.subList, adapterPosition)
				} else {
					clicked.click(data.title)
				}
			}
		}
	}

	inner class ChildViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		fun bind(data: MyChildData) {
			val tvChildTitle = itemView.findViewById<TextView>(R.id.child_Title)
			tvChildTitle.text = data.title
			itemView.setOnClickListener {
				clicked.click(data.title)
			}
		}
	}

	private fun showSublist(list: List<MyChildData>, startPosition: Int) {
		val nextPosition = startPosition + 1
		listData.addAll(
			nextPosition,
			list
		)
		notifyItemRangeInserted(nextPosition, list.size)
	}

	private fun hideSublist(list: List<MyChildData>, startPosition: Int) {
		val nextPosition = startPosition + 1
		repeat(list.size) {
			listData.removeAt(nextPosition)
			notifyItemRemoved(nextPosition)
		}
	}

	private fun initList() {
		val parentData: Array<String> =
			arrayOf("Andhra Pradesh", "Telangana", "Karnataka", "TamilNadu")
		val childDataData1: MutableList<MyChildData> = mutableListOf(
			MyChildData("Anathapur"),
			MyChildData("Chittoor"),
			MyChildData("Nellore"),
			MyChildData("Guntur")
		)
		val childDataData2: MutableList<MyChildData> = mutableListOf(
			MyChildData("Rajanna Sircilla"),
			MyChildData("Karimnagar"),
			MyChildData("Siddipet")
		)
		val childDataData3: MutableList<MyChildData> = mutableListOf(
			MyChildData("Chennai"),
			MyChildData("Erode")
		)
		val parentObj1 = MyParentData(title = parentData[0], subList = childDataData1)
		val parentObj2 = MyParentData(title = parentData[1], subList = childDataData2)
		val parentObj3 = MyParentData(title = parentData[2])
		val parentObj4 = MyParentData(title = parentData[1], subList = childDataData3)
		listData.add(parentObj1)
		listData.add(parentObj2)
		listData.add(parentObj3)
		listData.add(parentObj4)
	}
}