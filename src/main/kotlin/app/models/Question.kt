package app.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
data class Question(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
        var question: String = "",
        var goodAnswer: String = "",
        var wrongAnswer1: String = "",
        var wrongAnswer2: String = "")