package com.brunoccbertolini.base.util.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.brunoccbertolini.base.Factory

abstract class BaseActivity<V: ViewBinding>(
    val inflate: Factory<V>,
) : AppCompatActivity() {
    val binding: V by lazy { inflate(layoutInflater) }

    val context: Context
        get() = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}