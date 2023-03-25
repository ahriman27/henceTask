package com.example.hencetest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hencetest.databinding.FragmentListBinding

class ListFragment(var list : ArrayList<MarkerModel>) : Fragment(),Notify {

    lateinit var binding : FragmentListBinding
    lateinit var ctx : Context
    lateinit var adapter : ListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).listInter = this
        ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListAdapter(ctx,list)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(ctx)

    }

    override fun onEvent(pos : Int) {
        adapter.notifyDataSetChanged()
    }

}