package io.github.cloud.demo.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ShopOrder constructor(val orderId: String, val orderType: String, val orderReference: String)