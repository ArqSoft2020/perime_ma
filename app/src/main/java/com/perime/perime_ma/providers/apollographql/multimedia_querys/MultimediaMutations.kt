package com.perime.perime_ma.providers.apollographql.multimedia_querys

import android.util.Log
import apollo.StoreFileMutation
import apollo.UpdateFileMutation
import apollo.DeleteFileMutation
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

class MultimediaMutations{
    companion object{

        /* ######       GRAPHQL_MUTATION : storeFileMutation        ######*/
        /***
         * UN EJEMPLO DE USO
                apolloClient = ApolloGraphql.setUpApolloClient()
                MultimediaMutations.storeFileMutation(apolloClient, "2", "USER",file) {
                    var file = it.data?.storeFile!!
                    Log.w("File: ", file.id_model_media.toString())
                    Log.w("File: ", file.type_model_media.toString())
                    Log.w("File: ", file.path_media.toString())
                }
         **/
        fun storeFileMutation(client : ApolloClient, id: String, type: String, file: String, queryCallback: (Response<StoreFileMutation.Data>) -> Unit){
            Log.w("-----------", "--------------------------------------------")
            val inputId = Input.optional(id)
            val inputType = Input.optional(type)
            val inputFile = Input.optional(file)

            client.mutate(
                StoreFileMutation(inputId, inputType, inputFile)
            ).enqueue(object : ApolloCall.Callback<StoreFileMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<StoreFileMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: CONSULTA FILESQUERY::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }

        /* ######       GRAPHQL_MUTATION : updateFileMutation        ######*/
        fun updateFileMutation(client : ApolloClient, id: String, type: String, file: String, queryCallback: (Response<UpdateFileMutation.Data>) -> Unit){
            Log.w("-----------", "--------------------------------------------")
            val inputId = Input.optional(id)
            val inputType = Input.optional(type)
            val inputFile = Input.optional(file)

            client.mutate(
                UpdateFileMutation(inputId, inputType, inputFile)
            ).enqueue(object : ApolloCall.Callback<UpdateFileMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<UpdateFileMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: CONSULTA FILESQUERY::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }



        /* ######       GRAPHQL_MUTATION : deleteFileMutation        ######*/
        fun deleteFileMutation(client : ApolloClient, id: String, type: String, queryCallback: (Response<DeleteFileMutation.Data>) -> Unit){
            Log.w("-----------", "--------------------------------------------")
            val inputId = Input.optional(id)
            val inputType = Input.optional(type)

            client.mutate(
                DeleteFileMutation(inputId, inputType)
            ).enqueue(object : ApolloCall.Callback<DeleteFileMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<DeleteFileMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: CONSULTA FILESQUERY::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }


    }
}