package com.example.firebaseauthwithmvvm.ui.addItemToStore

import android.Manifest
import android.app.Activity
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
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import pl.aprilapps.easyphotopicker.EasyImage
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest


class AddStoreItemActivity : AppCompatActivity(), KodeinAware {

    private val PICKER_IMAGE_REQUEST = 1
    lateinit var imageUri: Uri

    companion object {
        //to be constant
        private const val RC_CAMERA_AND_STORAGE = 121

    }

    val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private lateinit var dialog: BottomSheetDialog


    private lateinit var viewModel: StoreItemViewModel
    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()

    val TAG = AddStoreItemActivity::class.java.simpleName


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        updateProfilePhoto()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hiding the toolbar
        supportActionBar!!.hide()

        setContentView(R.layout.activity_add_storage_item)

        Log.e(TAG, "AddStoreItemActivity: OnCreate")


        val binding: ActivityAddStorageItemBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_storage_item)
//        val handler: Handler =
        viewModel = ViewModelProviders.of(this).get(StoreItemViewModel::class.java)

        //create observer on viewModel.observeViewModelEvents() to listen to any view call this method
//        viewModel.observeViewModelEvents().observe(this, Observer {
//            val event = it.takeUnless { it == null || it.handler } ?: return@Observer
////            handlerViewModlAction(event)
//            Log.e(TAG, "happpppy1")
//
//        })

        viewModel.SetImage().observe(this, Observer {
            Log.e(TAG, "happpppy1 $it")
//            uploadImageDetails()
            requestCamAndStoragePerms()
        })

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

    }

    //Start Easy Permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


//     fun updateProfilePhoto()
//    {
//        BottomSheetMenuDialogFragment.Builder(this)
//            .setSheet(R.menu.bottom_sheet)
//            .setTitle(R.string.options)
//            .setListener(myListener)
//            .setObject(myObject)
//            .show()
//    }

    private fun updateProfilePhoto() {
        dialog = BottomSheetBuilder(this, null)
            .setMode(BottomSheetBuilder.MODE_LIST) //                .setMode(BottomSheetBuilder.MODE_GRID)
            .addDividerItem()
            .expandOnStart(true)
            .setDividerBackground(R.color.grey_55)
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

    private fun uploadImageDetails() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICKER_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICKER_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data!!
//            viewModel.weakReference = imageUri
            var path: String = imageUri.toString()
            Log.e(TAG, "Image" + path)


        }

    }

//    @BindingAdapter({"bind:uploaImage"})
//    public

    //pass the activity to ViewModelEvent so u can call use any activity method there without use here
    //so now ViewModelEvent.handler work like Activity now ad do all activity work there  :) :)
//    protected fun handlerViewModlAction(event: ViewModelEvent) {
//        event.handle(this)
//        Log.e(TAG, "happpppy2")
//
//    }

}
