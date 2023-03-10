package sofascore.academy.sofaproject.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sofascore.academy.sofaproject.data.Fighter

class FighterViewModel : ViewModel() {

    private val _fighterList: MutableLiveData<MutableList<Fighter>> = MutableLiveData()
    val fighterList: LiveData<MutableList<Fighter>> = _fighterList

    init {
        _fighterList.value = mutableListOf()
    }

    fun addFighter(fighter: Fighter) {
        _fighterList.value?.add(fighter)
        _fighterList.value = _fighterList.value
    }
}
