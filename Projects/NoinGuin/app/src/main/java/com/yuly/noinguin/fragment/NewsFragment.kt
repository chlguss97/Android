package com.yuly.noinguin.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuly.noinguin.adapter.NewsPagerAdapter
import com.yuly.noinguin.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {

    private val binding by lazy { FragmentNewsBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("AAA", "NewsFragment onViewCreated....")

        binding.pager.adapter= NewsPagerAdapter(childFragmentManager, lifecycle)
    }

}