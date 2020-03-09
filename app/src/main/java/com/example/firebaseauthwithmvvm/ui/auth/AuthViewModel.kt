package com.example.firebaseauthwithmvvm.ui.auth

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    //email nad password for the input
    var email: String? = null
    var pass: String? = null

    var TAG = AuthViewModel::class.java.canonicalName

    //auth listener
    var authListener: AuthListener? = null


    private val disposables = CompositeDisposable()


    val user by lazy {
        repository.currentUser()
    }

    //function to perform login
    fun login() {

        Log.e(TAG, "ViewModel Login1 $email and $pass")

        if (email.isNullOrEmpty() || pass.isNullOrEmpty()) {
            authListener?.onFailure("Invaild Phone or Password")
            return
        }

        Log.e(TAG, "ViewModel Login2 $email and $pass")

        authListener?.onStarted()


        val disposable = repository.login(email!!, pass!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
                Log.e(TAG, "Login2 $email and $pass")

            }, {
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }


    fun signUp() {


        Log.e(TAG, "ViewModel Register1 $email and $pass")

        if (email.isNullOrEmpty() || pass.isNullOrEmpty()) {

            authListener?.onFailure("Please Input all values")

            return
        }


        Log.e(TAG, "Register $email and $pass")

        authListener?.onStarted()

        val disposable = repository.register(email!!, pass!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                Log.e(TAG, "Register2 $email and $pass")

                authListener?.onSuccess()


            }, {
                authListener?.onFailure(it.message!!)
                Log.e(TAG, "Register0 " + email + " and " + pass + " error " + it.message!!)
            })

        disposables.add(disposable)
        Log.e(TAG, "Register00 " + disposable.isDisposed)
    }


    fun goToSignUp(view: View) {
        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}