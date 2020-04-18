package com.example.firebaseauthwithmvvm.ui.orders

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.data.firebase.FirebaseSource
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.databinding.FragmentStoreBinding
import com.example.firebaseauthwithmvvm.databinding.OrderFragmentBinding

class OrderFragment : Fragment() {

    companion object {
        fun newInstance() = OrderFragment()
    }

    private lateinit var viewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: OrderFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.order_fragment, container, false)

        viewModel = ViewModelProviders.of(
            this.activity!!, OrderVierModelFactory(
                Application(), StoreProductRepo(
                    FirebaseSource()
                )
            )
        ).get(OrderViewModel::class.java)

        val root = binding.root

        (activity as AppCompatActivity).supportActionBar?.show()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        return root
    }


//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}
