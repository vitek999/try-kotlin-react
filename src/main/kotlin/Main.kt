import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.child
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    window.onload = {

        val mainScope = MainScope()
        mainScope.launch {
            val videos = fetchVideos()
            render(document.getElementById("root")) {
                child(AppFunctional, props = jsObject { unwatchedVideos = videos })
            }
        }

    }
}