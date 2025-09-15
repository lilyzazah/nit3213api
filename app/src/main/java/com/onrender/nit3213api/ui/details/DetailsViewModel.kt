package com.onrender.nit3213api.ui.details


import androidx.lifecycle.ViewModel
import com.onrender.nit3213.data.model.Entity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private var _entity: Entity? = null
    val entity: Entity?
        get() = _entity

    fun setEntity(e: Entity) {
        _entity = e
    }
}