package com.vunh.android.demoahamove.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "tb_popular")
data class PopularEntity(
    @PrimaryKey
    @SerializedName("id")
    var id: Int,
    @SerializedName("node_id")
    var nodeId: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("description")
    var description: String?,
    @SerializedName("stargazers_count")
    var stargazersCount: Int,
    @SerializedName("forks_count")
    var forksCount: Int,
    @SerializedName("language")
    var language: String?,
    @SerializedName("visibility")
    var visibility: String?,
) : Serializable