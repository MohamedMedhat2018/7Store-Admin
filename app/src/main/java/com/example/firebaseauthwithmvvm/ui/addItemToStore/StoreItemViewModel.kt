package com.example.a7storenavigationdrawer.ui.addItemToStore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.ui.addItemToStore.ViewModelEvent

//(private val repository: UserRepository)
class StoreItemViewModel : ViewModel() {


//    val user by lazy {
//        repository.currentUser()
//    }

//    private val observableTest = MutableLiveData<>()


    private var uploadImageLiveData = MutableLiveData<String>()

    //observe
    fun SetImage(): LiveData<String> = uploadImageLiveData

    //onClick
    fun StartUploadImage() {
        //upload data
        uploadImageLiveData.value = "Mohamed"
        Log.e("test", "teeest1234")

    }



    //create observer getter
    private val observableEvents = MutableLiveData<ViewModelEvent>()

    // launch observer setter
    fun observeViewModelEvents(): LiveData<ViewModelEvent> = observableEvents

    //   Creates a MutableLiveData with value assigned to it. (event)
    fun PostViewModelEvent(event: ViewModelEvent) {
        Log.e("test", "teeest")
        observableEvents.postValue(event)
    }


//    @Nullable
//    var weakReference: UploadImage? = null
////    var ll = weakReference
//
//    fun onclick() {
//        weakReference?.UploadImageDetails("n")
//    }


//    fun uploadProductImage(view: View) {
//        val intent: Intent = Intent()
//        intent.type = "image/*"
//        intent.setAction(Intent.ACTION_GET_CONTENT)
//        view.startActivityForResult(intent, PICK_IMAGE_REQUEST)
//    }

}