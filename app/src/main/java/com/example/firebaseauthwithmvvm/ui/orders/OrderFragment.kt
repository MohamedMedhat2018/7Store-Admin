package com.example.firebaseauthwithmvvm.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.firebaseauthwithmvvm.R

class OrderFragment : Fragment() {

    companion object {
        fun newInstance() = OrderFragment()
    }

    private lateinit var viewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)

        val root = inflater.inflate(R.layout.order_fragment, container, false)

        val textView: TextView = root.findViewById(R.id.text_order)

        viewModel.textLiveDta.observe(this, Observer {
            textView.text = it
        })


        return root
    }


//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}
