package io.github.cloud.demo.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PubSubMessage constructor(val entity: Entity, val entityKey: String)