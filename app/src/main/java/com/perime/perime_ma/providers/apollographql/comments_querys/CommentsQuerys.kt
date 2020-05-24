package com.perime.perime_ma.providers.apollographql.comments_querys

import android.util.Log
import apollo.GetCommentQuery
import apollo.GetCommentsQuery
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

class CommentsQuerys{
    companion object{
        fun getCommentsQuery(client : ApolloClient, queryCallback: (Response<GetCommentsQuery.Data>) -> Unit){
            Log.w("-----------", "--------------------------------------------")
            client.newBuilder().build().query(
                GetCommentsQuery()
            ).enqueue(object : ApolloCall.Callback<GetCommentsQuery.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<GetCommentsQuery.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: CONSULTA COMMENTSQUERY::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }

        fun getCommentQuery(client : ApolloClient, id: String, queryCallback: (Response<GetCommentQuery.Data>) -> Unit){
            Log.w("-----------", "--------------------------------------------")
            val input = Input.optional(id)

            client.newBuilder().build().query(
                GetCommentQuery(input)
            ).enqueue(object : ApolloCall.Callback<GetCommentQuery.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<GetCommentQuery.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: CONSULTA COMMENTQUERY::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }
    }
}