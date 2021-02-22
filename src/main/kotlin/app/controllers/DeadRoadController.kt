package app.controllers

import app.*
import app.models.DeadRoadStat
import app.repositeries.DeadRoadRepositery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.PostConstruct

@Component
@RequestMapping("/")
class DeadRoadController {

    @Autowired
    private val deadRoadRepositery: DeadRoadRepositery? = null

    @PostConstruct
    private fun createTestData() {
        if (isDbInitialisationMode()) {
            deadRoadRepositery!!.save(DeadRoadStat(
                    0, /*automatic*/
                    1,
                    1,
                    54,
                    2017
            ))

            deadRoadRepositery.save(DeadRoadStat(
                    0, /*automatic*/
                    2,
                    1,
                    54,
                    2016
            ))

            deadRoadRepositery.save(DeadRoadStat(
                    0, /*automatic*/
                    1,
                    1,
                    24,
                    2017
            ))

            deadRoadRepositery.save(DeadRoadStat(
                    0, /*automatic*/
                    5,
                    2,
                    54,
                    2017
            ))
        }
    }

    @GetMapping(path = ["/deadOnRoad/byDepartement/{departement}"])
    @ResponseBody
    fun deadOnRoadByDepartement(@PathVariable("departement") departement: Int): Int {
        if (!isValidDepartement(departement)) throw BadRequestException("Bad departement")
        val items = deadRoadRepositery!!.findByDepartement(departement)
        return items.sumBy { it.count }
    }

    @GetMapping(path = ["/deadOnRoad/byRegion/{region}"])
    @ResponseBody
    fun deadOnRoadByRegion(@PathVariable("region") region: Int): Int {
        if (!isValidRegion(region)) throw BadRequestException("Bad region")
        val items = deadRoadRepositery!!.findByRegion(region)
        return items.sumBy { it.count }
    }

    @GetMapping(path = ["/deadOnRoad/byYear/{year}"])
    @ResponseBody
    fun deadOnRoadByYear(@PathVariable("year") year: Int): Int {
        if (!isValidYear(year)) throw BadRequestException("Bad year")
        val items = deadRoadRepositery!!.findByYear(year)
        return items.sumBy { it.count }
    }

    @GetMapping(path = ["/deadOnRoad/byDepartement/{departement}/byYear/{year}"])
    @ResponseBody
    fun deadOnRoadByDepartementAndYear(@PathVariable("departement") departement: Int, @PathVariable("year") year: Int): Int {
        if (!isValidDepartement(departement)) throw BadRequestException("Bad departement")
        if (!isValidYear(year)) throw BadRequestException("Bad year")
        val items = deadRoadRepositery!!.findByYear(year)
        return items
                .firstOrNull { it.year == year }
                ?.count
                ?: 0
    }

    @GetMapping(path = ["/deadOnRoad/byRegion/{region}/byYear/{year}"])
    @ResponseBody
    fun deadOnRoadByRegionAndYear(@PathVariable("region") region: Int, @PathVariable("year") year: Int): Int {
        if (!isValidRegion(region)) throw BadRequestException("Bad region")
        if (!isValidYear(year)) throw BadRequestException("Bad year")
        val items = deadRoadRepositery!!.findByDepartement(region)
        return items
                .firstOrNull { it.year == year }
                ?.count
                ?: 0
    }
}