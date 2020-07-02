package com.perime.perime_ma.providers.apollographql.user_querys

import android.util.Log
import apollo.GetUserQuery
import apollo.UsersQuery
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.request.RequestHeaders

class UserQuerys {
    companion object{

        /* ######       GRAPHQL_QUERY : publicationsQuery        ######*/
        /***
         * UN EJEMPLO DE USO
                apolloClient = ApolloGraphql.setUpApolloClient()
                UserQuerys.usersQuery(apolloClient) {
                var users = it.data?.user!!
                for(user: UsersQuery.User? in users){
                    Log.w("File: ", publication?.email_user)
                }
            }
         **/
        fun usersQuery(client: ApolloClient, queryCallback : (Response<UsersQuery.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            client.newBuilder().build().query(
                UsersQuery()
            ).enqueue(object : ApolloCall.Callback<UsersQuery.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<UsersQuery.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: usersQuery::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }



        /* ######       GRAPHQL_QUERY : getPublicationQuery        ######*/
        /***
         * UN EJEMPLO DE USO
        apolloClient = ApolloGraphql.setUpApolloClient()
        UserQuerys.getUserQuery(apolloClient, "1") {
        var user = it.data?.getUser!!
        Log.w("File: ", user?.email_user.toString())
        }
         **/
        fun getUserQuery(client: ApolloClient, id: Int, token: String, queryCallback : (Response<GetUserQuery.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            val input = Input.optional(id)

            client.newBuilder().build().query(
                GetUserQuery(input)
            ).requestHeaders(
                RequestHeaders.builder()
                    .addHeader("authorization", token)
                    .build()
            ).enqueue(object : ApolloCall.Callback<GetUserQuery.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<GetUserQuery.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: getPublicationQuery::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }


    }
}