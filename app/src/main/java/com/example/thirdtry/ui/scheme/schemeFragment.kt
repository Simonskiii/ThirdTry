package com.example.thirdtry.ui.scheme

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.thirdtry.R

class schemeFragment : Fragment() {

    companion object {
        fun newInstance() = schemeFragment()
    }

    private lateinit var viewModel: SchemeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.scheme_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SchemeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
