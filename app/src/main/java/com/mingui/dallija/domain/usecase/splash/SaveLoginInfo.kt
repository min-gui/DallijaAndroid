package com.mingui.dallija.domain.usecase.splash

import com.mingui.dallija.data.repository.AutoLoginRepository
import javax.inject.Inject

class SaveLoginInfo @Inject constructor(
    private val repository: AutoLoginRepository
) {

}