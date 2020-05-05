package com.perime.perime_ma.providers.apollographql

import okhttp3.OkHttpClient
import com.apollographql.apollo.ApolloClient

class ApolloGraphql {
    companion object{
        private const val BASE_URL = "http://ec2-54-88-18-124.compute-1.amazonaws.com:3000/graphql"

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