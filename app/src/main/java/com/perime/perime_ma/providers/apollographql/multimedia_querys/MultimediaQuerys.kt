package com.perime.perime_ma.providers.apollographql.multimedia_querys

import android.util.Log
import apollo.FilesQuery
import apollo.GetFileQuery
import apollo.GetFileRegisterQuery
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.api.Input

class MultimediaQuerys{
    companion object{

        /* ######       GRAPHQL_QUERY : FilesQuery        ######*/
        /***
         * UN EJEMPLO DE USO
                apolloClient = ApolloGraphql.setUpApolloClient()
                MultimediaQuerys.filesQuery(apolloClient) {
                    var files = it.data?.files!!
                    for(file: FilesQuery.File? in files){
                        Log.w("File: ", file?.path_media)
                    }
                }
        **/
        fun filesQuery(client : ApolloClient, queryCallback: (Response<FilesQuery.Data>) -> Unit){
            Log.w("-----------", "--------------------------------------------")
            client.newBuilder().build().query(
                FilesQuery()
            ).enqueue(object : ApolloCall.Callback<FilesQuery.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<FilesQuery.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: CONSULTA FILESQUERY::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }



        /* ######       GRAPHQL_QUERY : getFileQuery        ######*/
        /***
         * UN EJEMPLO DE USO
                apolloClient = ApolloGraphql.setUpApolloClient()
                MultimediaQuerys.getFileQuery(apolloClient, "5") {
                    var file = it.data?.getFile!!
                    Log.w("File: ", file.path_media.toString())
                }
         **/
        fun getFileQuery(client : ApolloClient, id: String, queryCallback: (Response<GetFileQuery.Data>) -> Unit){
            Log.w("-----------", "--------------------------------------------")
            val input = Input.optional(id)

            client.query(
                GetFileQuery(input)
            ).enqueue(object : ApolloCall.Callback<GetFileQuery.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<GetFileQuery.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: CONSULTA FILESQUERY::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }



        /* ######       GRAPHQL_QUERY : getFileRegisterQuery        ######*/
        /***
         * UN EJEMPLO DE USO
                apolloClient = ApolloGraphql.setUpApolloClient()
                MultimediaQuerys.getFileRegisterQuery(apolloClient, "2", "USER") {
                    var file = it.data?.getFileRegister!!
                    Log.w("File: ", file.path_media.toString())
                }
         **/
        fun getFileRegisterQuery(client : ApolloClient, id: String, type: String, queryCallback: (Response<GetFileRegisterQuery.Data>) -> Unit){
            Log.w("-----------", "--------------------------------------------")
            val inputId = Input.optional(id)
            val inputType = Input.optional(type)

            client.query(
                GetFileRegisterQuery(inputId, inputType)
            ).enqueue(object : ApolloCall.Callback<GetFileRegisterQuery.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<GetFileRegisterQuery.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: CONSULTA FILESQUERY::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }



    }
}