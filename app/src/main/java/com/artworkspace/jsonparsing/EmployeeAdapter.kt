package com.artworkspace.jsonparsing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class EmployeeAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var employees = arrayListOf<Employee>()

    override fun getCount(): Int {
        return employees.size
    }

    override fun getItem(p0: Int): Any {
        return employees[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var itemView = view
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false)
        }

        val viewHolder = ViewHolder(itemView as View)

        val employee = getItem(position) as Employee
        viewHolder.bind(employee)

        return itemView
    }

    private inner class ViewHolder(view: View?) {
        val tvName = view?.findViewById<TextView>(R.id.list_tv_name)
        val tvAge = view?.findViewById<TextView>(R.id.list_tv_age)
        val tvEmail = view?.findViewById<TextView>(R.id.list_tv_email)

        fun bind(employee: Employee) {
            tvName?.text = employee.name
            tvAge?.text = employee.age.toString()
            tvEmail?.text = employee.email
        }
    }
}