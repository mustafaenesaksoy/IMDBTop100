package com.enesaksoy.imdbtop100.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.enesaksoy.imdbtop100.adapter.ListAdapter
import javax.inject.Inject

class MovieFragmentFactory @Inject constructor(
    private val adapter : ListAdapter,
    private val glide : RequestManager
): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ListFragment::class.java.name -> ListFragment(adapter)
            DetailsFragment::class.java.name -> DetailsFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}