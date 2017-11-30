package com.pingpong

import net.corda.core.utilities.getOrThrow
import net.corda.node.services.transactions.SimpleNotaryService
import net.corda.nodeapi.User
import net.corda.nodeapi.internal.ServiceInfo
import net.corda.testing.DUMMY_BANK_A
import net.corda.testing.DUMMY_BANK_B
import net.corda.testing.DUMMY_BANK_C
import net.corda.testing.DUMMY_NOTARY
import net.corda.testing.driver.driver
import org.junit.Test
import kotlin.test.assertFailsWith

class DriverBasedTest {
    @Test
    fun `run driver test`() {
        val user = User("user1", "test", permissions = setOf("StartFlow.com.pingpong.Ping"))

        driver(startNodesInProcess = true) {
            val (_, nodeAHandle, _) = listOf(
                    startNode(providedName = DUMMY_NOTARY.name, advertisedServices = setOf(ServiceInfo(SimpleNotaryService.type))),
                    startNode(providedName = DUMMY_BANK_A.name, rpcUsers = listOf(user)),
                    startNode(providedName = DUMMY_BANK_B.name)
            ).map { it.getOrThrow() }

            val nodeARpcAddress = nodeAHandle.configuration.rpcAddress.toString()
            val nodeARpcClient = RpcClient(nodeARpcAddress)

            // We can ping the notary and Bank B...
            nodeARpcClient.ping(DUMMY_NOTARY.name.toString())
            nodeARpcClient.ping(DUMMY_BANK_B.name.toString())
            // ...but not Bank C, who isn't on the network
            assertFailsWith<IllegalArgumentException> {
                nodeARpcClient.ping(DUMMY_BANK_C.name.toString())
            }
        }
    }
}