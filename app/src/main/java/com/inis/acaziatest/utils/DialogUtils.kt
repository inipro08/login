package com.inis.acaziatest.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import com.inis.acaziatest.R


object DialogUtils {

    fun createDownloadDialog(
        context: Context?, cancelable: Boolean = false, canceledOnTouchOutside: Boolean = false
    ): AlertDialog? =
        context?.let {
            AlertDialog.Builder(context)
                .setView(R.layout.layout_loading_dialog)
                .create().apply {
                    setCancelable(cancelable)
                    setCanceledOnTouchOutside(canceledOnTouchOutside)
                    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
        }

    fun createAnimationDialog(
        context: Context?, cancelable: Boolean = false, canceledOnTouchOutside: Boolean = false
    ): AlertDialog? =
        context?.let {
            AlertDialog.Builder(context)
                .setView(R.layout.layout_success_dialog)
                .create().apply {
                    setCancelable(cancelable)
                    setCanceledOnTouchOutside(canceledOnTouchOutside)
                    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
        }


    fun showMessage(
        context: Context?,
        title: String? = null,
        message: String? = null,
        textPositive: String? = null,
        positiveListener: (() -> Unit)? = null,
        textNegative: String? = null,
        negativeListener: (() -> Unit)? = null,
        cancelable: Boolean = false,
        canceledOnTouchOutside: Boolean = false
    ): AlertDialog? =
        context?.let {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(textPositive) { _, _ -> positiveListener?.invoke() }
                .setNegativeButton(textNegative) { _, _ -> negativeListener?.invoke() }
                .setCancelable(cancelable)
                .create().apply {
                    setCanceledOnTouchOutside(canceledOnTouchOutside)
                    show()
                }
        }

    fun showMessage(
        context: Context?,
        title: Int? = null,
        message: Int? = null,
        textPositive: Int? = null,
        positiveListener: (() -> Unit)? = null,
        textNegative: Int? = null,
        negativeListener: (() -> Unit)? = null,
        cancelable: Boolean = false,
        canceledOnTouchOutside: Boolean = false
    ): AlertDialog? =
        context?.let { ctx ->
            AlertDialog.Builder(ctx).apply {
                title?.let { setTitle(it) }
                message?.let { setMessage(it) }
                textPositive?.let { setPositiveButton(it) { _, _ -> positiveListener?.invoke() } }
                textNegative?.let { setNegativeButton(it) { _, _ -> negativeListener?.invoke() } }
            }
                .setCancelable(cancelable)
                .create().apply {
                    setCanceledOnTouchOutside(canceledOnTouchOutside)
                    show()
                }
        }
}
