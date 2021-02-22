package app.controllers

import app.BadRequestException
import app.getACountOfItems
import app.isDbInitialisationMode
import app.models.DoYouKnowItem
import app.models.Question
import app.randomItem
import app.repositeries.DoYouKnowItemsRepositery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.PostConstruct

@Component
@RequestMapping("/")
class DoYouKnowController {

    @Autowired
    private val doYouKnowItemsRepositery: DoYouKnowItemsRepositery? = null

    @PostConstruct
    private fun createTestData() {
        if (isDbInitialisationMode()) {
            //From http://www.securite-routiere.gouv.fr/conseils-pour-une-route-plus-sure/le-saviez-vous/le-saviez-vous
            doYouKnowItemsRepositery!!.save(DoYouKnowItem(
                    0, /*automatic*/
                    "Conduire vite fatigue",
                    "Ce ne sont pas les vitesses régulières et modérées qui provoquent l’endormissement \"par ennui\" comme on le dit souvent, mais la conduite rapide.\n" +
                            "\n" +
                            "Une vitesse plus élevée oblige en effet le conducteur à traiter un grand nombre d’informations en un minimum de temps et à adapter en permanence sa vision. Conséquence : un stress important qui entraîne une plus grande fatigue et naturellement une perte de vigilance."
            ))

            doYouKnowItemsRepositery.save(DoYouKnowItem(
                    0, /*automatic*/
                    "En voiture, lors d’un choc à 50km/h, un enfant de 20kg, non attaché, peut se transformer en projectile d’une demi-tonne.",
                    "Jusqu’à ce qu’ils aient 10 ans, en voiture, les enfants doivent être installés dans un siège homologué UE, adapté à leur âge, leur morphologie, leur poids, et à l’arrière du véhicule*.\n" +
                            "\n" +
                            "De 0 à 10 kg, le siège de l’enfant doit être placé le dos à la route. Attention : si vous l’installez sur le siège passager dos à la route, désactivez l’airbag.\n" +
                            "\n" +
                            " \n" +
                            "*Il est interdit de transporter un enfant de moins de 10 ans aux places avant de tous les véhicules, sauf s’il est installé dos à la route dans un siège prévu à cet usage.\n" +
                            "\n"
            ))

            doYouKnowItemsRepositery.save(DoYouKnowItem(
                    0, /*automatic*/
                    "Les hommes et les jeunes sont les premières victimes de l’alcool au volant.",
                    "Près d’une personne tuée sur la route sur trois l’est dans un accident avec un taux d’alcool positif. 92% des conducteurs impliqués dans les accidents mortels avec un taux d’alcool dans le sang supérieur au taux légal sont des hommes.\n" +
                            "\n" +
                            "Les jeunes de 18-24 ans constituent la classe d’âge la plus gravement touchée : ils représentent 40% des personnes tuées dans un accident impliquant l’alcool. En moyenne, chaque jour, un jeune est tué dans un accident avec un taux d’alcool positif."
            ))
        }
    }

    @GetMapping(path = ["/getRandomDoYouKnow"])
    @ResponseBody
    fun getRandomDoYouKnow(): DoYouKnowItem {
        return getRandomDoYouKnow(1).first()
    }

    @GetMapping(path = ["/getRandomDoYouKnow/{count}"])
    @ResponseBody
    fun getRandomDoYouKnow(@PathVariable("count") count: Int): Iterable<DoYouKnowItem> {
        if (count < 1) throw BadRequestException("Bad count")
        val allQuestions: Iterable<DoYouKnowItem> = doYouKnowItemsRepositery!!.findAll()
        if (count > allQuestions.count()) throw BadRequestException("Bad count : Pas assez d'éléments à afficher")
        return getACountOfItems(allQuestions,count)
    }
}