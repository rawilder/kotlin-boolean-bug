package dev.awilder

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize

@MicronautTest
class KotlinBooleanBugTest(
    private val application: EmbeddedApplication<*>,
    private val testEntityRepository: TestEntityRepository
): StringSpec({

    "test the server is running" {
        assert(application.isRunning)
    }

    "hibernate is querying correctly" {
        testEntityRepository.save(
            TestEntity(
                name = "test"
            )
        )
        testEntityRepository.findById(1).orElseGet(null)?.also {
            it.name = "test2"
            testEntityRepository.update(it)
        }
        testEntityRepository.findAll().toList() shouldHaveSize 1
    }
})
