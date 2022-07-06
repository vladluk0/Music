package com.example.music.ui.screen.add_artist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music.data.model.artist.Artist
import com.example.music.data.repository.artists.ArtistsRepositoryImpl
import com.example.music.domain.observable.ObserveArtists
import com.example.music.ui.theme.MusicResult
import com.example.music.util.ObserveLoadingCounter
import com.example.music.util.UiMessage
import com.example.music.util.UiMessageManager
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class AddArtistViewState(
    val artists: List<Artist> = listOf(),
    val selectedArtists: List<Artist> = listOf(),
    val errorMessage: UiMessage? = null,
    val isRefreshing: Boolean = false,
    val snackBarText: UiMessage? = null
)

@OptIn(FlowPreview::class)
class AddArtistVM @Inject constructor(
    val observeArtists: ObserveArtists,
    repositoryImpl: ArtistsRepositoryImpl
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    private val observeLoadingCounter = ObserveLoadingCounter()
    private val uiMessageManager = UiMessageManager()

    val state: StateFlow<AddArtistViewState> = combine(
        observeArtists.flow,
        uiMessageManager.message
    ) { artists, message ->
        Log.d("zxc", "state")
        AddArtistViewState(
            artists = artists,
            snackBarText = message
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = AddArtistViewState(),
        started = SharingStarted.WhileSubscribed()
    )

    init {
        viewModelScope.launch {
            searchQuery.debounce(300)
                .onEach { searchQuery ->
                    repositoryImpl.searchArtists(artistsId, searchQuery).collect { stateArtists ->
                        when (stateArtists) {
                            is MusicResult.Success.Value -> {
                                Log.d("zxc", "success")
                                observeArtists(ObserveArtists.Params(artistsId, searchQuery))
                            }
                            else -> {}
                        }
                    }
                }
                .catch { throwable ->
                    Log.d("zxc", "catch")
                    uiMessageManager.emitMessage(UiMessage(throwable))
                    //Log.d("zxc", uiMessageManager.message)
                }
                .collect()
        }
    }

    fun search(query: String) {
        searchQuery.value = query
        Log.d("zxc", query)
    }

    fun snackBarShown(id: Long) {
        Log.d("zxc", "snackBarShown")
        viewModelScope.launch {
            uiMessageManager.clearMessage(id)
        }
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