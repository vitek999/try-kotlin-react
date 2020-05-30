import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.*
import kotlin.browser.window

class Api (private val httpClient: HttpClient) {

    suspend fun fetchVideo(id: Int) : Video =
        httpClient.get<Video>("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")

    suspend fun fetchVideos(): List<Video> = coroutineScope {
        (1..25).map {id ->
            async {
                fetchVideo(id)
            }
        }.awaitAll()
    }
}