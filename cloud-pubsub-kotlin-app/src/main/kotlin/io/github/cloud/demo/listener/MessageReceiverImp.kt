package io.github.cloud.demo.listener

import com.google.cloud.pubsub.v1.AckReplyConsumer
import com.google.cloud.pubsub.v1.MessageReceiver
import com.google.pubsub.v1.PubsubMessage
import io.github.cloud.demo.domain.PubSubMessage
import io.github.cloud.demo.utils.fromJson
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.LinkedBlockingDeque

@Component
open class MessageReceiverImp : MessageReceiver {
    val messages = LinkedBlockingDeque<PubsubMessage>()

    companion object {
        val LOG = LoggerFactory.getLogger(MessageReceiverImp::class.java)
    }

    override fun receiveMessage(message: PubsubMessage, consumer: AckReplyConsumer) {
        try {
            val pubsubMessage = fromJson(message.data.toStringUtf8(), PubSubMessage::class.java)
            LOG.info("Message received with messageId=${message.messageId}, entityKey = ${pubsubMessage!!.entityKey} " +
                    "and orderReference = ${pubsubMessage.entity.shopOrder.orderReference}")
            consumer.ack()
        } catch (e: Exception) {
            LOG.info("Failed to deserialize")
            consumer.nack()
            throw RuntimeException("Failed to ack message with messageIs = ${message.messageId} ", e)
        }
    }
}