package com.vunh.android.demoahamove.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tb_account")
data class AccountEntity(
    @PrimaryKey()
    @SerializedName("id")
    var id: Int,
    @SerializedName("node_id")
    var nodeId: String,
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("followers")
    var followers: Int,
    @SerializedName("following")
    var following: Int,
    @SerializedName("location")
    var location: String,
    @SerializedName("blog")
    var blog: String,
    @SerializedName("email")
    var email: String,
) {

}