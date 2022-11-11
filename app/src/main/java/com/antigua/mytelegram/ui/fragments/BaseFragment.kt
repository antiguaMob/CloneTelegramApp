package com.antigua.mytelegram.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

//****************************************************************************************
//https://stackoverflow.com/questions/64819181/how-to-make-basefragment-with-view-binding
// TO-DO  refactor base fragment to view binding  compatibility
//
//****************************************************************************************
// abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(
//    layoutID: Int
//) : Fragment(layoutID) {
//    protected abstract val viewModel: ViewModel
//protected abstract val binding: Binding
//
//override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//    setupViews()
//    setupListeners()
//    setupObservers()
//}

//abstract fun setupViews()
//abstract fun setupListeners()
//abstract fun setupObservers()
//}
//****************************************************************************************
// class Fragment : BaseFragment<BaseViewModel, FragmentBinding>( R.layout.fragment) {
//    override val viewModel: BaseViewModel by viewModels()
//    override val binding: FragmentBinding by viewBinding() // this is from library
//    override fun setupViews() { }
//    override fun setupListeners() {  }
//    override fun setupObservers() {  }
//}
//****************************************************************************************
open class BaseFragment( layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
    }
}