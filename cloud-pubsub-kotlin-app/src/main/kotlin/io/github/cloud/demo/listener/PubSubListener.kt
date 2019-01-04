package io.github.cloud.demo.listener

import com.google.cloud.ServiceOptions
import com.google.cloud.pubsub.v1.Subscriber
import com.google.pubsub.v1.ProjectSubscriptionName
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
open class PubSubListener(private val messageReceiver: MessageReceiverImp, @Value("\${subscriptionId}") val subscriptionId: String) {


    companion object {
        val LOG = LoggerFactory.getLogger(PubSubListener::class.java)
    }

    private val defaultProjectId: String = ServiceOptions.getDefaultProjectId()
    var subscriptionName = ProjectSubscriptionName.of(defaultProjectId, subscriptionId)!!

    fun listen() {
        val subscriber = Subscriber.newBuilder(subscriptionName, messageReceiver).build()
        subscriber.startAsync()
        messageReceiver.messages.take()
    }
}