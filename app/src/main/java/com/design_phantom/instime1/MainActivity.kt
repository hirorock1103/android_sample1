package com.design_phantom.instime1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.view.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : MyAdapter
    private lateinit var layoutManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var customerListJson : JSONArray = JSONArray("[{\"ID\":\"001\",\"name\":\"Android\"}, {\"ID\":\"002\",\"name\":\"Android2\"},{\"ID\":\"003\",\"name\":\"Android3\"}]")
        //var jsonObj : JSONObject = jarray.getJSONObject(i)
        var list = ArrayList<Customer>()
        for(i in 0..customerListJson.length()-1){
            var jsonObj : JSONObject = customerListJson.getJSONObject(i)
            var customer = Customer()
            customer.name = jsonObj.getString("name")
            customer.id = jsonObj.getString("ID")
            list.add(customer)
        }

        layoutManager = LinearLayoutManager(this)
        my_recycler_view.layoutManager = layoutManager
        adapter = MyAdapter(list)
        my_recycler_view.adapter = adapter


    }
}

class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var customerName : TextView? = null
    var customerNum : TextView? = null
    init {
        customerName = itemView.my_tx_customer_name
        customerNum = itemView.my_tx_customer_id
    }
}

class MyAdapter(resultList : ArrayList<Customer>) : RecyclerView.Adapter<MyViewHolder>(){

    private val list = resultList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var customer = list[position]
        holder.customerName?.text = customer.name
        holder.customerNum?.text = customer.id
        holder.itemView.setBackgroundColor(if(position % 2 == 0) Color.LTGRAY else Color.WHITE )
    }

}
