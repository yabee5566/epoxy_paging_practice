package com.example.epoxypagingpractice

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.example.epoxypagingpractice.databinding.HolderHeaderTitleWithMoreBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TitleWithMoreHolder @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val binding = HolderHeaderTitleWithMoreBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    private var titleRes: Int = 0
    private var titleText: String = ""

    @ModelProp
    @JvmOverloads
    fun setTitleRes(titleRes: Int = 0) {
        this.titleRes = titleRes
    }

    @ModelProp
    @JvmOverloads
    fun setTitleText(titleText: String = "") {
        this.titleText = titleText
    }

    @set:CallbackProp
    var onMoreClick: (() -> Unit)? = null

    @AfterPropsSet
    fun bind() {
        with(binding) {
            if (titleRes != 0) {
                title.text = context.getString(titleRes)
            } else {
                title.text = titleText
            }

            moreBtn.isVisible = onMoreClick != null
            moreBtn.setOnClickListener { onMoreClick?.invoke() }
        }
    }

    @OnViewRecycled
    fun unbind() {
        binding.moreBtn.setOnClickListener(null)
    }
}
