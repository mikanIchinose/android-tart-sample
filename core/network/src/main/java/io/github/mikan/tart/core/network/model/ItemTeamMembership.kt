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


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param name チームに登録しているユーザー名
 */


data class ItemTeamMembership (

    /* チームに登録しているユーザー名 */
    @Json(name = "name")
    val name: kotlin.String

) {


}

