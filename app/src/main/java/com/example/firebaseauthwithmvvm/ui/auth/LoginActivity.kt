package com.example.firebaseauthwithmvvm.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.databinding.ActivityLoginBinding
import com.example.firebaseauthwithmvvm.utils.startHomeActivty
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {


    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()

    private val TAG = LoginActivity::class.java.simpleName

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //bind view
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.authListener = this

    }

    override fun onStarted() {
        super.onStart()
        progressbar_login.visibility = View.VISIBLE

        Log.e(TAG, "Login test "   )


//        Intent(this, MainActivity::class.java).also {
//            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(it)
//        }
    }

    override fun onSuccess() {
        progressbar_login.visibility = View.GONE
        Log.e(TAG, "Login test2 "   )

        startHomeActivty()
    }

    override fun onFailure(message: String) {
        progressbar_login.visibility = View.GONE
        Toast.makeText(this, message, LENGTH_SHORT).show()
    }

    //when app stop then return to startHomeActivty with current user id
    override fun onStart() {
        super.onStart()
        viewModel.user?.let {
            startHomeActivty()
        }
    }

}
