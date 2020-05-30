import kotlinx.serialization.*

@Serializable
data class Video(val id: Int, val title: String, val speaker: String, val videoUrl: String)
