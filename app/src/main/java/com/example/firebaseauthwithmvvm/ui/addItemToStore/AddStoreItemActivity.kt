package com.example.firebaseauthwithmvvm.ui.addItemToStore

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.a7storenavigationdrawer.ui.addItemToStore.StoreItemViewModel
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.databinding.ActivityAddStorageItemBinding
import com.example.firebaseauthwithmvvm.ui.home2.HomeViewModelFactory
import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_storage_item.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest
import java.io.File


class AddStoreItemActivity : AppCompatActivity(), KodeinAware {

    val TAG = AddStoreItemActivity::class.java.simpleName

    private val PICKER_IMAGE_REQUEST = 1
    lateinit var imageUri: Uri

    companion object {
        //to be constant
        private const val RC_CAMERA_AND_STORAGE = 121

    }

    private lateinit var viewModel: StoreItemViewModel
    private val factory: HomeViewModelFactory by instance()
    override val kodein by kodein()


    //upload image dialog
    private lateinit var dialog: BottomSheetDialog
    //upload image permission
    val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hiding the toolbar
        supportActionBar!!.hide()



        setContentView(R.layout.activity_add_storage_item)
        Log.e(TAG, "AddStoreItemActivity: OnCreate")

        val binding: ActivityAddStorageItemBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_storage_item)

        viewModel = ViewModelProviders.of(this).get(StoreItemViewModel::class.java)

        viewModel.getProductImage().observe(this, Observer {
            Log.e(TAG, "viewModel.SetImage().observe $it")
//            uploadImageDetails()
            requestCamAndStoragePerms()
        })

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        updateProfilePhoto()
    }

    //Start Easy Permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.e(TAG, "Permision $requestCode and $permissions $grantResults")

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    //create a dialog for upload Image
    private fun updateProfilePhoto() {
        dialog = BottomSheetBuilder(this, null)
            .setMode(BottomSheetBuilder.MODE_LIST) //                .setMode(BottomSheetBuilder.MODE_GRID)
            .addDividerItem()
            .expandOnStart(true)
            .setDividerBackground(R.color.black)
//            .setBackground(R.drawable.ripple_grey)
            .setMenu(R.menu.menu_image_picker)
            .setItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.chooseFromCamera ->  //EasyImage.openChooserWithGallery(getApplicationContext(), "Ch", int type);
                        EasyImage.openCamera(this, 0)
                    R.id.chooseFromGellery -> EasyImage.openGallery(this, 0)
                }
            }
            .createDialog()
//        dialog.show();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        EasyImage.handleActivityResult(
            requestCode,
            resultCode,
            data,
            this,
            object : DefaultCallback() {
                override fun onImagePicked(
                    imageFile: File?,
                    source: EasyImage.ImageSource?,
                    type: Int
                ) {
                    var uri: Uri = Uri.fromFile(imageFile)

                    viewModel.ProductImageUri = uri
                    Log.e(TAG, "URI for Image is " + viewModel.ProductImageUri)
                    Picasso.get().load(uri).into(iv_store_product_image)
//
                }

            })
    }

    //check permission granted or not
    @AfterPermissionGranted(RC_CAMERA_AND_STORAGE)
    private fun requestCamAndStoragePerms() {
        if (EasyPermissions.hasPermissions(this, *PERMISSIONS)
        ) {
            if (!dialog.isShowing) {
                updateProfilePhoto()
                dialog.show()
            }
        } else { // Do not have permissions, request them now
//            EasyPermissions.requestPermissions(this, getString(R.string.contacts_and_storage_rationale),
//                    RC_CONTACT_AND_STORAGE, perms);
            EasyPermissions.requestPermissions(
                PermissionRequest.Builder(this, RC_CAMERA_AND_STORAGE, *PERMISSIONS)
                    .setRationale(R.string.cam_and_storage_rationale)
                    .setPositiveButtonText(R.string.rationale_ask_ok)
                    .setNegativeButtonText(R.string.rationale_ask_cancel)
                    //.setTheme(R.style.AppTheme)
                    .build()
            )
        }
    }

//    private fun uploadImageDetails() {
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(intent, PICKER_IMAGE_REQUEST)
//    }

}

//@BindingAdapter("app:imageUrl")
//fun setImageUri(view: ImageView, imageUri: Uri?) {
//    view.setImageURI(imageUri)
//    Picasso.get().load(imageUri).into(view)
//    Log.e("setImageUri", "image uri1 Test $imageUri")
//}

//@BindingAdapter("text")
//fun setText(view: EditText, double: Double) {
//    view.setText(double.toString())
//    Log.e("setImageUri", "image uri1 Test $double")
//}