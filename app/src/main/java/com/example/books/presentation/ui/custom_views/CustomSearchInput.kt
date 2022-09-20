package com.example.books.presentation.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.books.R
import com.google.android.material.textfield.TextInputEditText

class CustomSearchInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val view = LayoutInflater
        .from(context)
        .inflate(R.layout.custom_search_input, this, true)


    private val input: TextInputEditText by lazy {
        view.findViewById(R.id.bt_input)
    }

    var textChangedListener: (input: String) -> Unit = {}

    init {
        setLayout(attrs)
        configureInputSearch()
    }

    private fun setLayout(attrs: AttributeSet?) {

        attrs?.let { attributeSet ->

            val atributes =
                context.obtainStyledAttributes(attributeSet, R.styleable.CustomSearchInput)

            val customHint = atributes.getString(R.styleable.CustomSearchInput_custom_hint)

            input.hint = customHint
            atributes.recycle()
        }
    }

    private fun configureInputSearch() {
        input.addTextChangedListener { input ->
            configureBackground(input.isNullOrEmpty())
            textChangedListener.invoke(input.toString())
        }
    }

    private fun configureBackground(empty: Boolean) {
        if (empty)
            input.backgroundTintList = null
        else
            input.backgroundTintList =
                ContextCompat.getColorStateList(context, android.R.color.white)
    }

}