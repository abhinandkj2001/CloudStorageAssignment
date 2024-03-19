package com.example.cloudstorageassignment

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudstorageassignment.model.ProductAdapter
import com.example.cloudstorageassignment.model.product_items
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.toObject

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var recycleV:RecyclerView
    private lateinit var pd_arrayList : ArrayList<product_items>
    private lateinit var pdAdapter: ProductAdapter
    private lateinit var db : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view)
        recycleV=findViewById(R.id.View1)
        recycleV.layoutManager = LinearLayoutManager(this)
        recycleV.setHasFixedSize(true)
        pd_arrayList= arrayListOf()
        pdAdapter = ProductAdapter(pd_arrayList)
        recycleV.adapter = pdAdapter

        EventChangeListner()



    }
    private fun EventChangeListner(){
        db = FirebaseFirestore.getInstance()
        db.collection("products").addSnapshotListener(object :EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.e("Firestore Error",error.message.toString())
                    return
                }
                for(dc:DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        pd_arrayList.add(dc.document.toObject(product_items::class.java))

                    }
                }
                pdAdapter.notifyDataSetChanged()
            }
        })

    }
}
