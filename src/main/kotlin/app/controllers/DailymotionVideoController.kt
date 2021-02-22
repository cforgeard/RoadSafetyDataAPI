package app.controllers

import app.models.DailymotionResult
import app.models.DailymotionVideo
import app.randomItem
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.client.RestTemplate

const val DAILYMOTION_API_BASE_URL = "https://api.dailymotion.com/user/securite_routiere/videos?search=%s&limit=100"
const val DAILYMOTION_API_SEARCH_TERM_RANDOM_CHILDREN_VIDEO = "Elliot"
const val DAILYMOTION_API_SEARCH_TERM_RANDOM_MOTO_EXPERIENCE_VIDEO = "raconte"

@Component
@RequestMapping("/")
class DailymotionVideoController {

    @GetMapping(path = ["/getRandomChildrenVideo"])
    @ResponseBody
    fun getRandomChildrenVideo(): DailymotionVideo {
        return getRandomVideo(DAILYMOTION_API_SEARCH_TERM_RANDOM_CHILDREN_VIDEO)
    }

    @GetMapping(path = ["/getRandomMotoExperienceVideo"])
    @ResponseBody
    fun getRandomMotoExperienceVideo() : DailymotionVideo{
        return getRandomVideo(DAILYMOTION_API_SEARCH_TERM_RANDOM_MOTO_EXPERIENCE_VIDEO)
    }

    @GetMapping(path = ["/getAllChildrenVideo"])
    @ResponseBody
    fun getAllChildrenVideo(): Iterable<DailymotionVideo> {
        return getAllVideos(DAILYMOTION_API_SEARCH_TERM_RANDOM_CHILDREN_VIDEO)
    }

    @GetMapping(path = ["/getAllMotoExperienceVideo"])
    @ResponseBody
    fun getAllMotoExperienceVideo(): Iterable<DailymotionVideo> {
        return getAllVideos(DAILYMOTION_API_SEARCH_TERM_RANDOM_MOTO_EXPERIENCE_VIDEO)
    }

    private fun getAllVideos(searchTerm: String): Iterable<DailymotionVideo>{
        val restTemplate = RestTemplate()
        val allVideos = restTemplate.getForObject(String.format(DAILYMOTION_API_BASE_URL, searchTerm), DailymotionResult::class.java)
        return allVideos.list!!
    }

    private fun getRandomVideo(searchTerm: String): DailymotionVideo {
        val restTemplate = RestTemplate()
        val allVideos = restTemplate.getForObject(String.format(DAILYMOTION_API_BASE_URL, searchTerm), DailymotionResult::class.java)
        return randomItem(allVideos.list!!)
    }
}