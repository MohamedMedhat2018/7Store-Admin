package com.example.firebaseauthwithmvvm.ui.add_product

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseauthwithmvvm.R
import com.example.firebaseauthwithmvvm.data.model_test.Product
import kotlinx.android.synthetic.main.activity_test.*

class addProductActivity : AppCompatActivity() {

    val products: ArrayList<Product> = ArrayList()
    val TAG: String = this.javaClass.simpleName

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        //hiding the toolbar
        supportActionBar!!.hide()
        supportActionBar!!.setShowHideAnimationEnabled(true)

        addDummyData()

        // Creates a vertical Layout Manager
        rv_store_products.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        rv_store_products.adapter = dummyDataRecyclerView(products, this)

//        val offset: Int = nsv_product_parent.computeVerticalScrollOffset()
//        val extent: Int = nsv_product_parent.computeVerticalScrollExtent()
//        val range: Int = nsv_product_parent.computeVerticalScrollRange()

//        Log.e(TAG, "Scroll " + offset + ", " + extent + ", " + range)

        var mImageViewHeight: Int

        //Retrieve a dimensional for a particular resource ID for use as a size in raw pixels.
        mImageViewHeight = resources.getDimensionPixelOffset(R.dimen.flexible_space_image_height)

        nsv_product_parent.viewTreeObserver
            .addOnScrollChangedListener {

                var fade_in: Animation =
                    AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
                var fade_out: Animation =
                    AnimationUtils.loadAnimation(applicationContext, R.anim.fade_out)

                //Math.max(n1, n2) return the maximum num of two num
                var scrollY: Int =
                    Math.min(Math.max(nsv_product_parent.scrollY, 0), mImageViewHeight)


                iv_product_img.translationY = (scrollY / 2).toFloat()


                var alpha: Float = (scrollY / mImageViewHeight).toFloat()


                Log.e(TAG, "test " + iv_product_img.translationY + " , " + mImageViewHeight)

                if (iv_product_img.translationY == resources.getDimension(R.dimen.flexible_space_image_height)) {
//                    iv_product_img.startAnimation(slide_left_in)
                    Log.e(TAG, "test3 " + iv_product_img.translationY)
                    supportActionBar!!.title = "Products"
                    supportActionBar!!.show()
                    Log.e(TAG, "Action bar " + supportActionBar!!.isShowing)

                    if (iv_product_img.isVisible) {
                        iv_product_img.visibility = View.INVISIBLE
                        tv_product_title.visibility = View.INVISIBLE
                    }

                } else {
                    Log.e(TAG, "MMM1" + iv_product_img.translationY)
                    supportActionBar!!.hide()
//                    tv_product_title.alpha = alpha
                    if (!iv_product_img.isVisible) {
                        iv_product_img.visibility = View.VISIBLE
                        tv_product_title.startAnimation(fade_in)
                        tv_product_title.visibility = View.VISIBLE
                        iv_product_img.startAnimation(fade_in)
                    }
                }

                //top edge of the displayed part of your view
//                val scrollY: Int = nsv_product_parent.scrollY
                //the height of the content of scrollView.
//                val scrollContentHeight: Int = rv_store_products.getChildAt(0).height

//                iv_product_img.layoutParams.height = (iv_product_img.height - scrollY/2)

//                iv_product_img.animate().scaleY(400f)
//                Log.e(TAG, "Scroll " + scrollY + ", " + scrollContentHeight)

                //screen height
//                val display = windowManager.defaultDisplay
//                val size = Point()
//                display.getSize(size)
//                val width: Int = size.x
//                val height: Int = size.y
//
//                Log.e(TAG, "height " + height + ", " + iv_product_img.layoutParams.height)

                //the max height of top edge in full scrolled
//                val percent =
//                    (scrollY.toFloat() / (scrollContentHeight - height + imageHight).toFloat()).toDouble()


//                val scrollViewHeight: Int =
//                    nsv_product_parent.scrollY
////                - nsv_product_parent.height
//
//                Log.e(TAG, "Height" + scrollViewHeight)
//
//                var imageHight =
//                    iv_product_img.layoutParams.height / baseContext.resources.displayMetrics.density
//                Log.e(TAG, "IMAGE TEST " + imageHight)
//
////                val getScrollY: Int = nsv_product_parent.scrollY
////
//                if (imageHight > 0.0) {
//
//                    Log.e(TAG, "  image contain " + imageHight)
//
//                    imageHight -= scrollViewHeight
//                    iv_product_img.scrollY = imageHight.toInt()
//                    iv_product_img.visibility = View.VISIBLE
//                    Log.e(TAG, "  image contain " + imageHight)
//
//                } else {
//                    iv_product_img.visibility = View.GONE
//                }
//
//                val scrollPosition = getScrollY / scrollViewHeight * 100.0
//                Log.i(
//                    "scrollview", "scroll Percent Y: " + scrollPosition.toInt() + " , scrollViewHeight " + scrollViewHeight +
//                            ", getScrollY " + getScrollY + ", scrollPosition " + scrollPosition + ", img after scroll" + imageHight)
            }
    }

    fun addDummyData() {

        products.add(
            Product(
                "Black 7zaza ", 20,
                "data:image/jpeg;base64,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"
            )
        )
        products.add(
            Product(
                "white 7zaza", 10,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsJj0btfTBY63Fxz4Az_J2-adEusfC3RRZ3M5_fOCU-yzmRna6ug&s"
            )
        )
        products.add(
            Product(
                "green 7zaza", 5,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlUV4MCkOGZgQlNvD_cjDiRB-vVAVodddUnEvZaH_LASO2HKFm&s"
            )
        )
        products.add(
            Product(
                "blue 7zaza", 8,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqbr_OQVDmGGQrj0mXu-IHDSaIM4YNiOINAA6hXpseUO_ZWs8j&s"
            )
        )
        products.add(
            Product(
                "blue 7zaza", 8,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqbr_OQVDmGGQrj0mXu-IHDSaIM4YNiOINAA6hXpseUO_ZWs8j&s"
            )
        )
        products.add(
            Product(
                "blue 7zaza", 8,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqbr_OQVDmGGQrj0mXu-IHDSaIM4YNiOINAA6hXpseUO_ZWs8j&s"
            )
        )
    }
}