package com.bala.codeglotask.utils

import android.content.Context
import android.view.View
import android.widget.Toast

@Suppress("UNCHECKED_CAST")
class CommonUtils {

    companion object {
        fun showToastMessage(context: Context, message: String?) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        fun showHideView(view: View, isToShow: Boolean) {
            if (isToShow) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }
    }
}