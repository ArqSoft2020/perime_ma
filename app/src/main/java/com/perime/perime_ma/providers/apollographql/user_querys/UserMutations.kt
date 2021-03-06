package com.perime.perime_ma.providers.apollographql.user_querys

import android.util.Log
import apollo.CreateUserMutation
import apollo.DeleteUserMutation
import apollo.UpdateUserMutation
import apollo.type.UserInput
//import apollo.CreateUserMutation
//import apollo.DeleteUserMutation
//import apollo.UpdateUserMutation
//import apollo.type.UserInput
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

class UserMutations {

    companion object {


        /* ######       GRAPHQL_MUTATION : createPublicationMutation        ######*/
        /***
         * UN EJEMPLO DE USO
        apolloClient = ApolloGraphql.setUpApolloClient()
        UserMutations.createUserMutation(apolloClient, "luaavilagu", "password", "Cra. 102 # 30x - 33", "3001234567", "luaavilagu@temp.com")) {
        var user = it.data?.createUser!!
        Log.w("File: ", user?.email_user.toString())
        }
         **/
        fun createUserMutation(client: ApolloClient, username_user: String, passhash_user: String, address_user: String, cellphone_user: String, email_user: String,
                               queryCallback : (Response<CreateUserMutation.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            val input = Input.optional(
                UserInput(
                    Input.optional(username_user),
                    Input.optional(passhash_user),
                    Input.optional(address_user),
                    Input.optional(cellphone_user),
                    Input.optional(email_user)))


            client.newBuilder().build().mutate(
                CreateUserMutation(input)
            ).enqueue(object : ApolloCall.Callback<CreateUserMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<CreateUserMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: createUserMutation::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }


        /* ######       GRAPHQL_MUTATION : updatePublicationMutation        ######*/
        /***
         * UN EJEMPLO DE USO
        apolloClient = ApolloGraphql.setUpApolloClient()
        UserMutations.updateUserMutation(apolloClient, "luaavilagu1", "password", "Cra. 102 # 30x - 33", "3001234567", "luaavilagu@temp.com") {
        var user = it.data?.updateUser!!
        Log.w("File: ", user?.email_user.toString())
        }
         **/
        fun updateUserMutation(client: ApolloClient,  id_user: String, username_user: String, passhash_user: String, address_user: String, cellphone_user: String, email_user: String,
                               queryCallback : (Response<UpdateUserMutation.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            val inputId = Input.optional(id_user)
            val input = Input.optional(
                UserInput(
                    Input.optional(username_user),
                    Input.optional(passhash_user),
                    Input.optional(address_user),
                    Input.optional(cellphone_user),
                    Input.optional(email_user)) )


            client.newBuilder().build().mutate(
                UpdateUserMutation(inputId, input)
            ).enqueue(object : ApolloCall.Callback<UpdateUserMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<UpdateUserMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: updateUserMutation::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }



        /* ######       GRAPHQL_MUTATION : deletePublicationMutation        ######*/
        /***
         * UN EJEMPLO DE USO
        apolloClient = ApolloGraphql.setUpApolloClient()
        UserMutations.deleteUserMutation(apolloClient, "1") {
        var user = it.data?.deleteUser!!
        Log.w("File: ", user?.toString())
        }
         **/
        fun deleteUserMutation(client: ApolloClient, id_user: String, queryCallback : (Response<DeleteUserMutation.Data>) -> Unit) {
            Log.w("-----------", "--------------------------------------------")
            val inputId = Input.optional(id_user)


            client.newBuilder().build().mutate(
                DeleteUserMutation(inputId)
            ).enqueue(object : ApolloCall.Callback<DeleteUserMutation.Data>(){
                override fun onFailure(e: ApolloException) { Log.w("######  ERROR:::    ", e.message.toString()); Log.w("-----------", "--------------------------------------------") }

                override fun onResponse(response: Response<DeleteUserMutation.Data>) {

                    queryCallback(response)

                    Log.w("######  GRAPHQL: deleteUserMutation::: ", response.data.toString());Log.w("-----------", "--------------------------------------------")
                }

            })
        }
    }
}