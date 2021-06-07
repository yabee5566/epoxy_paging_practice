package com.example.epoxypagingpractice.epoxy

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.epoxypagingpractice.R
import com.example.epoxypagingpractice.databinding.VhTicketBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TicketModelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding:VhTicketBinding
    init {
        inflate(context, R.layout.vh_ticket,this)
        binding = VhTicketBinding.bind(rootView)
    }

    @TextProp
    fun setName(name:CharSequence?) {
        Log.d("TicketModelView", "setName $name")
        binding.name.text = name
    }
    @ModelProp
    fun setTicketId(id:Int){
        Log.d("TicketModelView", "setTicketId $id")
        binding.id.text = "$id"
    }
}
