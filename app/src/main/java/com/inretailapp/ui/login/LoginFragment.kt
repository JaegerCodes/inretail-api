package com.inretailapp.ui.login

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import com.inretailapp.R
import com.inretailapp.app.providers.DataStoreProvider
import com.inretailapp.app.util.enable
import com.inretailapp.app.util.handleApiError
import com.inretailapp.app.util.startNewActivity
import com.inretailapp.app.util.visible
import com.inretailapp.data.network.Resource
import com.inretailapp.databinding.FragmentLoginBinding
import com.inretailapp.ui.base.BaseFragment
import com.inretailapp.ui.home.HomeActivity
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: BaseFragment<AuthViewModel, FragmentLoginBinding>() {

    override val viewModel : AuthViewModel by viewModel()
    private lateinit var loginView  : View

    override fun getLayoutResId(): Int = R.layout.fragment_login

    override fun init() {
        binding.apply {
            loginView       = root
            viewModel       = this@LoginFragment.viewModel
            lifecycleOwner  = activity
            fragment        = this@LoginFragment
        }
    }

    override fun iniciarObservers(lifecycleOwner: LifecycleOwner) {
        viewModel.authResponse.observe(lifecycleOwner, {
            binding.progressBar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    viewModel.grabarTokenDeAcceso(
                        accessToken = it.datos.token?: ""
                    )
                    startNewActivity(HomeActivity::class.java)
                }
                is Resource.Failure -> {
                    viewModel.grabarTokenDeAcceso("")
                    handleApiError(it) {
                        enviarCredenciales()
                    }
                }
                else -> {}
            }

        })
    }

    override fun configurarWidgets() {
        binding.apply {

            btnIngresar.setOnClickListener {
                enviarCredenciales()
            }

            etPassword.addTextChangedListener {
                val email = etUsuario.text.toString().trim()
                btnIngresar.enable(email.isNotEmpty() && it.toString().isNotEmpty())
            }

            progressBar.visible(false)
        }
    }

    private fun enviarCredenciales() {
        binding.apply {
            viewModel?.login(etUsuario.text.toString(), etPassword.text.toString())
        }
    }
}