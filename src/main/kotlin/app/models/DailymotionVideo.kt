package app.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Id

@JsonIgnoreProperties(ignoreUnknown = true)
data class DailymotionVideo(
        @Id var id: String = "",
        var title: String = "",
        var channel: String = "",
        var owner: String = "")