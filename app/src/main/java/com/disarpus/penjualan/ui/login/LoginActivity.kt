package com.disarpus.penjualan.ui.login
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.disarpus.penjualan.R
import com.disarpus.penjualan.data.database.PrefsManager
import com.disarpus.penjualan.data.model.ResponseLogin
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
    lateinit var presenter: LoginPresenter
    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        prefsManager = PrefsManager(this)
    }

    override fun initActivity() {

    }

    override fun initListener() {
        btnLogin.setOnClickListener {
            presenter.doLogin(edtUsername.text.toString(), edtPassword.text.toString())
        }
    }

    override fun onResult(responseLogin: ResponseLogin) {
        presenter.setPrefs(prefsManager,responseLogin.pegawai!!)
        finish()
    }

    override fun onLoading(loading: Boolean){
        when (loading) {
            true -> {
                progress.visibility = View.VISIBLE
                btnLogin.visibility = View.GONE
            }
            false -> {
                progress.visibility = View.GONE
                btnLogin.visibility = View.VISIBLE
            }
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}