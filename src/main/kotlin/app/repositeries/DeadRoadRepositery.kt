package app.repositeries

import app.models.DeadRoadStat
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeadRoadRepositery : CrudRepository<DeadRoadStat, Long> {
    fun findByDepartement(departement: Int): Iterable<DeadRoadStat>
    fun findByRegion(region: Int): Iterable<DeadRoadStat>
    fun findByYear(year: Int): Iterable<DeadRoadStat>
}