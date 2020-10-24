package com.disarpus.penjualan.ui.login

import com.disarpus.penjualan.data.database.PrefsManager
import com.disarpus.penjualan.data.model.DataLogin
import com.disarpus.penjualan.data.model.ResponseLogin
import com.disarpus.penjualan.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginPresenter (val view: LoginContract.View): LoginContract.Presenter{

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }


    override fun doLogin(username: String, password: String) {
        view.onLoading(true)
        ApiService.endpoint.loginUser(username, password)
            .enqueue(object : Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful){
                        val responseLogin: ResponseLogin? = response.body()
                        view.showMessage(responseLogin!!.msg)
                        if (responseLogin!!.status){ // cek status Login
                            view.onResult(responseLogin)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    view.onLoading(false)
                }

            })
    }

    override fun setPrefs(prefsManager: PrefsManager, dataLogin: DataLogin) {
        prefsManager.prefsIsLogin = true
        prefsManager.prefsUsername = dataLogin.username!!
        prefsManager.prefsPassword = dataLogin.password!!
        prefsManager.prefsNamaPegawai = dataLogin.nama_pegawai!!
        prefsManager.prefsJk = dataLogin.jk!!
        prefsManager.prefsAlamat = dataLogin.alamat!!
        prefsManager.prefsIsAktif = dataLogin.is_aktif!!

    }

}