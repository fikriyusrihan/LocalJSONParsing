package com.artworkspace.jsonparsing

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: EmployeeAdapter
    private lateinit var employees: ArrayList<Employee>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lv_employee)
        adapter = EmployeeAdapter(this)
        employees = parseJSON()

        listView.adapter = adapter
        adapter.employees = employees

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            val toast = Toast.makeText(this, employees[i].name, Toast.LENGTH_SHORT)
            toast.show()
        }
    }


    private fun parseJSON(): ArrayList<Employee> {
        val employees = arrayListOf<Employee>()
        var json: String? = null

        try {
            val input: InputStream = assets.open("company.json")
            json = input.bufferedReader().use { it.readText() }

            // Mengambil object terluar pada file JSON
            val jsonObject = JSONObject(json)

            // Mengambil array dengan kunci "employees" dari jsonObject
            val jsonArray = jsonObject.getJSONArray("employees")

            // Melakukan perulangan untuk mengambil object employee dari jsonArray
            for (i in 0 until jsonArray.length()) {
                val jsonObj = jsonArray.getJSONObject(i)

                val name: String = jsonObj.getString("name")
                val age: Int = jsonObj.getInt("age")
                val email: String = jsonObj.getString("email")

                val employee = Employee(name, age, email)

                employees.add(employee)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return employees
    }
}