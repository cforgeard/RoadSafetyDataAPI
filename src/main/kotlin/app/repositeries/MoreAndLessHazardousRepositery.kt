package app.repositeries

import app.models.MoreAndLessHazardousItem
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MoreAndLessHazardousRepositery : CrudRepository<MoreAndLessHazardousItem, Long> {

}