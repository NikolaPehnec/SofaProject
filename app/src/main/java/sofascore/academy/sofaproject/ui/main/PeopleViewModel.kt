package sofascore.academy.sofaproject.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sofascore.academy.sofaproject.data.Person

class PeopleViewModel : ViewModel() {

    private val _peopleList: MutableLiveData<MutableList<Person>> = MutableLiveData()
    val peopleList: LiveData<MutableList<Person>> = _peopleList

    init {
        _peopleList.value = mutableListOf()
    }

    fun addPerson(person: Person) {
        _peopleList.value?.add(person)
        _peopleList.value = _peopleList.value
    }
}