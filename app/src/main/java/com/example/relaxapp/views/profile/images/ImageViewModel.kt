import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.login.models.UserRepository
import com.example.relaxapp.views.profile.models.UserProfile
import com.example.relaxapp.views.profile.images.ImagesData
import kotlinx.coroutines.launch
class ImageViewModel(
    private val userRepository: UserRepository,
    private val context: Context
) : ViewModel() {

    var imagesState = mutableStateOf<List<ImagesData>>(emptyList())
    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)
    private val tokenManager = TokenManager(context)

    fun fetchImages() {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                isLoading.value = true
                val images = userRepository.getImages("Bearer $token")
                Log.d("fetchImages: ", "images: $images")
                imagesState.value = images
                isLoading.value = false
                errorMessage.value = null

            } catch (e: Exception) {
                errorMessage.value = "Error al cargar las imágenes: ${e.localizedMessage}"
                isLoading.value = false
            }
        }
    }
    fun updateUserProfile(imageUrl: String) {
        viewModelScope.launch {
            try {
                val userId= tokenManager.getUserId()
                val token = tokenManager.getToken()
                val userProfile = UserProfile(id = userId!!, photo = imageUrl)
                val response = userRepository.updateUser("Bearer $token", userProfile)

                if (response.isSuccessful) {
                    Log.d("ImageViewModel", "Foto de usuario actualizada correctamente.")
                } else {
                    Log.e("ImageViewModel", "Error al actualizar la foto del usuario.")
                }
            } catch (e: Exception) {
                Log.e("ImageViewModel", "Error: ${e.localizedMessage}")
            }
        }
    }
}
