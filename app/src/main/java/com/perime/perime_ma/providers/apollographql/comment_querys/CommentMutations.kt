package com.perime.perime_ma.providers.apollographql.comment_querys

import android.util.Log
import apollo.DeleteCommentMutation
import apollo.StoreCommentMutation
import apollo.UpdateCommentMutation
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

class CommentMutations {
    companion object{
        fun createCommentMutation(client: ApolloClient, idpubl: String, iduser: String, text: String
                                  , queryCallback : (Response<StoreCommentMutation.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")

            val inputIdPubl = Input.optional(idpubl)
            val inputIdUser = Input.optional(iduser)
            val inputText = Input.optional(text)

            client.newBuilder().build().mutate(
                StoreCommentMutation(inputIdPubl, inputIdUser, inputText)
            ).enqueue(object : ApolloCall.Callback<StoreCommentMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<StoreCommentMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: storeCommentMutation::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }

        fun updateCommentMutation(client : ApolloClient, id: String, text: String, queryCallback: (Response<UpdateCommentMutation.Data>) -> Unit){
            Log.w("-----------", "--------------------------------------------")
            val inputId = Input.optional(id)
            val inputText = Input.optional(text)

            client.newBuilder().build().mutate(
                UpdateCommentMutation(inputId, inputText)
            ).enqueue(object : ApolloCall.Callback<UpdateCommentMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<UpdateCommentMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: updateCommentMutation::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }

        fun deleteCommentMutation(client : ApolloClient, id: String, queryCallback: (Response<DeleteCommentMutation.Data>) -> Unit){
            Log.w("-----------", "--------------------------------------------")
            val inputId = Input.optional(id)

            client.newBuilder().build().mutate(
                DeleteCommentMutation(inputId)
            ).enqueue(object : ApolloCall.Callback<DeleteCommentMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<DeleteCommentMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: deleteCommentMutation::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }

    }
}