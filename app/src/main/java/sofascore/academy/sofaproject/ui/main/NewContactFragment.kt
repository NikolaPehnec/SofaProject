package sofascore.academy.sofaproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.data.Person
import sofascore.academy.sofaproject.databinding.FragmentNewContactBinding

class NewContactFragment : Fragment() {
    private val peopleViewModel: PeopleViewModel by activityViewModels()
    private var _binding: FragmentNewContactBinding? = null
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
        _binding = FragmentNewContactBinding.inflate(inflater, container, false)

        binding.newPersonLinearLayout.children.filterIsInstance(TextInputLayout::class.java)
            .forEach { txtInptLayout ->
                txtInptLayout.editText?.let {
                    editTextFields.add(it as TextInputEditText)
                }
            }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addFab.setOnClickListener {
            addNewPerson()
        }
    }

    private fun addNewPerson() {
        val firstName = binding.firstName.text.toString()
        val lastName = binding.lastName.text.toString()
        val age = binding.age.text.toString()
        val oib = binding.oib.text.toString()
        val gender = binding.gender.text.toString()
        val education = binding.education.text.toString()
        val fatherName = binding.fatherName.text.toString()
        val motherName = binding.motherName.text.toString()
        val nationality = binding.nationality.text.toString()
        val additionalInfo = binding.additionalInfo.text.toString()

        if (validateData()) {
            peopleViewModel.addPerson(
                Person(
                    firstName,
                    lastName,
                    age,
                    oib,
                    gender,
                    education,
                    motherName,
                    fatherName,
                    nationality,
                    additionalInfo
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
        Toast.makeText(requireContext(), getString(R.string.add_person_success), Toast.LENGTH_SHORT)
            .show()
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
