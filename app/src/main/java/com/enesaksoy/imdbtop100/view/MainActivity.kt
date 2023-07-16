package com.enesaksoy.imdbtop100.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enesaksoy.imdbtop100.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var fragmentfactory : MovieFragmentFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentfactory
        setContentView(R.layout.activity_main)
    }
}