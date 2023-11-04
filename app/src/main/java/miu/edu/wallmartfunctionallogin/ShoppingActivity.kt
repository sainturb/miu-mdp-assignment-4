package miu.edu.wallmartfunctionallogin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ShoppingActivity : AppCompatActivity() {
    private val p1 = Product("product 1", R.drawable.wallemart)
    private val p2 = Product("product 2", R.drawable.wallemart)
    private val p3 = Product("product 3", R.drawable.wallemart)
    private val p4 = Product("product 4", R.drawable.wallemart)
    private val p5 = Product("product 5", R.drawable.wallemart)

    private val products = mutableListOf<Product>(p1, p2, p3, p4, p5)
    private var username = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        if (intent.getStringExtra("name") != null) {
            username = intent.getStringExtra("name").toString()
            val profile = findViewById<TextView>(R.id.profileName)
            profile.text = "Welcome! $username"
        }

        val adapter = GridAdapter(products, this)
        val grid = findViewById<GridView>(R.id.grid)
        grid.adapter = adapter

        grid.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                applicationContext, products[position].name + " selected", Toast.LENGTH_SHORT
            ).show()
        }
    }
}