package com.perime.perime_ma.providers.apollographql.session_querys

import android.util.Log
import apollo.SessionmLoginMutation
import apollo.SessionmLogoutMutation
import apollo.type.SessionInput
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

class SessionMutations{
    companion object{

        /* ######       GRAPHQL_MUTATION : sessionmLoginMutation        ######*/
        /***
         * UN EJEMPLO DE USO
            apolloClient = ApolloGraphql.setUpApolloClient()
            SessionMutations.sessionmLoginMutation(apolloClient, "client@email.com", "123123") {
                var token = it.data?.sessionmLogin!!
                Log.w("File: ", token?.token_session.toString())
            }
         **/
        fun sessionmLoginMutation(client: ApolloClient,  email_user: String, passhash_user: String,
                               queryCallback : (Response<SessionmLoginMutation.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            val input = Input.optional(
                SessionInput(
                    Input.optional(email_user),
                    Input.optional(passhash_user)
                )
            )


            client.newBuilder().build().mutate(
                SessionmLoginMutation(input)
            ).enqueue(object : ApolloCall.Callback<SessionmLoginMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<SessionmLoginMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: sessionmLoginMutation::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }


        /* ######       GRAPHQL_MUTATION : sessionmLogout        ######*/
        /***
         * UN EJEMPLO DE USO
        apolloClient = ApolloGraphql.setUpApolloClient()
        SessionMutations.sessionmLogoutMutation(apolloClient, 1) {
        var token = it.data?.sessionmLogout!!
        Log.w("File: ", token?.token_session.toString())
        }
         **/
        fun sessionmLogoutMutation(client: ApolloClient,  id: Int,
                                  queryCallback : (Response<SessionmLogoutMutation.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            val input = Input.optional(id)


            client.newBuilder().build().mutate(
                SessionmLogoutMutation(input)
            ).enqueue(object : ApolloCall.Callback<SessionmLogoutMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<SessionmLogoutMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: sessionmLogoutMutation::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }
    }
}