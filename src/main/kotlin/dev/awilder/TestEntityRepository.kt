package dev.awilder

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.Optional

@Repository
interface TestEntityRepository : CrudRepository<TestEntity, Long> {
    @Query("FROM TestEntity t WHERE t.name LIKE :name")
    fun withoutBooleanTest(name: String): Optional<TestEntity>
    @Query("FROM TestEntity t WHERE t.isImportant = :isImportant")
    fun withBooleanTest(isImportant: Boolean): Optional<TestEntity>
}
