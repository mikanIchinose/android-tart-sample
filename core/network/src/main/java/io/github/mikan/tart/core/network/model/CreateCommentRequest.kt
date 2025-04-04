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
 * @param body コメントの内容を表すMarkdown形式の文字列
 */


data class CreateCommentRequest (

    /* コメントの内容を表すMarkdown形式の文字列 */
    @Json(name = "body")
    val body: kotlin.String

) {


}

