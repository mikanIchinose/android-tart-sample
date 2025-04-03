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
 * @param userId ユーザーID
 * @param createdAt データが作成された日時
 * @param updatedAt データが最後に更新された日時
 */


data class CreateImportedCommentRequest (

    /* コメントの内容を表すMarkdown形式の文字列 */
    @Json(name = "body")
    val body: kotlin.String,

    /* ユーザーID */
    @Json(name = "user_id")
    val userId: kotlin.String,

    /* データが作成された日時 */
    @Json(name = "created_at")
    val createdAt: kotlin.String? = null,

    /* データが最後に更新された日時 */
    @Json(name = "updated_at")
    val updatedAt: kotlin.String? = null

) {


}

