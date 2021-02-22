package app.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
data class MoreAndLessHazardousItem(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
        var lessItem: String = "",
        var moreItem: String = "")