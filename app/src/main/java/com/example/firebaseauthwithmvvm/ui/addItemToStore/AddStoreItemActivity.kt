package com.example.firebaseauthwithmvvm.ui.addItemToStore

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hiding the toolbar
        supportActionBar!!.hide()

        setContentView(R.layout.activity_add_storage_item)


        val binding: ActivityAddStorageItemBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_storage_item)
        viewModel = ViewModelProviders.of(this, factory).get(StoreItemViewModel::class.java)
        binding.viewmodel = viewModel


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
