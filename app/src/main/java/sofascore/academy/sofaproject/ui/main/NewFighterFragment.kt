package sofascore.academy.sofaproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.data.FightingStyle
import sofascore.academy.sofaproject.data.Stance
import sofascore.academy.sofaproject.databinding.FragmentNewFighterBinding

class NewFighterFragment : Fragment() {
    private val fighterViewModel: FighterViewModel by activityViewModels()
    private var _binding: FragmentNewFighterBinding? = null
    private val binding get() = _binding!!
    private val editTextFields = mutableListOf<TextInputEditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewFighterBinding.inflate(inflater, container, false)

        binding.newFighterLinearLayout.children.filterIsInstance(TextInputLayout::class.java)
            .forEach { txtInptLayout ->
                txtInptLayout.editText?.let {
                    if (it is TextInputEditText)
                        editTextFields.add(it)
                }
            }

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            Stance.values().map { it.stanceName }.toTypedArray()
        )
        binding.stance.setSelection(0)
        binding.stance.setAdapter(adapter)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addFab.setOnClickListener {
            addNewPerson()
        }
    }

    private fun addNewPerson() {
        val checkedRadioButton =
            binding.root.findViewById<RadioButton>(binding.radioGroupFightingStyle.checkedRadioButtonId)

        if (validateData()) {
            fighterViewModel.addFighter(
                Fighter(
                    binding.firstName.text.toString(),
                    binding.lastName.text.toString(),
                    binding.nickname.text.toString(),
                    binding.height.text.toString(),
                    binding.weight.text.toString(),
                    binding.reach.text.toString(),
                    Stance.fromString(binding.stance.text.toString())!!,
                    FightingStyle.fromString(checkedRadioButton.text.toString())!!,
                    binding.win.text.toString(),
                    binding.lose.text.toString(),
                    binding.draw.text.toString(),
                )
            )

            showSuccessNotification()
            clearFields()
        }
    }

    /**
     * Basic validation
     */
    fun validateData(): Boolean {
        var validated = true

        editTextFields.forEach {
            if (it.text.toString().isEmpty()) {
                it.error = getString(R.string.missing_field_validation)
                validated = false
            }
        }

        return validated
    }

    private fun showSuccessNotification() {
        Toast.makeText(
            requireContext(),
            getString(R.string.add_fighter_success),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun clearFields() {
        editTextFields.forEach {
            it.text?.clear()
        }

        binding.firstName.requestFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
