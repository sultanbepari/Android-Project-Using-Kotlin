package com.example.project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var sp : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        val logout = findViewById<Button>(R.id.logOut)
        val arrayList = ArrayList<ActList>()

        arrayList.add(ActList("Google Maps", R.drawable.map))
        arrayList.add(ActList("Calculator", R.drawable.calculator))
        arrayList.add(ActList("Calender Event", R.drawable.calender))
        arrayList.add(ActList("Dial Pad", R.drawable.phone))
        arrayList.add(ActList("Contacts", R.drawable.contacts))
        arrayList.add(ActList("Browser", R.drawable.browser))
        arrayList.add(ActList("Camera", R.drawable.camera))
        arrayList.add(ActList("Alarm", R.drawable.alarm))
        arrayList.add(ActList("Email", R.drawable.email))
        arrayList.add(ActList("SMS", R.drawable.message))

        val myAdapter = MyAdapter(arrayList,this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter

        logout.setOnClickListener {
            sp = getSharedPreferences("Shared_pref", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putBoolean("remember", false)
            editor.apply()
            Toast.makeText(this, "Logged out Successfully!", Toast.LENGTH_LONG).show()
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}