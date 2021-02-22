package app.controllers

import app.*
import app.models.ArrestRoadStat
import app.repositeries.ArrestRoadRepositery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.PostConstruct

@Component
@RequestMapping("/")
class ArrestRoadController {

    @Autowired
    private val arrestRoadRepositery: ArrestRoadRepositery? = null

    @PostConstruct
    private fun createTestData() {
        if (isDbInitialisationMode()) {
            arrestRoadRepositery!!.save(ArrestRoadStat(
                    0, /*automatic*/
                    1,
                    1,
                    54,
                    2017
            ))

            arrestRoadRepositery.save(ArrestRoadStat(
                    0, /*automatic*/
                    2,
                    1,
                    54,
                    2016
            ))

            arrestRoadRepositery.save(ArrestRoadStat(
                    0, /*automatic*/
                    1,
                    1,
                    24,
                    2017
            ))

            arrestRoadRepositery.save(ArrestRoadStat(
                    0, /*automatic*/
                    5,
                    2,
                    54,
                    2017
            ))
        }
    }

    @GetMapping(path = ["/arrestOnRoad/byDepartement/{departement}"])
    @ResponseBody
    fun arrestOnRoadByDepartement(@PathVariable("departement") departement: Int): Int {
        if (!isValidDepartement(departement)) throw BadRequestException("Bad departement")
        val items = arrestRoadRepositery!!.findByDepartement(departement)
        return items.sumBy { it.count }
    }

    @GetMapping(path = ["/arrestOnRoad/byRegion/{region}"])
    @ResponseBody
    fun arrestOnRoadByRegion(@PathVariable("region") region: Int): Int {
        if (!isValidRegion(region)) throw BadRequestException("Bad region")
        val items = arrestRoadRepositery!!.findByRegion(region)
        return items.sumBy { it.count }
    }

    @GetMapping(path = ["/arrestOnRoad/byYear/{year}"])
    @ResponseBody
    fun arrestOnRoadByYear(@PathVariable("year") year: Int): Int {
        if (!isValidYear(year)) throw BadRequestException("Bad year")
        val items = arrestRoadRepositery!!.findByYear(year)
        return items.sumBy { it.count }
    }

    @GetMapping(path = ["/arrestOnRoad/byDepartement/{departement}/byYear/{year}"])
    @ResponseBody
    fun arrestOnRoadByDepartementAndYear(@PathVariable("departement") departement: Int, @PathVariable("year") year: Int): Int {
        if (!isValidDepartement(departement)) throw BadRequestException("Bad departement")
        if (!isValidYear(year)) throw BadRequestException("Bad year")
        val items = arrestRoadRepositery!!.findByYear(year)
        return items
                .firstOrNull { it.year == year }
                ?.count
                ?: 0
    }

    @GetMapping(path = ["/arrestOnRoad/byRegion/{region}/byYear/{year}"])
    @ResponseBody
    fun arrestOnRoadByRegionAndYear(@PathVariable("region") region: Int, @PathVariable("year") year: Int): Int {
        if (!isValidRegion(region)) throw BadRequestException("Bad region")
        if (!isValidYear(year)) throw BadRequestException("Bad year")
        val items = arrestRoadRepositery!!.findByDepartement(region)
        return items
                .firstOrNull { it.year == year }
                ?.count
                ?: 0
    }
}

