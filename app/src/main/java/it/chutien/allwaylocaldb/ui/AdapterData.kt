package it.chutien.allwaylocaldb.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.chutien.allwaylocaldb.R
import it.chutien.allwaylocaldb.room.model.Dog
import java.util.*
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.format.DateTimeFormatter


class AdapterData constructor
    (
    private val items: ArrayList<Dog>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }


    private var onItemClickListener: OnItemClickListener? = null


    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var name: TextView = v.findViewById(R.id.name) as TextView
        var id: TextView = v.findViewById(R.id.id) as TextView
        var date: TextView = v.findViewById(R.id.date) as TextView
        var lyt_parent: LinearLayout = v.findViewById(R.id.lyt_parent) as LinearLayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh: RecyclerView.ViewHolder
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        vh = ViewHolder(v)
        return vh
    }

    // Replace the contents of a view (invoked by the layout manager)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val o = items[position]
            holder.id.text = o.id
            holder.name.text = o.name
            val dateFormat = "HH:mm:ss dd/MM/yy"
            holder.date.text = o.date.format(DateTimeFormatter.ofPattern(dateFormat))

            holder.lyt_parent.setOnClickListener { view ->
                if (onItemClickListener != null) {
                    onItemClickListener!!.onItemClick(view, o, position)
                }
            }
        }
    }

    fun getItem(position: Int): Dog {
        return items[position]
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: Dog, pos: Int)
    }
}