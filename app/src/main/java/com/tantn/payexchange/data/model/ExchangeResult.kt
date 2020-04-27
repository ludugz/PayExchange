package com.tantn.payexchange.data.model

data class ExchangeResult(
    var privacy: String,
    var quotes: Quotes,
    var source: String,
    var success: Boolean,
    var terms: String,
    var timestamp: Int
)

