package com.perime.perime_ma.providers.apollographql.publication_querys

import android.util.Log
import apollo.GetPublicationQuery
import apollo.PublicationsQuery
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

class PublicationQuerys {
    companion object{

        /* ######       GRAPHQL_QUERY : publicationsQuery        ######*/
        /***
         * UN EJEMPLO DE USO
                apolloClient = ApolloGraphql.setUpApolloClient()
                PublicationQuerys.publicationsQuery(apolloClient) {
                    var publications = it.data?.publications!!
                    for(publication: PublicationsQuery.Publication? in publications){
                        Log.w("File: ", publication?.title)
                    }
                }
         **/
        fun publicationsQuery(client: ApolloClient, queryCallback : (Response<PublicationsQuery.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            client.newBuilder().build().query(
                PublicationsQuery()
            ).enqueue(object : ApolloCall.Callback<PublicationsQuery.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<PublicationsQuery.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: publicationsQuery::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }



        /* ######       GRAPHQL_QUERY : getPublicationQuery        ######*/
        /***
         * UN EJEMPLO DE USO
                apolloClient = ApolloGraphql.setUpApolloClient()
                PublicationQuerys.getPublicationQuery(apolloClient, "5eb0b8022727be001995a06d") {
                    var publication = it.data?.getPublication!!
                    Log.w("File: ", publication?.title.toString())
                }
         **/
        fun getPublicationQuery(client: ApolloClient, id: String, queryCallback : (Response<GetPublicationQuery.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            val input = Input.optional(id)

            client.newBuilder().build().query(
                GetPublicationQuery(input)
            ).enqueue(object : ApolloCall.Callback<GetPublicationQuery.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<GetPublicationQuery.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: getPublicationQuery::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }


    }
}