package miu.edu.wallmartfunctionallogin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val register = findViewById<Button>(R.id.createUser)
        register.setOnClickListener {
            val firstname = findViewById<EditText>(R.id.firstname)
            val lastname = findViewById<EditText>(R.id.lastname)
            val username = findViewById<EditText>(R.id.userEmail)
            val password = findViewById<EditText>(R.id.userPassword)
            attemptCreate(firstname.text.toString(), lastname.text.toString(), username.text.toString(), password.text.toString())
        }
    }

    private fun attemptCreate(firstname: String, lastname: String, username: String, password: String) {
        if (firstname.isNotEmpty() && lastname.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
            val data = intent
            data.putExtra("firstname", firstname)
            data.putExtra("lastname", lastname)
            data.putExtra("username", username)
            data.putExtra("password", password)
            setResult(RESULT_OK, data)
            finish()
        } else {
            Toast.makeText(this, "Fill out the missing fields", Toast.LENGTH_SHORT).show()
        }
    }
}