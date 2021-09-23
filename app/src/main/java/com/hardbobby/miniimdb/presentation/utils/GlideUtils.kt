package com.hardbobby.miniimdb.presentation.utils

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.signature.ObjectKey
import com.hardbobby.data.common.Constants
import com.hardbobby.miniimdb.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
object GlideUtils {

    /**
     * Call glide with app context
     */
    fun getGlide(
        uri: String? = "",
        context: Context,
        thumbnailValue: Float = 0.5f
    ): RequestBuilder<Drawable> {
        val calendar = Calendar.getInstance()
        val stringSignature =
            SimpleDateFormat("yyyy w", Constants.APPS_LOCALE).format(calendar.time)

        return  GlideApp.with(context)
            .load(uri.orEmpty())
            .thumbnail(thumbnailValue)
            .placeholder(context.getDrawable(R.drawable.ic_picture_placeholder))
            .signature(ObjectKey(stringSignature))
    }
}
