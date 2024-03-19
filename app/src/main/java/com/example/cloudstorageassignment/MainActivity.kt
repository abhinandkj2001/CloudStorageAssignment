package com.example.cloudstorageassignment


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.hashMapOf as hashMapOf1

class MainActivity : AppCompatActivity() {
    private lateinit var Pid:EditText
    private lateinit var Pname:EditText
    private lateinit var ppice:EditText
    private lateinit var add:Button
    private lateinit var show:Button
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        db=FirebaseFirestore.getInstance()
        Pid=findViewById(R.id.editTextText)
        Pname=findViewById(R.id.editTextText2)
        ppice=findViewById(R.id.editTextText3)
        add=findViewById(R.id.button)
        show=findViewById(R.id.button2)

        show.setOnClickListener(View.OnClickListener {
            var i = Intent(applicationContext,RecyclerViewActivity::class.java)
            startActivity(i)
        })

        add.setOnClickListener {
            addProductToFirestore()
        }







    }

    private fun addProductToFirestore() {
        val productId = Pid.text.toString().trim()
        val productName = Pname.text.toString().trim()
        val productPrice = ppice.text.toString().trim()

        if (productId.isNotEmpty() && productName.isNotEmpty() && productPrice.isNotEmpty()) {
            val product = hashMapOf1(
                "productId" to productId,
                "productName" to productName,
                "productPrice" to productPrice
            )


            db.collection("products")
                .add(product)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(
                        applicationContext,
                        "Product added successfully with ID: ${documentReference.id}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        applicationContext,
                        "Error adding product: $e",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        } else {
            Toast.makeText(applicationContext, "Please fill in all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }
}