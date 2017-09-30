package de.drkalz.todolist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val tvItem = findViewById<TextView>(R.id.tvItem)
        tvItem.text = intent.getStringExtra("item")

        val cbImp = findViewById<CheckBox>(R.id.cbImportant)
        cbImp.isChecked = intent.getBooleanExtra("important", false)

        val toDoItemID = intent.getStringExtra("itemId")

        val buDelete = findViewById<Button>(R.id.buDelete)
        buDelete.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().reference
            ref.child("toDoItems").child(toDoItemID).removeValue()
            finish()
        }

    }
}
