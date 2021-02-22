package app.repositeries

import app.models.Question
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionsRepositery : CrudRepository<Question, Long> {
}