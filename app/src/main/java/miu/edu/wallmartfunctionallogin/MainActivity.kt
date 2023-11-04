package miu.edu.wallmartfunctionallogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val u1 = User("John","Doe","user1", "pass1")
    private val u2 = User("Hurley","Doe","user2", "pass2")
    private val u3 = User("Jane","Doe","user3", "pass3")
    private val u4 = User("Micheal","Doe","user4", "pass4")
    private val u5 = User("Howard","Doe","user5", "pass5")

    private val users = mutableListOf<User>(u1, u2, u3, u4, u5)
    private var loggedUser: User? = null

    private val activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val firstname = it.data?.getStringExtra("firstname")
            val lastname = it.data?.getStringExtra("lastname")
            val username = it.data?.getStringExtra("username")
            val password = it.data?.getStringExtra("password")
            if (!firstname.isNullOrBlank() && !lastname.isNullOrBlank() && !username.isNullOrBlank() && !password.isNullOrBlank()) {
               users.add(User(firstname, lastname, username, password))
               Toast.makeText(this, "User $firstname is created successfully.", Toast.LENGTH_SHORT).show()
           }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login = findViewById<Button>(R.id.signIn)
        val forgot = findViewById<TextView>(R.id.forgotPassword)
        val register = findViewById<TextView>(R.id.register)

        login.setOnClickListener {
            val username = findViewById<EditText>(R.id.username)
            val password = findViewById<EditText>(R.id.password)
            if (username.text.isNotEmpty() && password.text.isNotEmpty()) {
                attemptLogin(username.text.toString(), password.text.toString())
            }
        }

        forgot.setOnClickListener {
            val username = findViewById<EditText>(R.id.username)
            if (username.text.isNotEmpty()) {
                val found = users.find { it.name == username.text.toString() }
                if (found != null) {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Your password is " + found.pass)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                } else {
                    Toast.makeText(this, "User is not existed in system.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fill the email field.", Toast.LENGTH_SHORT).show()
            }
        }

        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            activityLauncher.launch(intent)
        }
    }

    private fun attemptLogin(username: String, password: String): Boolean {
        val found = users.find { it.name == username && it.pass == password}
        if (found != null) {
            loggedUser = found
            Toast.makeText(this, "Welcome! " + found.name, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ShoppingActivity::class.java)
            intent.putExtra("name", found.name)
            startActivity(intent)
        } else {
            Toast.makeText(this, "User not found.", Toast.LENGTH_SHORT).show()
        }
        return found != null
    }
}