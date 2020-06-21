package com.perime.perime_ma.providers.apollographql

import okhttp3.OkHttpClient
import com.apollographql.apollo.ApolloClient

class ApolloGraphql {
    companion object{
        private const val BASE_URL = "http://104.198.185.230/graphql"

        fun setUpApolloClient(): ApolloClient {

            val okHttp = OkHttpClient
                .Builder()
            return ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttp.build())
                .build()
        }
    }
}