package com.example.firebaseauthwithmvvm.ui.storeProducts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.firebaseauthwithmvvm.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_product_preview.*

class ProductPreviewActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private val PERCENTAGE_TO_SHOW_IMAGE = 300
    private var mMaxScrollSize = 0
    private var mIsImageHidden = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hiding the toolbar
        supportActionBar!!.hide()

        setContentView(R.layout.activity_product_preview)

    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        appBar.addOnOffsetChangedListener(this)

    }


    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (mMaxScrollSize == 0) mMaxScrollSize = appBarLayout!!.totalScrollRange

        val currentScrollPercentage: Int = (Math.abs(verticalOffset) * 100
                / mMaxScrollSize)

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true
                ViewCompat.animate(ivWantToCollapse).scaleY(0F).scaleX(0F)
                    .withEndAction({ ivWantToCollapse.setEnabled(false) }).start()
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false
                ViewCompat.animate(ivWantToCollapse).scaleY(1F).scaleX(1F)
                    .withEndAction({ ivWantToCollapse.setEnabled(true) }).start()
            }
        }


    }
}
