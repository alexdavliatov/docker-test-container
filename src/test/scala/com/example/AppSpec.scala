package com.example

import java.sql.DriverManager

import com.dimafeng.testcontainers._
import net.manub.embeddedkafka.{EmbeddedKafka, EmbeddedKafkaConfig}
import org.scalatest.FlatSpec

class AppSpec extends FlatSpec with ForAllTestContainer with EmbeddedKafka {

  val postgres = PostgreSQLContainer()
  val kafka = KafkaContainer()

  override val container: Container = MultipleContainers(postgres, kafka)

  override def afterStart(): Unit = {
    super.afterStart()
  }

  "DockerComposeContainer" should "retrieve non-0 port for any of services" in {
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

    implicit val kafkaConfig: EmbeddedKafkaConfig = EmbeddedKafkaConfig(
      kafkaPort = 9093,
      zooKeeperPort = 2181
    )

    withRunningKafka {
      publishStringMessageToKafka("topic", "message")
      assert(consumeFirstStringMessageFrom("topic") == "message")
    }

  }
}
