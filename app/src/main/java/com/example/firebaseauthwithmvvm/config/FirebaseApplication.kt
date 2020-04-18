package com.example.firebaseauthwithmvvm.config

import android.app.Application
import android.content.ContextWrapper
import com.example.firebaseauthwithmvvm.data.firebase.FirebaseSource
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.data.repository.UserRepository
import com.example.firebaseauthwithmvvm.ui.addItemToStore.StoreItemViewModelFactory
import com.example.firebaseauthwithmvvm.ui.addOrder.AddOrderViewModelFactory
import com.example.firebaseauthwithmvvm.ui.auth.AuthViewModelFactory
import com.example.firebaseauthwithmvvm.ui.home2.HomeViewModelFactory
import com.example.firebaseauthwithmvvm.ui.orders.OrderVierModelFactory
import com.example.firebaseauthwithmvvm.ui.store.StoreViewModelFactory
import com.example.firebaseauthwithmvvm.ui.store_details.StoreDetailsViewModelFactory
import com.pixplicity.easyprefs.library.Prefs
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

//must ad it to manifests  android:name=".FirebaseApplication"
class FirebaseApplicationConfig : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FirebaseApplicationConfig))

        bind() from singleton { FirebaseSource() }
        bind() from singleton { UserRepository(instance()) }
        bind() from singleton { StoreProductRepo(instance()) }
        bind() from singleton { AuthViewModelFactory(instance()) }
        bind() from singleton { StoreItemViewModelFactory(Application(), instance()) }
        bind() from singleton { StoreDetailsViewModelFactory() }
        bind() from singleton { StoreViewModelFactory(Application(), instance()) }
        bind() from singleton { HomeViewModelFactory(instance()) }
        bind() from singleton { OrderVierModelFactory(Application(), instance()) }
        bind() from singleton { AddOrderViewModelFactory(Application(), instance()) }


    }

    override fun onCreate() {
        super.onCreate()

        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}