package com.example.firebaseauthwithmvvm.ui.store

import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.constants.Childs
import com.example.firebaseauthwithmvvm.data.firebase.FirebaseSource
import com.example.firebaseauthwithmvvm.data.repository.StoreProductRepo
import com.example.firebaseauthwithmvvm.databinding.FragmentStoreBinding
import com.example.firebaseauthwithmvvm.models.StoreProduct
import com.example.firebaseauthwithmvvm.ui.store_details.StoreDetailsActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_store.*
import kotlin.math.abs


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [StoreFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [StoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class StoreFragment : Fragment(), AppBarLayout.OnOffsetChangedListener, OnItemClick {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//    private var listener: OnFragmentInteractionListener? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    private lateinit var storeViewModel: StoreViewModel
    private val TAG = this.javaClass.simpleName

    //    private val PERCENTAGE_TO_SHOW_IMAGE = 65
    private val PERCENTAGE_TO_SHOW_IMAGE = 80
    private var mMaxScrollSize = 0
    private var mIsImageHidden = false

//    lateinit var storeDetailsViewModel: StoreDetailsViewModel

//    private lateinit var option: FirebaseRecyclerOptions<StoreProduct>
//    private lateinit var sAdapter: StoreAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        app_bar_layout.addOnOffsetChangedListener(this)

//        (activity as AppCompatActivity).supportActionBar?.title = "Title"

        (activity as AppCompatActivity).supportActionBar?.hide()

//        recycler_view_products.also {
//            it.layoutManager = LinearLayoutManager(context)
//            it.adapter = StoreAdapter(option)
////            sAdapter.startListening()
//            Log.e(TAG, "data ${option}")
//        }


//        recycler_view_products.also {
//            it.layoutManager = LinearLayoutManager(context)
//            sAdapter = StoreAdapter(option)
//            it.adapter = sAdapter
//            sAdapter.startListening()
//            Log.e(TAG, "data00 ${option}")
//        }

//        Log.e(TAG, "data2 ${option}")

//        activity?.actionBar!!.hide()
    }

    //    private lateinit var adapter: StoreAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
//        val root = inflater.inflate(R.layout.fragment_store, container, false)

        //for testing

        //create viewModel for display details
//        storeDetailsViewModel = activity?.run {
//            ViewModelProviders.of(
//                this,
//                StoreDetailsViewModelFactory()
//            ).get(StoreDetailsViewModel::class.java)
//        } ?: throw Exception("Invalid Activity")


        val binding: FragmentStoreBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false)
        storeViewModel = ViewModelProviders.of(
            this.activity!!,
            StoreViewModelFactory(Application(), StoreProductRepo(FirebaseSource()))
        ).get(StoreViewModel::class.java)


//        storeViewModel.getProduct()
        storeViewModel.getProducts()



        storeViewModel.storeProductModel.observe(viewLifecycleOwner, Observer { ListOfProducts ->
            recycler_view_products.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
//                option = options
                val adapter = StoreAdapter(ListOfProducts, this)
                it.adapter = adapter
//                adapter.startListening()
                Log.e(TAG, "it's return ${ListOfProducts.size}")
//                Log.e(TAG, "data3 ${option}")

                for (product in ListOfProducts) {
                    Log.e(TAG, "Product name  = ${product.product_name}")
                }

            }
        })

//        option = storeViewModel.getProducts()

//        sAdapter.startListening()

        /*if (option != null) {
            sAdapter = StoreAdapter(option)
            recycler_view_products.adapter = sAdapter
            sAdapter.startListening()
        }*/

        storeViewModel.storeProductTest.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "it's return $it")
        })

        binding.viewmodel = storeViewModel
        binding.lifecycleOwner = this

//        storeViewModel.itemStoreListener = this

//        val textView: TextView = root.findViewById(R.id.text_store)
//        storeViewModel.textLiveData.observe(this, Observer {
//            textView.text = it
//            Log.e(TAG, "test it $it")
//        })

        val root = binding.root

        return root
    }


    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {

        if (mMaxScrollSize == 0) mMaxScrollSize = appBarLayout!!.totalScrollRange

        val currentScrollPercentage: Int = (abs(verticalOffset) * 100 / mMaxScrollSize)

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            Log.e(TAG, "111 LOLLIPOP")
            if (!mIsImageHidden) {
                mIsImageHidden = true
                ViewCompat.animate(iv_wanna_collapse).scaleY(0F).scaleX(0F)
                    .withEndAction {
                        //when animation finish
                        iv_wanna_collapse.isEnabled = false
                        //                        toolbar.visibility = View.VISIBLE
                        //                        supportActionBar!!.show()
                        //                        supportActionBar!!.hide()
//                        tv_product_title.visibility = View.GONE
                        tv_product_title2.visibility = View.VISIBLE
//                        (activity as AppCompatActivity).supportActionBar?.show()

                    }.start()
                Log.e(TAG, "111 LOLLIPOP")

            }

        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            Log.e(TAG, "222 LOLLIPOP $currentScrollPercentage")

            if (mIsImageHidden) {
                mIsImageHidden = false
                ViewCompat.animate(iv_wanna_collapse).scaleY(1F).scaleX(1F)
                    .withEndAction {
                        //before animation start
                        //                        supportActionBar!!.hide()
                        //                        supportActionBar!!.show()
                        iv_wanna_collapse.isEnabled = true
//                        tv_product_title.visibility = View.VISIBLE
                        (activity as AppCompatActivity).supportActionBar?.hide()
                        tv_product_title2.visibility = View.GONE

                    }.start()
                Log.e(TAG, "222 LOLLIPOP")

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Log.e(TAG, "it's LOLLIPOP")

                    val w: Window? = activity?.window // in Activity's onCreate() for instance
                    w?.setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
                    )
                    w?.setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    )
                } else {
                    Log.e(TAG, "less than LOLLIPOP")

                }

            }
        }
    }

    override fun onClick(view: View, storeProduct: StoreProduct) {
        Log.e("Test", "storeProduct ${storeProduct.product_name}")
//        var model: StoreProduct = StoreProduct()
//        model = storeProduct
        storeViewModel.getData(storeProduct)
//        storeViewModel.test()

        val gson: Gson = Gson()
        val json = gson.toJson(storeProduct)

        Prefs.edit().putString(Childs.PRODUCT.name, json).apply()

//        storeDetailsViewModel.storeProductModel.value = storeProduct

        val intent = Intent(context, StoreDetailsActivity::class.java)

//        val bundle = Bundle()

//        bundle.putSerializable(com.example.firebaseauthwithmvvm.constants.Constants.STORE_PRODUCTS, storeProduct)
//        intent.putExtras(bundle)
        startActivity(intent)


    }


//    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     *
//     *
//     * See the Android Training lesson [Communicating with Other Fragments]
//     * (http://developer.android.com/training/basics/fragments/communicating.html)
//     * for more information.
//     */
//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment StoreFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            StoreFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }

}
