package com.example.epoxypagingpractice.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.example.epoxypagingpractice.R
import com.example.epoxypagingpractice.databinding.VhLoadBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class LoadStateModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    var binding: VhLoadBinding

    init {
        inflate(context, R.layout.vh_load, this)
        binding = VhLoadBinding.bind(rootView)
    }
    @set:CallbackProp
    var retryListener: (() -> Unit)? = null

    @ModelProp(options = [ModelProp.Option.IgnoreRequireHashCode])
    fun setLoadState(state: LoadState) {
        binding.loadingText.text = "isLoading"
        binding.loadingText.isVisible = state is LoadState.Loading
        binding.retryBtn.isVisible = state is LoadState.Error
        binding.retryBtn.setOnClickListener{retryListener?.invoke()}
    }
}