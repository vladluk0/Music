package com.example.music.ui.screen.add_artist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music.data.model.artist.Artist
import com.example.music.data.repository.artists.ArtistsRepositoryImpl
import com.example.music.domain.observable.ObserveArtists
import com.example.music.util.ObserveLoadingCounter
import kotlinx.coroutines.flow.*
import javax.inject.Inject


data class AddArtistViewState(
    val artists: List<Artist> = listOf(),
    val selectedArtists: List<Artist> = listOf(),
    val errorMessage: String? = null,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val snackBarText: String? = null
)

class AddArtistVM @Inject constructor(
    val observeArtists: ObserveArtists,
    repositoryImpl: ArtistsRepositoryImpl
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    private val observeLoadingCounter = ObserveLoadingCounter()

    val state: StateFlow<AddArtistViewState> = combine(
        observeArtists.flow
    ) { artists ->
        Log.d("zxc", "view state $artists")
        AddArtistViewState(
            artists = artists[0]
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = AddArtistViewState(),
        started = SharingStarted.WhileSubscribed()
    )

    fun refresh() {

    }

    init {
        Log.d("zxc", "init")
        observeArtists(ObserveArtists.Params(artistsId))
    }
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
        /*viewModelScope.launch {
            artistsRepository.getArtists(artistsId).collect { artists ->
                when (artists) {
                    is Result.Loading -> _state.update {
                        it.copy(
                            isRefreshing = true
                        )
                    }
                    is Result.Success.Value -> _state.update {
                        it.copy(
                            artists = artists.data!!.artists,
                            isRefreshing = false
                        )
                    }
                    is Result.Success.Empty -> {}
                    is Result.Error -> {}
                }
            }
        }*/

    fun search(query: String) {
        searchQuery.value = query
    }

    fun snackBarShown() {
        /*_state.update {
            it.copy(snackBarText = null)
        }*/
    }

    fun artistsSelected(artist: Artist) {
        /*_state.update {
            it.copy(
                snackBarText = artist.name
            )
        }*/
    }

    companion object {
        val ERROR_400 = 400
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