package com.yuly.noinguin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yuly.noinguin.databinding.FragmentTestBinding

class TestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }
}