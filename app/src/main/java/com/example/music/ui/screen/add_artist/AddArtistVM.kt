package com.example.music.ui.screen.add_artist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music.data.model.artist.Artist
import com.example.music.data.model.artist.Artists
import com.example.music.data.repository.artists.ArtistsRepositoryImpl
import com.example.music.ui.theme.Result
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class AddArtistViewState(
    val artists: List<Artist> = listOf(),
    val selectedArtists: List<Artist> = listOf(),
    val errorMessage: String? = null,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val snackBarText: String? = null
)

@OptIn(FlowPreview::class)
class AddArtistVM @Inject constructor(
    private val artistsRepository: ArtistsRepositoryImpl
) : ViewModel() {

    private var _state = MutableStateFlow(AddArtistViewState())

    private val searchQuery = MutableStateFlow("")

    val state: StateFlow<AddArtistViewState>
        get() = _state.asStateFlow()

    init {
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
        viewModelScope.launch {
            when (val artists = artistsRepository.getArtists(artistsId)) {
                is Result.Loading -> _state.update {
                    it.copy(
                        isRefreshing = true
                    )
                }
                is Result.Success.Value -> _state.update {
                    searchQuery.debounce(300)
                        .onEach {

                        }
                    it.copy(
                        artists = artists.data!!.artists,
                        isRefreshing = false
                    )
                }
                is Result.Success.Empty -> {
                    Log.d("zxc", "empty")
                }
                is Result.Error -> {
                    when (artists.code) {
                        else -> {
                            Log.d("zxc", "code: ${artists.code}")
                        }
                    }
                }
            }
        }
    }

    fun search(query: String) {
        searchQuery.value = query
    }

    fun snackBarShown() {
        _state.update {
            it.copy(snackBarText = null)
        }
    }

    fun artistsSelected(artist: Artist) {
        _state.update {
            it.copy(
                snackBarText = artist.name
            )
        }
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