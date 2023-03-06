package sofascore.academy.sofaproject.dz1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Change to make a pull request
    private fun setListeners() {
        binding.buttonHideShowText.setOnClickListener {
            binding.helloTextView.isVisible = !binding.helloTextView.isVisible

            if (binding.buttonHideShowText.text == getText(R.string.hide)) {
                binding.buttonHideShowText.text = getText(R.string.show)
            } else {
                binding.buttonHideShowText.text = getText(R.string.hide)
            }
        }

        binding.buttonChangeText.setOnClickListener {
            if (binding.helloTextView.text == getText(R.string.hello_text)) {
                binding.helloTextView.text = getText(R.string.hello_text2)
            } else {
                binding.helloTextView.text = getText(R.string.hello_text)
            }
        }
    }
}
