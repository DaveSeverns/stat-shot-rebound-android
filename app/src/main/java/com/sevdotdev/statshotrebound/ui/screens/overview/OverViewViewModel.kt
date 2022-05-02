package com.sevdotdev.statshotrebound.ui.screens.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdotdev.statshotrebound.data.network.entities.Stats
import com.sevdotdev.statshotrebound.domain.model.Match
import com.sevdotdev.statshotrebound.domain.model.UserInfo
import com.sevdotdev.statshotrebound.domain.usecases.RecentMatchUseCase
import com.sevdotdev.statshotrebound.domain.usecases.UserInfoUseCase
import com.sevdotdev.statshotrebound.ui.state.ComposeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias MatchViewState = ComposeState<List<Match>>

@HiltViewModel
class OverViewViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val recentMatchUseCase: RecentMatchUseCase
): ViewModel() {
    fun updatePlayerInfo(playerId: String):Boolean {
        if (playerId.isEmpty()){
            return false
        }
        _userInfoState.value = (UserInfo("Test Boy", playerId))
        return true
    }

    private val _userInfoState: MutableStateFlow<UserInfo> = MutableStateFlow(UserInfo.NO_USER_IDENTIFIED_YET)
    val userStateInfo: StateFlow<UserInfo> = _userInfoState.asStateFlow()

    private val _recentMatchViewState: MutableStateFlow<MatchViewState> = MutableStateFlow(ComposeState.Loading())
    val recentMatchViewState: StateFlow<MatchViewState> = _recentMatchViewState.asStateFlow()

    init {
        viewModelScope.launch {
            userInfoUseCase().collect {
                _userInfoState.emit(it)
            }
            recentMatchUseCase().collect{
                val state = ComposeState.Ready(payload = it)
                _recentMatchViewState.value = state
            }
        }
    }

}