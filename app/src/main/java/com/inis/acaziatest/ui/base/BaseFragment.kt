package com.inis.acaziatest.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.inis.acaziatest.BR
import com.inis.acaziatest.R
import com.inis.acaziatest.shareviewmodel.MainShareViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {
    lateinit var viewBinding: ViewBinding
    var alertDialog: AlertDialog? = null

    abstract val viewModel: ViewModel

    val mainShareViewModel: MainShareViewModel
        get() = ViewModelProvider(requireActivity(), viewModelFactory)[MainShareViewModel::class.java]

    @get:LayoutRes
    abstract val layoutId: Int

    var mAlertDialog: AlertDialog? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onAttach(context: Context) {
        performDependencyInjection()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            setVariable(BR.viewModel, viewModel)
            root.isClickable = true
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            errorMessage.observe(viewLifecycleOwner, Observer {
                if (it != null && it.isNotBlank()) { }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.isLoading.removeObservers(this)
        viewModel.errorMessage.removeObservers(this)
        viewModel.onActivityDestroyed()
        alertDialog?.dismiss()
    }

    /**
     * fragment transaction
     */
    fun findFragment(TAG: String): Fragment? {
        return activity?.supportFragmentManager?.findFragmentByTag(TAG)
    }

    fun findChildFragment(parentFragment: Fragment = this, TAG: String): Fragment? {
        return parentFragment.childFragmentManager.findFragmentByTag(TAG)
    }

    fun findLastChildFragment(parentFragment: Fragment): Fragment {
        return parentFragment.childFragmentManager.fragments.last()
    }

    fun addFragment(
        container: Int = R.id.main_activity_container,
        fragment: Fragment,
        TAG: String?,
        addToBackStack: Boolean = false,
        transit: TransitionFrom = TransitionFrom.RIGHT
    ) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            setTransActionAnimation(this, transit)
            add(container, fragment, TAG)
            commitTransaction(this, addToBackStack)
        }
    }

    fun replaceFragment(
        container: Int = R.id.main_activity_container,
        fragment: Fragment,
        TAG: String?,
        addToBackStack: Boolean = false,
        transit: TransitionFrom = TransitionFrom.RIGHT
    ) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            setTransActionAnimation(this, transit)
            replace(container, fragment, TAG)
            commitTransaction(this, addToBackStack)
        }
    }

    fun replaceChildFragment(
        parentFragment: Fragment = this,
        containerViewId: Int,
        fragment: Fragment,
        TAG: String?,
        addToBackStack: Boolean = false,
        transit: TransitionFrom = TransitionFrom.RIGHT
    ) {
        val transaction = parentFragment.childFragmentManager.beginTransaction().apply {
            setTransActionAnimation(this, transit)
            replace(containerViewId, fragment, TAG)
        }
        commitTransaction(transaction, addToBackStack)
    }

    fun addChildFragment(
        parentFragment: Fragment = this,
        containerViewId: Int,
        targetFragment: Fragment,
        TAG: String?,
        addToBackStack: Boolean = false,
        transit: TransitionFrom = TransitionFrom.RIGHT
    ) {
        val transaction = parentFragment.childFragmentManager.beginTransaction().apply {
            setTransActionAnimation(this, transit)
            add(containerViewId, targetFragment, TAG)
        }
        commitTransaction(transaction, addToBackStack)
    }

    fun setTransActionAnimation(
        fragmentTransaction: FragmentTransaction,
        transitionFrom: TransitionFrom
    ) {
        fragmentTransaction.apply {
            when (transitionFrom) {
                TransitionFrom.BOTTOM -> setCustomAnimations(
                    R.anim.enter_from_bottom,
                    R.anim.exit_to_bottom,
                    R.anim.enter_from_bottom,
                    R.anim.exit_to_bottom
                )
                TransitionFrom.RIGHT -> setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_right,
                    R.anim.enter_from_right,
                    R.anim.exit_to_right
                )
                TransitionFrom.NONE -> { // do nothing
                }
                else -> { // do nothing
                }
            }
        }
    }

    fun showDialogFragment(
        dialogFragment: DialogFragment,
        TAG: String?,
        addToBackStack: Boolean = false
    ) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if (addToBackStack) transaction?.addToBackStack(TAG)
        if (transaction != null) {
            dialogFragment.show(transaction, TAG)
        }
    }

    private fun commitTransaction(
        transaction: FragmentTransaction,
        addToBackStack: Boolean = false
    ) {
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    fun popChildFragment(parentFragment: Fragment = this) {
        parentFragment.childFragmentManager.popBackStack()
    }

    open fun onBack(): Boolean {
        activity?.onBackPressed()
        return false
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    fun popBackFragmentToPosition(toFragmentPosition: Int) {
        activity?.supportFragmentManager?.backStackEntryCount?.let {
            for (i in toFragmentPosition until it) {
                parentFragmentManager.popBackStackImmediate()
            }
        }
    }

    enum class TransitionFrom {
        RIGHT, BOTTOM, LEFT, TOP, NONE
    }
}