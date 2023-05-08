package com.example.androidfinal.utils

import com.example.androidfinal.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

suspend fun getCurrentUserData(): Result<User> {
    if(FirebaseUtils.auth.currentUser == null) {
        return Result.Error("User is not active")
    }
    var curUser = User()
    FirebaseUtils.ref.getReference("Users").child(FirebaseUtils.auth.currentUser!!.uid!!)
        .addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                curUser = snapshot.getValue(User::class.java)!!
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    return Result.Success(curUser)
}