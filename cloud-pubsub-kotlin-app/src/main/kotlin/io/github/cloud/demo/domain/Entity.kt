package io.github.cloud.demo.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Entity constructor(val shopOrder: ShopOrder)