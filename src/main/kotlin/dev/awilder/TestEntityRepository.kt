package dev.awilder

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface TestEntityRepository : CrudRepository<TestEntity, Long>
