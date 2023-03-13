package sofascore.academy.sofaproject.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.databinding.TextLayoutAndEditTextBinding

class TextLayoutAndEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: TextLayoutAndEditTextBinding = TextLayoutAndEditTextBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    private val minNumOfCharacters: Int
    private val maxNumOfCharacters: Int
    private val minIntValue: Int
    private val maxIntValue: Int

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.TextLayoutAndEditText, 0, 0).apply {
            try {
                binding.textInputLayout.hint = getString(R.styleable.TextLayoutAndEditText_hint)
                binding.editText.inputType =
                    getInt(R.styleable.TextLayoutAndEditText_inputType, 1)

                minNumOfCharacters =
                    getInt(R.styleable.TextLayoutAndEditText_minNumberOfCharacters, -1)
                maxNumOfCharacters =
                    getInt(R.styleable.TextLayoutAndEditText_maxNumberOfCharacters, -1)
                minIntValue = getInt(R.styleable.TextLayoutAndEditText_minIntValue, -1)
                maxIntValue = getInt(R.styleable.TextLayoutAndEditText_maxIntValue, -1)

                if (minNumOfCharacters < -1 || maxNumOfCharacters < -1 || minIntValue < -1 || maxIntValue < -1) {
                    throw IllegalArgumentException(resources.getString(R.string.validation_exception))
                }
            } finally {
                recycle()
            }
        }
    }

    fun getText(): String {
        return binding.editText.text.toString()
    }

    fun clearText() {
        binding.editText.text?.clear()
    }

    fun getHint(): String {
        return binding.textInputLayout.hint.toString()
    }

    fun setErrorText(errText: String) {
        binding.textInputLayout.error = errText
    }

    fun validateInput(): Boolean {
        var validated = true

        if (getText().isEmpty()) {
            setErrorText(resources.getString(R.string.missing_field_validation))
            return false
        }

        minNumOfCharacters.let {
            if (it != -1 && getText().length < it) {
                setErrorText(resources.getString(R.string.min_number_characters_validation) + " " + it)
                validated = false
            }
        }

        maxNumOfCharacters.let {
            if (it != -1 && getText().length > it) {
                setErrorText(resources.getString(R.string.max_number_characters_validation) + " " + it)
                validated = false
            }
        }

        minIntValue.let {
            if (it != -1 && getText().toInt() < it) {
                setErrorText(resources.getString(R.string.min_value__validation) + " " + it)
                validated = false
            }
        }

        maxIntValue.let {
            if (it != -1 && getText().toInt() > it) {
                setErrorText(resources.getString(R.string.max_value_validation) + " " + it)
                validated = false
            }
        }

        if (validated) binding.textInputLayout.error = null
        return validated
    }
}
