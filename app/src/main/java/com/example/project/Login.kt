package com.example.project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class Login : AppCompatActivity() {

    private lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.user)
        val password = findViewById<EditText>(R.id.pass)
        val remember = findViewById<CheckBox>(R.id.remember)
        val login = findViewById<ImageButton>(R.id.sign_In)
        val register = findViewById<Button>(R.id.sign_up)

        sp = getSharedPreferences("Shared_pref",Context.MODE_PRIVATE)
        val userName = sp.getString("username", "")
        val passwd = sp.getString("password","")
        val remember_me : Boolean = sp.getBoolean("remember",false)

        if(remember_me){
            username.setText(userName)
            password.setText(passwd)
            remember.isChecked = remember_me
        }

        login.setOnClickListener {

            if (username.text.toString().isEmpty()){
                Toast.makeText(this, "Please fill username",Toast.LENGTH_LONG).show()
            }

            if (password.text.toString().isEmpty()){
                Toast.makeText(this, "Please fill password",Toast.LENGTH_LONG).show()
            }

            if(userName == username.text.toString() && passwd == password.text.toString()){
                if (remember.isChecked){
                    val editor = sp.edit()
                    editor.putBoolean("remember", remember.isChecked)
                    editor.apply()
                }
                Toast.makeText(this, "Logged In Successfully!",Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Incorrect Username and Password",Toast.LENGTH_LONG).show()
            }
        }

        register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

    }
}