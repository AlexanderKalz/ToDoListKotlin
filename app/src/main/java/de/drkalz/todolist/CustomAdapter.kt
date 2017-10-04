package de.drkalz.todolist

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by Alex on 04.10.17.
 */
class CustomAdapter : BaseAdapter {

    lateinit var c: Context
    lateinit var cList: MutableList<ToDoListItem>
    lateinit var inflter: LayoutInflater

    constructor(c: Context, cList: MutableList<ToDoListItem>) : super() {
        this.c = c
        this.cList = cList
        inflter = LayoutInflater.from(c)
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var z = p1
        z = inflter.inflate(R.layout.row, null)
        // var tv : TextView = rowItem
        var tv: TextView = z.findViewById(R.id.rowItem)
        tv.text = cList[p0].item
        if (cList[p0].important) {
            tv.setTypeface(tv.typeface, Typeface.BOLD)
            tv.setTextColor(Color.RED)
        } else {
            tv.setTypeface(tv.typeface, Typeface.NORMAL)
            tv.setTextColor(Color.BLACK)
        }
        return z
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return cList.size
    }

}