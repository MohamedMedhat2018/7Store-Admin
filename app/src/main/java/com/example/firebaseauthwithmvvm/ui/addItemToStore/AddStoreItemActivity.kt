package com.example.firebaseauthwithmvvm.ui.addItemToStore

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.a7storenavigationdrawer.ui.addItemToStore.StoreItemViewModel
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.databinding.ActivityAddStorageItemBinding
import com.example.firebaseauthwithmvvm.databinding.ActivityLoginBinding
import com.example.firebaseauthwithmvvm.databinding.ActivityMain2Binding
import com.example.firebaseauthwithmvvm.ui.auth.AuthViewModel
import com.example.firebaseauthwithmvvm.ui.home2.HomeViewModel
import com.example.firebaseauthwithmvvm.ui.home2.HomeViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AddStoreItemActivity : AppCompatActivity(), KodeinAware, UploadImage {

    private val PICKER_IMAGE_REQUEST = 1
    lateinit var imageUri: Uri

    private lateinit var viewModel: StoreItemViewModel
    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()

    val TAG = AddStoreItemActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hiding the toolbar
        supportActionBar!!.hide()

        setContentView(R.layout.activity_add_storage_item)

        Log.e(TAG, "happpppy4")


        val binding: ActivityAddStorageItemBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_storage_item)
//        val handler: Handler =
        viewModel = ViewModelProviders.of(this).get(StoreItemViewModel::class.java)

        //create observer on viewModel.observeViewModelEvents() to listen to any view call this method
        viewModel.observeViewModelEvents().observe(this, Observer {
            val event = it.takeUnless { it == null || it.handler } ?: return@Observer
            handlerViewModlAction(event)
            Log.e(TAG, "happpppy1")

        })
        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this);


    }

    //pass the activity to ViewModelEvent so u can call use any activity method there without use here
    //so now ViewModelEvent.handler work like Activity now ad do all activity work there  :) :)
    protected fun handlerViewModlAction(event: ViewModelEvent) {
        event.handle(this)
        Log.e(TAG, "happpppy2")

    }

    override fun UploadImageDetails(imageId: String) {
        val intent: Intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICKER_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICKER_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data!!

//            Picasso.get().load(imageUri).into()

        }

    }


}
