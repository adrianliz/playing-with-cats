package org.adrianliz.playingwithcats.breeds.infrastructure.thecatapi

import com.hazelcast.core.HazelcastInstance
import com.hazelcast.map.IMap
import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BreedsClientCache {
    companion object {
        const val BREEDS_CACHE = "breeds"
        const val CACHE_TTL_SECONDS = 60 * 60 * 24 // 24 hours
    }

    @Bean
    fun breedsCache(hazelcastInstance: HazelcastInstance): IMap<String, Breed> {
        hazelcastInstance.config.getMapConfig(BREEDS_CACHE).setTimeToLiveSeconds(CACHE_TTL_SECONDS)
        return hazelcastInstance.getMap(BREEDS_CACHE)
    }
}
