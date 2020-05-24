import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.h3
import styled.css
import styled.styledButton
import styled.styledDiv

external interface VideoPlayerProps : RProps {
    var video: Video
    var onWatchedButtonPressed: (Video) -> Unit
    var unwatchedVideo: Boolean
}

val VideoPlayer = functionalComponent<VideoPlayerProps> { props ->
    styledDiv {
        css {
            position = Position.absolute
            top = 10.px
            right = 10.px
        }
        h3 {
            +"${props.video.speaker}: ${props.video.title}"
        }
        styledButton {
            css {
                display = Display.block
                backgroundColor = if (props.unwatchedVideo) Color.lightGreen else Color.red
            }
            attrs {
                onClickFunction = {
                    props.onWatchedButtonPressed(props.video)
                }
            }
            if (props.unwatchedVideo) {
                +"Mark as wathced"
            } else {
                +"Mark as unwatched"
            }
        }
        styledDiv {
            css {
                display = Display.flex
                marginBottom = 10.px
            }
            EmailShareButton {
                attrs.url = props.video.videoUrl
                EmailIcon {
                    attrs {
                        size = 32
                        round = true
                    }
                }
            }
            TelegramShareButton {
                attrs.url = props.video.videoUrl
                TelegramIcon {
                    attrs {
                        size = 32
                        round = true
                    }
                }
            }
        }
        ReactPlayer {
            attrs.url = props.video.videoUrl
        }
    }
}
