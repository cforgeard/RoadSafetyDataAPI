package app.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
data class ArrestRoadStat(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
        var departement: Int = 0,
        var region: Int = 0,
        var count: Int = 0,
        var year: Int = 0)