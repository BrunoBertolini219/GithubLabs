package com.brunoccbertolini.base.util.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<V: ViewBinding>(
    private val inflate: Inflate<V>
) : Fragment() {
    private var _binding: V? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun getContext(): Context {
        return requireActivity()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}