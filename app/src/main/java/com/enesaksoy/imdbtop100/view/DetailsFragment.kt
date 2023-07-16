package com.enesaksoy.imdbtop100.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.enesaksoy.imdbtop100.R
import com.enesaksoy.imdbtop100.databinding.DetailsFragmentBinding
import com.enesaksoy.imdbtop100.viewmodel.ListViewModel
import javax.inject.Inject

class DetailsFragment @Inject constructor(private val glide : RequestManager) : Fragment(R.layout.details_fragment) {
    private lateinit var viewmodel : ListViewModel
    private lateinit var binding: DetailsFragmentBinding
    private var url = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailsFragmentBinding.bind(view)
        viewmodel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
        observeOn()
        binding.trailerButton.setOnClickListener {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }
    }

    private fun observeOn(){
        viewmodel.selectedmovie.observe(viewLifecycleOwner, Observer {
            glide.load(it.image).into(binding.movieImage)
            binding.movieDescription.text = it.description
            var directorString = ""
            for(movieString in it.director){
                directorString += "- ${movieString}\n"
            }
            binding.movieDirector.text = "Director\n${directorString}"
            binding.movieYear.text = "year:${it.year}"
            binding.movieName.text = it.title
            binding.movieRating.text = "rating:${it.rating}"
            var writersString = ""
            for(movieString in it.writers){
                writersString += "- ${movieString}\n"
            }
            binding.movieWriters.text = "Writers\n${writersString}"
            var genreString = ""
            for(movieString in it.genre){
                genreString += "- ${movieString}\n"
            }
            binding.movieGenre.text = "Genre\n${genreString}"
            url = it.trailer
        })
    }
}