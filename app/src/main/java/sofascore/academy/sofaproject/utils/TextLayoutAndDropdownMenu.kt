package sofascore.academy.sofaproject.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.databinding.TextLayoutAndDropdownMenuBinding

class TextLayoutAndDropdownMenu @JvmOverloads constructor(
    context: Context, attrs: AttributeSet, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: TextLayoutAndDropdownMenuBinding =
        TextLayoutAndDropdownMenuBinding.inflate(
            LayoutInflater.from(context), this, true
        )


    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.TextLayoutAndEditText, 0, 0)
            .apply {
                try {
                    binding.textInputLayout.hint = getString(R.styleable.TextLayoutAndEditText_hint)
                } finally {
                    recycle()
                }
            }
    }

    fun setStringArrayAdapter(adapter: ArrayAdapter<String>) {
        binding.autoCompleteTv.setAdapter(adapter)
    }

    fun getSelectedItemText(): String {
        return binding.autoCompleteTv.text.toString()
    }

    fun setErrorText(errText: String) {
        binding.textInputLayout.error = errText
    }

    fun getHint(): String {
        return binding.textInputLayout.hint.toString()
    }

    fun validateInput(): Boolean {
        if (getSelectedItemText().isEmpty()) {
            setErrorText(getHint() + " " + resources.getString(R.string.missing_field_validation))
            return false
        } else {
            binding.textInputLayout.isErrorEnabled = false
        }

        return true
    }

}