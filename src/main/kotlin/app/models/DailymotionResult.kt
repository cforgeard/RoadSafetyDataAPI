package app.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Id
import javax.persistence.OneToMany

@JsonIgnoreProperties(ignoreUnknown = true)
data class DailymotionResult(
        @Id var id: Long = 0,
        var total: Int = 0,
        @OneToMany() var list: Set<DailymotionVideo>? = null)