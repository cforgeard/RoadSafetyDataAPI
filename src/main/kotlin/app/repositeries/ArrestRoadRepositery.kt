package app.repositeries

import app.models.ArrestRoadStat
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ArrestRoadRepositery : CrudRepository<ArrestRoadStat, Long> {
    fun findByDepartement(departement: Int): Iterable<ArrestRoadStat>
    fun findByRegion(region: Int): Iterable<ArrestRoadStat>
    fun findByYear(year: Int): Iterable<ArrestRoadStat>
}