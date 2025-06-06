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
 * @param description 自己紹介文
 * @param facebookId Facebook ID
 * @param followeesCount このユーザーがフォローしているユーザーの数
 * @param followersCount このユーザーをフォローしているユーザーの数
 * @param githubLoginName GitHub ID
 * @param id ユーザーID
 * @param itemsCount このユーザーが qiita.com 上で公開している記事の数 (Qiita Teamでの記事数は含まれません)
 * @param linkedinId LinkedIn ID
 * @param location 居住地
 * @param name 設定している名前
 * @param organization 所属している組織
 * @param permanentId ユーザーごとに割り当てられる整数のID
 * @param profileImageUrl 設定しているプロフィール画像のURL
 * @param teamOnly Qiita Team専用モードに設定されているかどうか
 * @param twitterScreenName Twitterのスクリーンネーム
 * @param websiteUrl 設定しているWebサイトのURL
 */


data class User (

    /* 自己紹介文 */
    @Json(name = "description")
    val description: kotlin.String?,

    /* Facebook ID */
    @Json(name = "facebook_id")
    val facebookId: kotlin.String?,

    /* このユーザーがフォローしているユーザーの数 */
    @Json(name = "followees_count")
    val followeesCount: kotlin.Int,

    /* このユーザーをフォローしているユーザーの数 */
    @Json(name = "followers_count")
    val followersCount: kotlin.Int,

    /* GitHub ID */
    @Json(name = "github_login_name")
    val githubLoginName: kotlin.String?,

    /* ユーザーID */
    @Json(name = "id")
    val id: kotlin.String,

    /* このユーザーが qiita.com 上で公開している記事の数 (Qiita Teamでの記事数は含まれません) */
    @Json(name = "items_count")
    val itemsCount: kotlin.Int,

    /* LinkedIn ID */
    @Json(name = "linkedin_id")
    val linkedinId: kotlin.String?,

    /* 居住地 */
    @Json(name = "location")
    val location: kotlin.String?,

    /* 設定している名前 */
    @Json(name = "name")
    val name: kotlin.String?,

    /* 所属している組織 */
    @Json(name = "organization")
    val organization: kotlin.String?,

    /* ユーザーごとに割り当てられる整数のID */
    @Json(name = "permanent_id")
    val permanentId: kotlin.Int,

    /* 設定しているプロフィール画像のURL */
    @Json(name = "profile_image_url")
    val profileImageUrl: kotlin.String,

    /* Qiita Team専用モードに設定されているかどうか */
    @Json(name = "team_only")
    val teamOnly: kotlin.Boolean,

    /* Twitterのスクリーンネーム */
    @Json(name = "twitter_screen_name")
    val twitterScreenName: kotlin.String?,

    /* 設定しているWebサイトのURL */
    @Json(name = "website_url")
    val websiteUrl: kotlin.String?

) {


}

