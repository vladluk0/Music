package com.example.music.ui.screen.add_artist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music.data.model.artist.Artist
import com.example.music.data.repository.artists.ArtistsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject


data class AddArtistViewState(
    val artists: List<Artist> = listOf(),
    val selectedArtists: List<Artist> = listOf(),
    val showErrorMessage: String? = null,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
)

@OptIn(FlowPreview::class)
class AddArtistVM @Inject constructor(
    private val artistsRepository: ArtistsRepositoryImpl
) : ViewModel() {

    private var _state = MutableStateFlow(AddArtistViewState(isRefreshing = true))

    private val searchQuery = MutableStateFlow("")

    val state: StateFlow<AddArtistViewState>
        get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
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
        }
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