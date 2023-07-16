package com.enesaksoy.imdbtop100.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesaksoy.imdbtop100.R
import com.enesaksoy.imdbtop100.adapter.ListAdapter
import com.enesaksoy.imdbtop100.databinding.ListFragmentBinding
import com.enesaksoy.imdbtop100.util.Status
import com.enesaksoy.imdbtop100.viewmodel.ListViewModel
import javax.inject.Inject

class ListFragment @Inject constructor(private val adapter : ListAdapter): Fragment(R.layout.list_fragment) {
    private lateinit var binding: ListFragmentBinding
    private lateinit var viewmodel : ListViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListFragmentBinding.bind(view)
        viewmodel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
        viewmodel.getRetrofitApi()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        observeOn()
        adapter.onItemClickListener {
            viewmodel.setSelectedMovie(it)
            val action = ListFragmentDirections.actionListFragmentToDetailsFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

    private fun observeOn(){
        viewmodel.getlist.observe(viewLifecycleOwner, Observer {
            if(it.status == Status.SUCCESS){
                it.data?.let {
                    adapter.movies = it
                    adapter.notifyDataSetChanged()
                }
            }else if(it.status == Status.ERROR){
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
            }
        })
    }
}