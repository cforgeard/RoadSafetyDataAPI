package app.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
data class DoYouKnowItem(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
        var title: String = "",
        @Column(length = 10000) var content: String = "")