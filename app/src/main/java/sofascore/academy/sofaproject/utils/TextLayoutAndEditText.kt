package sofascore.academy.sofaproject.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.databinding.TextLayoutAndEditTextBinding

class TextLayoutAndEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: TextLayoutAndEditTextBinding = TextLayoutAndEditTextBinding.inflate(
        LayoutInflater.from(context), this, true
    )


    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.TextLayoutAndEditText, 0, 0).apply {
            try {
                binding.textInputLayout.hint = getString(R.styleable.TextLayoutAndEditText_hint)
                binding.editText.inputType =
                    getInt(R.styleable.TextLayoutAndEditText_inputType, 1)
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
            setErrorText(getHint() + " " + resources.getString(R.string.missing_field_validation))
            return false
        }

        when (getHint()) {
            resources.getString(R.string.first_name_hint), resources.getString(R.string.last_name_hint) -> {
                if (getText().length < 3 || getText().length > 50) {
                    setErrorText(getHint() + " " + resources.getString(R.string.wrong_input_validation))
                    validated = false
                }
            }
            resources.getString(R.string.height_hint) -> {
                if (getText().toInt() < 100 || getText().toInt() > 250) {
                    setErrorText(getHint() + " " + resources.getString(R.string.wrong_input_validation))
                    validated = false
                }
            }
            resources.getString(R.string.weight_hint) -> {
                if (getText().toInt() < 30 || getText().toInt() > 250) {
                    setErrorText(getHint() + " " + resources.getString(R.string.wrong_input_validation))
                    validated = false
                }
            }
            resources.getString(R.string.reach_hint) -> {
                if (getText().toInt() < 100 || getText().toInt() > 300) {
                    setErrorText(getHint() + " " + resources.getString(R.string.wrong_input_validation))
                    validated = false
                }
            }
            resources.getString(R.string.win_hint), resources.getString(R.string.lose_hint),
            resources.getString(R.string.draw_hint) -> {
                if (getText().toInt() < 0 || getText().toInt() > 200) {
                    setErrorText(getHint() + " " + resources.getString(R.string.wrong_input_validation))
                    validated = false
                }
            }
        }

        if (validated) binding.textInputLayout.isErrorEnabled = false

        return validated
    }


}