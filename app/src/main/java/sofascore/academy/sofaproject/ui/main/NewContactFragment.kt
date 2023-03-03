package sofascore.academy.sofaproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.data.Person
import sofascore.academy.sofaproject.databinding.FragmentNewContactBinding

class NewContactFragment : Fragment() {
    private lateinit var peopleViewModel: PeopleViewModel
    private var _binding: FragmentNewContactBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        peopleViewModel = ViewModelProvider(this)[PeopleViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewContactBinding.inflate(inflater, container, false)
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

        if (validateData(
                firstName,
                lastName,
                age,
                oib,
                gender,
                education,
                fatherName,
                motherName,
                nationality
            )
        ) {
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
    fun validateData(
        firstName: String,
        lastName: String,
        age: String,
        oib: String,
        gender: String,
        education: String,
        motherName: String, fatherName: String, nationality: String
    ): Boolean {
        var validated = true

        if (firstName.isEmpty()) {
            binding.firstName.error = getString(R.string.missing_field_validation)
            validated = false
        }
        if (lastName.isEmpty()) {
            binding.lastName.error = getString(R.string.missing_field_validation)
            validated = false
        }
        if (age.isEmpty()) {
            binding.age.error = getString(R.string.missing_field_validation)
            validated = false
        }
        if (oib.isEmpty()) {
            binding.oib.error = getString(R.string.missing_field_validation)
            validated = false
        }
        if (gender.isEmpty()) {
            binding.gender.error = getString(R.string.missing_field_validation)
            validated = false
        }
        if (education.isEmpty()) {
            binding.education.error = getString(R.string.missing_field_validation)
            validated = false
        }
        if (motherName.isEmpty()) {
            binding.motherName.error = getString(R.string.missing_field_validation)
            validated = false
        }
        if (fatherName.isEmpty()) {
            binding.fatherName.error = getString(R.string.missing_field_validation)
            validated = false
        }
        if (nationality.isEmpty()) {
            binding.nationality.error = getString(R.string.missing_field_validation)
            validated = false
        }

        return validated
    }

    private fun showSuccessNotification() {
        Toast.makeText(requireContext(), getString(R.string.add_person_success), Toast.LENGTH_SHORT)
            .show()
    }

    private fun clearFields() {
        binding.firstName.text?.clear()
        binding.lastName.text?.clear()
        binding.age.text?.clear()
        binding.oib.text?.clear()
        binding.gender.text?.clear()
        binding.education.text?.clear()
        binding.fatherName.text?.clear()
        binding.motherName.text?.clear()
        binding.nationality.text?.clear()
        binding.additionalInfo.text?.clear()

        binding.firstName.requestFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}