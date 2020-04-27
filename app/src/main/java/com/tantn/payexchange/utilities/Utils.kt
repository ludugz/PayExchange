package com.tantn.payexchange.utilities

import android.view.View
import com.tantn.payexchange.data.model.Quotes

class Utils {

    companion object {
        const val BASE_URL = "http://apilayer.net/"
        const val ACCESS_KEY = "d0f871fb1e8988631eed037d10439880"
        const val CURRENCIES_PARAM =
            "AED,AFN,ALL,AMD,ANG,AOA,ARS,AUD,AWG,AZN,BAM,BBD,BDT,BGN,BHD," +
                    "BIF,BMD,BND,BOB,BRL,BSD,BTC,BTN,BWP,BYN,BYR,BZD,CAD,CDF,CHF" // First 30 item

        fun getRateList(quotes: Quotes): ArrayList<Double> {
            return ArrayList<Double>().apply {
                this.add(quotes.uSDAED)
                this.add(quotes.uSDAFN)
                this.add(quotes.uSDALL)
                this.add(quotes.uSDAMD)
                this.add(quotes.uSDANG)
                this.add(quotes.uSDAOA)
                this.add(quotes.uSDARS)
                this.add(quotes.uSDAUD)
                this.add(quotes.uSDAWG)
                this.add(quotes.uSDAZN)
                this.add(quotes.uSDBAM)
                this.add(quotes.uSDBBD)
                this.add(quotes.uSDBDT)
                this.add(quotes.uSDBGN)
                this.add(quotes.uSDBHD)
                this.add(quotes.uSDBIF)
                this.add(quotes.uSDBMD)
                this.add(quotes.uSDBND)
                this.add(quotes.uSDBOB)
                this.add(quotes.uSDBRL)
                this.add(quotes.uSDBSD)
                this.add(quotes.uSDBTC)
                this.add(quotes.uSDBTN)
                this.add(quotes.uSDBWP)
                this.add(quotes.uSDBYN)
                this.add(quotes.uSDBYR)
                this.add(quotes.uSDBZD)
                this.add(quotes.uSDCAD)
                this.add(quotes.uSDCDF)
                this.add(quotes.uSDCHF)
            }
        }
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}
