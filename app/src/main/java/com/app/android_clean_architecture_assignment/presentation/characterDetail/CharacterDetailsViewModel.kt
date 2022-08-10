package com.app.android_clean_architecture_assignment.presentation.characterDetail

import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
) : BaseViewModel()