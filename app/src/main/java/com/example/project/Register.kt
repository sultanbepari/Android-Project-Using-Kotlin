package com.example.project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class Register : AppCompatActivity() {

    lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val email = findViewById<EditText>(R.id.email)
        val male = findViewById<CheckBox>(R.id.male)
        val female = findViewById<CheckBox>(R.id.female)
        val login = findViewById<Button>(R.id.sign_in)
        val register = findViewById<ImageButton>(R.id.signup)

        sp = getSharedPreferences("Shared_pref",Context.MODE_PRIVATE)

        register.setOnClickListener {
            val user : String = username.text.toString()
            val emailId : String = email.text.toString()
            val passWord : String= password.text.toString()
            val Male : Boolean = male.isChecked
            val Female : Boolean = female.isChecked

            if(user.trim().isEmpty()){
                Toast.makeText(this, "Please fill username",Toast.LENGTH_LONG).show()
            }
            else if (emailId.trim().isEmpty()){
                Toast.makeText(this, "Please fill Email ID",Toast.LENGTH_LONG).show()
            }
            else if (passWord.trim().isEmpty()){
                Toast.makeText(this, "Please fill Password",Toast.LENGTH_LONG).show()
            }
            else if (!male.isChecked && !female.isChecked){
                Toast.makeText(this, "Please select your gender",Toast.LENGTH_LONG).show()
            }
            else{
                val editor = sp.edit()
                editor.putString("username", user)
                editor.putString("password", passWord)
                editor.putString("Email", emailId)
                editor.putBoolean("Male",Male)
                editor.putBoolean("Female", Female)
                editor.apply()

                Toast.makeText(this, "Registered Successfully!",Toast.LENGTH_LONG).show()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }

        login.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}