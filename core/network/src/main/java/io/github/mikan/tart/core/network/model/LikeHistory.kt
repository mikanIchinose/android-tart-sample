/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package io.github.mikan.tart.core.network.model

import io.github.mikan.tart.core.network.model.User

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param createdAt データが作成された日時
 * @param user 
 */


data class LikeHistory (

    /* データが作成された日時 */
    @Json(name = "created_at")
    val createdAt: kotlin.String,

    @Json(name = "user")
    val user: User

) {


}

