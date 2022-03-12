package dev.awilder

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

@MicronautTest
class KotlinBooleanBugTest(
    private val application: EmbeddedApplication<*>,
    private val testEntityRepository: TestEntityRepository
): StringSpec({

    "test the server is running" {
        assert(application.isRunning)
    }

    "hibernate with repository method is querying correctly" {
        testEntityRepository.save(
            TestEntity(
                name = "test"
            )
        )
        testEntityRepository.findById(1).orElse(null) should {
            it.shouldNotBeNull()
            it.name shouldBe "test"
        }
        testEntityRepository.findAll().toList() shouldHaveSize 1
    }

    "hibernate with custom query without boolean is querying correctly" {
        testEntityRepository.save(
            TestEntity(
                name = "test"
            )
        )
        testEntityRepository.withoutBooleanTest("tes%").orElse(null) should {
            it.shouldNotBeNull()
            it.name shouldBe "test"
        }
    }

    "hibernate with custom query with boolean is querying correctly" {
        testEntityRepository.save(
            TestEntity(
                name = "test"
            )
        )
        testEntityRepository.withBooleanTest(false).orElse(null) should {
            it.shouldNotBeNull()
            it.name shouldBe "test"
        }
    }

    "hibernate is updating correctly" {
        testEntityRepository.save(
            TestEntity(
                name = "test"
            )
        )
        testEntityRepository.findById(4).orElse(null).also {
            it.shouldNotBeNull()
            it.name = "test2"
            it.isImportant = true
            testEntityRepository.update(it)
        }
    }
})
