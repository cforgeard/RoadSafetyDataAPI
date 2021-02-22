package app.controllers

import app.BadRequestException
import app.getACountOfItems
import app.isDbInitialisationMode
import app.models.MoreAndLessHazardousItem
import app.models.Question
import app.randomItem
import app.repositeries.MoreAndLessHazardousRepositery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.PostConstruct

@Component
@RequestMapping("/")
class MoreAndLessHazardousController {

    @Autowired
    private val moreAndLessHazardousRepositery: MoreAndLessHazardousRepositery? = null

    @PostConstruct
    private fun createTestData() {
        if (isDbInitialisationMode()) {
            moreAndLessHazardousRepositery!!.save(MoreAndLessHazardousItem(
                    0, /*automatic*/
                    "10cl vin 12.5%",
                    "4cl whisky 40%"
            ))

            moreAndLessHazardousRepositery.save(MoreAndLessHazardousItem(
                    0, /*automatic*/
                    "10cl vin 12.5%",
                    "9cl apéritif 16%"
            ))

            moreAndLessHazardousRepositery.save(MoreAndLessHazardousItem(
                    0, /*automatic*/
                    "24cl biere 5%",
                    "15cl vin 12.5%"
            ))
        }
    }

    @GetMapping(path = ["/getRandomMoreAndLessHazardous"])
    @ResponseBody
    fun getRandomMoreAndLessHazardous(): MoreAndLessHazardousItem {
        return getRandomMoreAndLessHazardous(1).first()
    }

    @GetMapping(path = ["/getRandomMoreAndLessHazardous/{count}"])
    @ResponseBody
    fun getRandomMoreAndLessHazardous(@PathVariable("count") questionsCount: Int): Iterable<MoreAndLessHazardousItem> {
        if (questionsCount < 1) throw BadRequestException("Bad count")
        val allQuestions: Iterable<MoreAndLessHazardousItem> = moreAndLessHazardousRepositery!!.findAll()
        if (questionsCount > allQuestions.count()) throw BadRequestException("Bad count : Pas assez d'éléments à afficher")
        return getACountOfItems(allQuestions,questionsCount)
    }
}