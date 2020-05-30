import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import react.child
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main() {

    val httpClient = HttpClient() {
        install(JsonFeature) { serializer = KotlinxSerializer() }
    }

    val api = Api(httpClient)

    window.onload = {

        val mainScope = MainScope()
        mainScope.launch {
            val videos = api.fetchVideos()
            render(document.getElementById("root")) {
                child(AppFunctional, props = jsObject { unwatchedVideos = videos })
            }
        }

    }
}