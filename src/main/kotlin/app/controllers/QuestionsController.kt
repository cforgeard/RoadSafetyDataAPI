package app.controllers

import app.BadRequestException
import app.getACountOfItems
import app.isDbInitialisationMode
import app.models.Question
import app.repositeries.QuestionsRepositery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*
import javax.annotation.PostConstruct

@Component
@RequestMapping("/")
class QuestionsController {

    @Autowired
    private val questionsRepositery: QuestionsRepositery? = null

    @PostConstruct
    private fun createTestData() {
        if (isDbInitialisationMode()) {
            questionsRepositery!!.save(Question(
                    0, /*automatic*/
                    "Quel est la limite de vitesse en campagne ?",
                    "90km/h",
                    "70km/h",
                    "110km/h"
            ))

            questionsRepositery.save(Question(
                    0, /*automatic*/
                    "Quel est le taux d'alcool minimum répréhensible (non probatoire) ?",
                    "50mg/L",
                    "40mg/L",
                    "55mg/L"
            ))

            questionsRepositery.save(Question(
                    0, /*automatic*/
                    "A partir de quand un stationnement est vu comme abusif ?",
                    "7 jours",
                    "4 jours",
                    "6 jours"
            ))
        }
    }

    @GetMapping(path = ["/getRandomQuestion"])
    @ResponseBody
    fun getRandomQuestions(): Question {
        return getRandomQuestions(1).first()
    }

    @GetMapping(path = ["/getRandomQuestion/{questionsCount}"])
    @ResponseBody
    fun getRandomQuestions(@PathVariable("questionsCount") questionsCount: Int): Iterable<Question> {
        if (questionsCount < 1) throw BadRequestException("Bad questionsCount")
        val allQuestions: Iterable<Question> = questionsRepositery!!.findAll()
        if (questionsCount > allQuestions.count()) throw BadRequestException("Bad questionsCount : Pas assez de questions à afficher")
        return getACountOfItems(allQuestions,questionsCount)
    }
}