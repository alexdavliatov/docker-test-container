package com.example

import java.sql.DriverManager

import com.dimafeng.testcontainers.{ForAllTestContainer, MultipleContainers, PostgreSQLContainer}
import org.scalatest.FlatSpec

class PostgresSpec extends FlatSpec with ForAllTestContainer {

  val postgres = PostgreSQLContainer()
  override val container = MultipleContainers(postgres)

  "PostgreSQL container" should "be started" in {
    Class.forName(postgres.driverClassName)
    val connection = DriverManager.getConnection(postgres.jdbcUrl, postgres.username, postgres.password)

    val preparedStatement = connection.prepareStatement(postgres.testQueryString)
    try {
      val resultSet = preparedStatement.executeQuery()
      resultSet.next()
      assert(1 == resultSet.getInt(1))
      resultSet.close()
    } finally {
      preparedStatement.close()
      connection.close()
    }
  }
}