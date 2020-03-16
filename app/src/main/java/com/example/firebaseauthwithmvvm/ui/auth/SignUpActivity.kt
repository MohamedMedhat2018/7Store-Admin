package com.example.firebaseauthwithmvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.databinding.ActivitySignUpBinding
import com.example.firebaseauthwithmvvm.ui.home.MainActivity
import com.example.firebaseauthwithmvvm.utils.startHomeActivty
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), AuthListener, KodeinAware {


    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)

        val binding: ActivitySignUpBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.authListener = this

    }

    override fun onStarted() {
        progressbar_sign_up.visibility = View.VISIBLE

//        Intent(this, MainActivity::class.java).also {
//            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(it)
//        }
    }

    override fun onSuccess() {
        progressbar_sign_up.visibility = View.GONE
        startHomeActivty()

    }

    override fun onFailure(message: String) {
        progressbar_sign_up.visibility = View.VISIBLE
        Toast.makeText(this, message, LENGTH_SHORT).show()
    }
}