package com.example.firebaseauthwithmvvm.ui.finihsed_orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.firebaseauthwithmvvm.R

class FinishedOrderFragment : Fragment() {

    companion object {
        fun newInstance() = FinishedOrderFragment()
    }

    val TAG = this.javaClass.simpleName

    private lateinit var viewModel: FinishedOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(FinishedOrderViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.show()


        val root = inflater.inflate(R.layout.finished_order_fragment, container, false)


        val textView: TextView = root.findViewById(R.id.text_finished_order)

        viewModel.textLiveData.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(FinishedOrderViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}
