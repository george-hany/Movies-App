package com.example.android.movieapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android.movieapp.R
import com.example.android.movieapp.Class.SaveSharedPreference
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val actionBar=supportActionBar
        actionBar!!.title="Login"

        val pref : SaveSharedPreference =
            SaveSharedPreference(this)
        if (pref.isLogedIn())
        {
            val intent=Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

            startActivity(intent)
            finish()
        }

        login_btn.setOnClickListener(View.OnClickListener {
            pref.save(user_name.text.toString(),passowrd.text.toString())
            val intent=Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()

        })
    }
}
