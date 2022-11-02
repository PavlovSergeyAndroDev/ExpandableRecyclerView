package com.example.expandablerecyclerview.myRecyclerView

abstract class RecyclerViewData(open var type: Int)

data class MyParentData (
	val title: String,
	override var type: Int = PARENT_TYPE,
	var subList: MutableList<MyChildData> = ArrayList(),
	var isExpanded: Boolean = false,

	): RecyclerViewData(type)

data class MyChildData(val title: String, override var type: Int = CHILD_TYPE) : RecyclerViewData(type)

const val PARENT_TYPE = 0
const val CHILD_TYPE = 1
