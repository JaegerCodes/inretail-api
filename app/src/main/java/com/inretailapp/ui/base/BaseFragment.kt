package com.inretailapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.inretailapp.BR


abstract class BaseFragment<VM: ViewModel, B: ViewDataBinding> : Fragment() {

    protected lateinit var binding: B
    protected abstract val viewModel: VM
    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doDataBinding()
    }

    private fun doDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner // it is extra if you want to set life cycle owner in binding
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
        init()
        iniciarObservers(viewLifecycleOwner)
        configurarWidgets()
    }

    abstract fun init()

    open fun configurarWidgets() {}

    abstract fun iniciarObservers(lifecycleOwner: LifecycleOwner)

}
