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
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.data.FightingStyle
import sofascore.academy.sofaproject.data.Stance
import sofascore.academy.sofaproject.databinding.FragmentNewFighterBinding
import sofascore.academy.sofaproject.utils.TextLayoutAndEditText

class NewFighterFragment : Fragment() {
    private val fighterViewModel: FighterViewModel by activityViewModels()
    private var _binding: FragmentNewFighterBinding? = null
    private val binding get() = _binding!!
    private val textFields = mutableListOf<TextLayoutAndEditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewFighterBinding.inflate(inflater, container, false)

        textFields.clear()
        binding.newFighterLinearLayout.children.filterIsInstance(TextLayoutAndEditText::class.java)
            .forEach {
                textFields.add(it)
            }

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            Stance.values().map { it.stanceName }.toTypedArray()
        )
        binding.stanceDropdownMenu.setStringArrayAdapter(adapter)

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
                    binding.firstName.getText(),
                    binding.lastName.getText(),
                    binding.nickname.getText(),
                    binding.height.getText(),
                    binding.weight.getText(),
                    binding.reach.getText(),
                    Stance.fromString(binding.stanceDropdownMenu.getSelectedItemText())!!,
                    FightingStyle.fromString(checkedRadioButton.text.toString())!!,
                    binding.win.getText(),
                    binding.lose.getText(),
                    binding.draw.getText(),
                )
            )

            showSuccessNotification()
            clearFields()
        }
    }


    fun validateData(): Boolean {
        var validated = true
        textFields.forEach {
            if (!it.validateInput())
                validated = false
        }

        if (!binding.stanceDropdownMenu.validateInput())
            validated = false

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
        textFields.forEach {
            it.clearText()
        }

        binding.stanceDropdownMenu.clearText()
        binding.firstName.requestFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
