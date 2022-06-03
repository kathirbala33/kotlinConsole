package com.myconsolekotlin.app.FragmentAdapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myconsolekotlin.app.Constants.Companion.ACTION_ADAPTER
import com.myconsolekotlin.app.databinding.SampleFragmentBinding
import com.myconsolekotlin.app.interfacePack.Listener

class SampleFragment : Fragment(), Listener {
    var binding: SampleFragmentBinding? = null
    var sampleAdapter: SampleAdapter? = null
    var listner: Listener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SampleFragmentBinding.inflate(layoutInflater)
        setAdapter()
        return binding?.root
    }

    override fun onAttach(context: Context) {
        listner = this
        super.onAttach(context)
    }

    override fun adapterData(values: Any, action: Int) {
        super.adapterData(values, action)
        if (action == ACTION_ADAPTER) {
            var value: String = values as String
            Toast.makeText(context, value, Toast.LENGTH_LONG).show()
        }
    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding?.sampleRecyclerView?.setHasFixedSize(true);
        binding?.sampleRecyclerView?.layoutManager = layoutManager
        var list: ArrayList<String>? = sampleList()
        sampleAdapter = SampleAdapter(context, list, listner)
        binding?.sampleRecyclerView?.adapter = sampleAdapter
    }

    private fun sampleList(): ArrayList<String>? {
        var list: ArrayList<String>? = ArrayList()
        for (i in 1..10) {
            list?.add("check$i")
        }
        return list
    }
}