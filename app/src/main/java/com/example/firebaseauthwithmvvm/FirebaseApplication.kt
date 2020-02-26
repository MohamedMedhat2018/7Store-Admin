package com.example.firebaseauthwithmvvm

import android.app.Application
import com.example.firebaseauthwithmvvm.data.firebase.FirebaseSource
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.data.repository.UserRepository
import com.example.firebaseauthwithmvvm.ui.addItemToStore.StoreItemViewModelFactory
import com.example.firebaseauthwithmvvm.ui.auth.AuthViewModelFactory
import com.example.firebaseauthwithmvvm.ui.home2.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

//mast ad it to manifests  android:name=".FirebaseApplication"
class FirebaseApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FirebaseApplication))

        bind() from singleton { FirebaseSource() }
        bind() from singleton { UserRepository(instance()) }
        bind() from singleton { StoreProductRepo(instance()) }
        bind() from singleton { AuthViewModelFactory(instance()) }
        bind() from singleton { StoreItemViewModelFactory(instance()) }
        bind() from singleton { HomeViewModelFactory(instance()) }

    }
}