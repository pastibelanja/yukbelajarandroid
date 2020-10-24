package com.disarpus.penjualan.ui.login

import com.disarpus.penjualan.data.database.PrefsManager
import com.disarpus.penjualan.data.model.DataLogin
import com.disarpus.penjualan.data.model.ResponseLogin

interface LoginContract{
    interface Presenter{
        fun doLogin(username: String, password: String)
        fun setPrefs(prefsManager: PrefsManager, dataLogin: DataLogin)
    }
    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseLogin: ResponseLogin)
        fun showMessage(message: String)
    }
}