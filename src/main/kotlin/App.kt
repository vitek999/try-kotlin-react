import kotlinext.js.jsObject
import react.*
import react.dom.div
import react.dom.h1
import react.dom.h3

external interface AppProps : RProps {
    var unwatchedVideos: List<Video>
}

val AppFunctional = functionalComponent<AppProps> {props ->
    val (currentVideo , setCurrentVideo) = useState<Video?>(null)
    val (unwatchedVideos, setUnwatchedVideos) = useState<List<Video>>(props.unwatchedVideos)
    val (watchedVideos, setWatchedVideos) = useState<List<Video>>(emptyList())

    h1 {
        +"KotlinConf Explorer"
    }
    div {
        h3 {
            +"Videos to watch"
        }

        child(VideoList, props = jsObject {
            videos = unwatchedVideos
            selectedVideo = currentVideo
            onSelectVideo = { video -> setCurrentVideo(video) }
        })

        h3 {
            +"Videos watched"
        }

        child(VideoList, props = jsObject {
            videos = watchedVideos
            selectedVideo = currentVideo
            onSelectVideo = { video -> setCurrentVideo(video) }
        })
    }

    currentVideo?.let {
        child(VideoPlayer, props = jsObject {
            video = it
            unwatchedVideo = video in unwatchedVideos
            onWatchedButtonPressed = {
                if (video in unwatchedVideos) {
                    setUnwatchedVideos(unwatchedVideos - video)
                    setWatchedVideos(watchedVideos + video)
                } else {
                    setUnwatchedVideos(unwatchedVideos + video)
                    setWatchedVideos(watchedVideos - video)
                }
            }
        })
    }
}