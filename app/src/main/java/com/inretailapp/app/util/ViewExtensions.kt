package com.inretailapp.app.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.inretailapp.R
import com.inretailapp.data.network.Resource
import com.inretailapp.ui.home.HomeActivity
import com.inretailapp.ui.login.LoginFragment
import kotlinx.coroutines.launch
import java.text.NumberFormat

fun<A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun<A : Activity> Fragment.startNewActivity(activityClass: Class<A>) {
    requireActivity().startNewActivity(activityClass)
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}
fun View.snackbar(message: String, position: Int? = null, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, 4000)

    position?.let {
        val snacView = snackbar.view
        val params = snacView.layoutParams as CoordinatorLayout.LayoutParams
        params.gravity = position
        snacView.layoutParams = params
    }

    snackbar.setAction("OK!") {
        action?.invoke()
    }
    snackbar.show()
}

fun Fragment.logout() = lifecycleScope.launch {
    if (activity is HomeActivity) {
        (activity as HomeActivity).performLogout()
    }
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    messageId: Int? = null,
    retry: (() -> Unit)? = null
) {
    when {
        failure.isNetworkError -> {
            Log.e("NET", failure.toString())
            requireView().snackbar(
                getString(R.string.no_lo_vimos_venir),
            ) { retry?.invoke() }
        }
        failure.errorCode == 401 -> {
            if (this is LoginFragment) {
                requireView().snackbar(getString(R.string.credenciales_incorrectas))
            } else {
                logout()
            }
        }
        failure.errorCode == 404 -> {
            val mensaje =  getString(messageId?:R.string.mensaje_de_error_404)
            requireView().snackbar(mensaje)
        }
        else -> {
            requireView().snackbar("¿Error ${failure.errorCode}? ¿Qué es eso?")
        }
    }
}

fun Fragment.mostrarMensaje(mensaje: String, posicionEnPantalla: Int? = null) {
    requireView().snackbar(mensaje, posicionEnPantalla)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun EditText.onDone(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
        }
        false
    }
    setOnKeyListener { _, keyCode, event ->
        if ((event.action == KeyEvent.ACTION_DOWN) &&
            (keyCode == KeyEvent.KEYCODE_ENTER)) {
            callback.invoke()
        }
        false
    }
}

fun Double.round(decimals: Int = 2): String {
    val formatter: NumberFormat = NumberFormat.getNumberInstance()
    formatter.minimumFractionDigits = decimals
    formatter.maximumFractionDigits = decimals
    return formatter.format(this)
}

fun Double.percentOf(maxValueLimit: Double): Double {
    return if (maxValueLimit > 0) (this*100)/maxValueLimit else 0.00
}