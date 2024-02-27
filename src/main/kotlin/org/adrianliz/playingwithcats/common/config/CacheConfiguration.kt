package org.adrianliz.playingwithcats.common.config

import com.hazelcast.config.Config
import com.hazelcast.config.UserCodeDeploymentConfig.ClassCacheMode.ETERNAL
import com.hazelcast.config.UserCodeDeploymentConfig.ProviderMode.LOCAL_CLASSES_ONLY
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.spring.cache.HazelcastCacheManager
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class CacheConfiguration {
    @Bean
    fun hazelcastConfig(): Config {
        val config = Config()
        config.setClassLoader(Thread.currentThread().contextClassLoader)
        config.setClusterName("playing-with-cats")
        config.setProperty("hazelcast.phone.home.enabled", "false")
        config.setProperty("hazelcast.discovery.enabled", "true")

        val codeDeploymentConfig = config.userCodeDeploymentConfig
        codeDeploymentConfig
            .setEnabled(true)
            .setClassCacheMode(ETERNAL)
            .setProviderMode(LOCAL_CLASSES_ONLY)

        return config
    }

    @Bean
    @Primary
    fun hazelcastMainInstance(hazelcastConfig: Config): HazelcastInstance {
        return Hazelcast.newHazelcastInstance(hazelcastConfig)
    }

    @Bean
    fun cacheManager(hazelcastMainInstance: HazelcastInstance): CacheManager {
        return HazelcastCacheManager(hazelcastMainInstance)
    }
}
