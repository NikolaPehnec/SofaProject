package sofascore.academy.sofaproject.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sofascore.academy.sofaproject.data.Person

class PeopleViewModel : ViewModel() {

    private val _peopleList: MutableLiveData<MutableList<Person>> = MutableLiveData()
    private val peopleList: LiveData<MutableList<Person>> = _peopleList

    fun addPerson(person: Person) {
        _peopleList.value?.add(person)
        _peopleList.value = _peopleList.value
    }
}