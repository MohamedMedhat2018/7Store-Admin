package com.example.a7storenavigationdrawer.ui.addItemToStore

import android.content.Intent
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseauthwithmvvm.data.repository.UserRepository
import com.example.firebaseauthwithmvvm.ui.addItemToStore.UploadImage
import com.example.firebaseauthwithmvvm.ui.addItemToStore.ViewModelEvent
import java.lang.ref.WeakReference

//(private val repository: UserRepository)
class StoreItemViewModel : ViewModel() {

//    val user by lazy {
//        repository.currentUser()
//    }

    //create observer getter
    private val observableEvents = MutableLiveData<ViewModelEvent>()

    // launch observer setter
    fun observeViewModelEvents(): LiveData<ViewModelEvent> = observableEvents

    //   Creates a MutableLiveData with value assigned to it. (event)
    protected fun PostViewModelEvent(event: ViewModelEvent) {
        observableEvents.postValue(event)
    }


    @Nullable
    var weakReference: UploadImage? = null
//    var ll = weakReference

    fun onclick() {
        weakReference?.UploadImageDetails("n")
    }


//    fun uploadProductImage(view: View) {
//        val intent: Intent = Intent()
//        intent.type = "image/*"
//        intent.setAction(Intent.ACTION_GET_CONTENT)
//        view.startActivityForResult(intent, PICK_IMAGE_REQUEST)
//    }

}