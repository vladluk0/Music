package com.example.music.ui.screen.add_artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music.data.model.artist.Artist
import com.example.music.domain.observable.ObserveArtists
import kotlinx.coroutines.flow.*
import javax.inject.Inject


data class AddArtistViewState(
    val artists: List<Artist> = listOf(),
    val selectedArtists: List<Artist> = listOf(),
    val showErrorMessage: String? = null,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
)

class AddArtistVM @Inject constructor(
    private val observeArtists: ObserveArtists
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    val state: StateFlow<AddArtistViewState> = combine(
        observeArtists.flow
    ) { artists ->
        AddArtistViewState(
            artists = artists[0]
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AddArtistViewState()
    )

    init {
        observeArtists(ObserveArtists.Params(artistsId))
        /*viewModelScope.launch {
            searchQuery.debounce(300)
                .onEach { searchQuery ->
                    artistsRepository.findArtists(artistsId, searchQuery).collect { artists ->
                        _state.update {
                            it.copy(
                                artists = artists,
                                isRefreshing = false
                            )
                        }
                    }
                }
                .collect()
        }*/
    }

    fun search(query: String) {
        searchQuery.value = query
    }
}

val artistsId = "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6," +
        "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6," +
        "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6," +
        "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6," +
        "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6," +
        "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6," +
        "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6," +
        "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6," +
        "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6," +
        "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6," +
        "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6"