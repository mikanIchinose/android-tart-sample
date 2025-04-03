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

import io.github.mikan.tart.core.network.model.ItemTag

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param body Markdown形式の本文
 * @param tags 記事に付いたタグ一覧
 * @param title 記事のタイトル
 * @param coediting この記事が共同更新状態かどうか (Qiita Teamでのみ有効)
 * @param groupUrlName この投稿を公開するグループの url_name (null で全体に公開。Qiita Teamでのみ有効)
 * @param `private` 限定共有状態かどうかを表すフラグ (Qiita Teamでは無効)
 * @param tweet Twitterに投稿するかどうか (Twitter連携を有効化している場合のみ有効)
 * @param organizationUrlName 記事のOrganization の url_name を表します。
 * @param slide スライドモードが有効を表すフラグ
 */


data class CreateItemRequest (

    /* Markdown形式の本文 */
    @Json(name = "body")
    val body: kotlin.String,

    /* 記事に付いたタグ一覧 */
    @Json(name = "tags")
    val tags: kotlin.collections.List<ItemTag>,

    /* 記事のタイトル */
    @Json(name = "title")
    val title: kotlin.String,

    /* この記事が共同更新状態かどうか (Qiita Teamでのみ有効) */
    @Json(name = "coediting")
    val coediting: kotlin.Boolean? = null,

    /* この投稿を公開するグループの url_name (null で全体に公開。Qiita Teamでのみ有効) */
    @Json(name = "group_url_name")
    val groupUrlName: kotlin.String? = null,

    /* 限定共有状態かどうかを表すフラグ (Qiita Teamでは無効) */
    @Json(name = "private")
    val `private`: kotlin.Boolean? = null,

    /* Twitterに投稿するかどうか (Twitter連携を有効化している場合のみ有効) */
    @Json(name = "tweet")
    val tweet: kotlin.Boolean? = null,

    /* 記事のOrganization の url_name を表します。 */
    @Json(name = "organization_url_name")
    val organizationUrlName: kotlin.String? = null,

    /* スライドモードが有効を表すフラグ */
    @Json(name = "slide")
    val slide: kotlin.Boolean? = null

) {


}

