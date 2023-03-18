package sofascore.academy.sofaproject.view.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.data.FightingStyle
import sofascore.academy.sofaproject.data.Stance
import sofascore.academy.sofaproject.databinding.NewFighterBottomSheetBinding
import sofascore.academy.sofaproject.utils.customviews.TextLayoutAndEditText
import sofascore.academy.sofaproject.viewmodel.FighterViewModel
import java.net.URL

// Layout and methods copied from NewFighterFragment

class NewFighterDialogFragment : BottomSheetDialogFragment() {
    private var _binding: NewFighterBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val fighterViewModel: FighterViewModel by activityViewModels()
    private val textFields = mutableListOf<TextLayoutAndEditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewFighterBottomSheetBinding.inflate(inflater, container, false)

        textFields.clear()
        binding.newFighterLinearLayout.children.filterIsInstance(TextLayoutAndEditText::class.java)
            .forEach {
                textFields.add(it)
            }

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item_1,
            Stance.values().map { it.stanceName }.toTypedArray()
        )
        binding.stanceDropdownMenu.setStringArrayAdapter(adapter)

        binding.addButon.setOnClickListener {
            addNewPerson()
        }

        return binding.root
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
                    URL("https://live-production.wcms.abc-cdn.net.au/eead633374dd407290c366118c1b679e?impolicy=wcms_crop_resize&cropH=2893&cropW=4340&xPos=34&yPos=0&width=862&height=575")
                )
            )

            showSuccessNotification()
            this.dismiss()
        } else {
            showErrorNotification()
        }
    }

    private fun validateData(): Boolean {
        var validated = true
        textFields.forEach {
            if (!it.validateInput()) {
                validated = false
            }
        }

        if (!binding.stanceDropdownMenu.validateInput()) {
            validated = false
        }

        return validated
    }

    private fun showSuccessNotification() {
        Snackbar.make(
            binding.root,
            getString(sofascore.academy.sofaproject.R.string.add_fighter_success),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showErrorNotification() {
        Toast.makeText(
            requireContext(),
            getString(sofascore.academy.sofaproject.R.string.add_fighter_error),
            Toast.LENGTH_SHORT
        ).show()
    }
}
