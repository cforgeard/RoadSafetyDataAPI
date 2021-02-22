package app.repositeries

import app.models.DoYouKnowItem
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DoYouKnowItemsRepositery : CrudRepository<DoYouKnowItem, Long> {

}