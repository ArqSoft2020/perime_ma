package com.perime.perime_ma.providers.apollographql.publication_querys

import android.util.Log
import apollo.CreatePublicationMutation
import apollo.DeletePublicationMutation
import apollo.UpdatePublicationMutation
import apollo.type.PublicationInput
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

class PublicationMutations {
    companion object {


        /* ######       GRAPHQL_MUTATION : createPublicationMutation        ######*/
        /***
         * UN EJEMPLO DE USO
                apolloClient = ApolloGraphql.setUpApolloClient()
                PublicationMutations.createPublicationMutation(apolloClient, "Arroz android", "apollo arroz", true, "42531433425", "2", "1", "2016-05-18T16:00:00Z", "20", listOf("arroz","diana","blanca")) {
                    var publication = it.data?.createPublication!!
                    Log.w("File: ", publication?.title.toString())
                }
         **/
        fun createPublicationMutation(client: ApolloClient, title: String, description: String, state_publication: Boolean, contact_information: String, id_image: String, stock: String,
                                   expiration_date: String, price: String, categories: List<String>, queryCallback : (Response<CreatePublicationMutation.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            val input = Input.optional(PublicationInput(Input.optional(""), Input.optional(title),Input.optional(description),Input.optional(state_publication),Input.optional(contact_information),
                Input.optional(id_image), Input.optional(stock),Input.optional(expiration_date),Input.optional(price),Input.optional(categories)) )


            client.newBuilder().build().mutate(
                CreatePublicationMutation(input)
            ).enqueue(object : ApolloCall.Callback<CreatePublicationMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<CreatePublicationMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: createPublicationMutation::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }


        /* ######       GRAPHQL_MUTATION : updatePublicationMutation        ######*/
        /***
         * UN EJEMPLO DE USO
                apolloClient = ApolloGraphql.setUpApolloClient()
                PublicationMutations.updatePublicationMutation(apolloClient, "5eb20adac0c12f0012a0b803", "Arroz android updated", "apollo arroz", true, "42531433425", "2", "1", "2016-05-18T16:00:00Z", "20", listOf("arroz","diana","blanca")) {
                    var publication = it.data?.updatePublication!!
                    Log.w("File: ", publication?.title.toString())
                }
         **/
        fun updatePublicationMutation(client: ApolloClient, id: String, title: String, description: String, state_publication: Boolean, contact_information: String, id_image: String, stock: String,
                                      expiration_date: String, price: String, categories: List<String>, queryCallback : (Response<UpdatePublicationMutation.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            val inputId = Input.optional(id)
            val input = Input.optional(PublicationInput(Input.optional(id), Input.optional(title),Input.optional(description),Input.optional(state_publication),Input.optional(contact_information),
                Input.optional(id_image), Input.optional(stock),Input.optional(expiration_date),Input.optional(price),Input.optional(categories)) )


            client.newBuilder().build().mutate(
                UpdatePublicationMutation(inputId, input)
            ).enqueue(object : ApolloCall.Callback<UpdatePublicationMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<UpdatePublicationMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: updatePublicationMutation::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }



        /* ######       GRAPHQL_MUTATION : deletePublicationMutation        ######*/
        /***
         * UN EJEMPLO DE USO
                apolloClient = ApolloGraphql.setUpApolloClient()
                PublicationMutations.deletePublicationMutation(apolloClient, "5eb20adac0c12f0012a0b803") {
                    var publication = it.data?.deletePublication!!
                    Log.w("File: ", publication?.toString())
                }
         **/
        fun deletePublicationMutation(client: ApolloClient, id: String, queryCallback : (Response<DeletePublicationMutation.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            val inputId = Input.optional(id)


            client.newBuilder().build().mutate(
                DeletePublicationMutation(inputId)
            ).enqueue(object : ApolloCall.Callback<DeletePublicationMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<DeletePublicationMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: deletePublicationMutation::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }
    }
}