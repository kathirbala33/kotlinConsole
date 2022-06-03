package com.myconsolekotlin.app.FragmentAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myconsolekotlin.app.Constants.Companion.ACTION_ADAPTER
import com.myconsolekotlin.app.R
import com.myconsolekotlin.app.interfacePack.Listener

class SampleAdapter(context: Context?, list: ArrayList<String>?, listner: Listener?) :
    RecyclerView.Adapter<SampleAdapter.Holder>() {
    var lists: ArrayList<String>? = list
    var listener: Listener? = listner
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.sample_adapter, parent, false)
        return Holder(v)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        var m = lists?.get(position)
        holder.textView?.setText(m)
        holder.textView?.tag = position
        holder.textView?.setOnClickListener {
            listener?.adapterData(
                holder.textView?.text.toString(),
                ACTION_ADAPTER
            )
        }

    }

    override fun getItemCount(): Int {
        return lists!!.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = itemView.findViewById(R.id.textView)

    }
}

