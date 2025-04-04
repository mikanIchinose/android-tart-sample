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

import io.github.mikan.tart.core.network.model.GetAuthenticatedUserItemsResponseInnerGroup
import io.github.mikan.tart.core.network.model.GetAuthenticatedUserItemsResponseInnerTagsInner
import io.github.mikan.tart.core.network.model.GetAuthenticatedUserItemsResponseInnerTeamMembership
import io.github.mikan.tart.core.network.model.GetAuthenticatedUserItemsResponseInnerUser

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param renderedBody 
 * @param body 
 * @param coediting 
 * @param commentsCount 
 * @param createdAt 
 * @param group 
 * @param id 
 * @param likesCount 
 * @param `private` 
 * @param reactionsCount 
 * @param stocksCount 
 * @param tags 
 * @param title 
 * @param updatedAt 
 * @param url 
 * @param user 
 * @param pageViewsCount 
 * @param teamMembership 
 * @param organizationUrlName 
 * @param slide 
 */


data class GetAuthenticatedUserItemsResponseInner (

    @Json(name = "rendered_body")
    val renderedBody: kotlin.String? = null,

    @Json(name = "body")
    val body: kotlin.String? = null,

    @Json(name = "coediting")
    val coediting: kotlin.Boolean? = null,

    @Json(name = "comments_count")
    val commentsCount: kotlin.Int? = null,

    @Json(name = "created_at")
    val createdAt: kotlin.String? = null,

    @Json(name = "group")
    val group: GetAuthenticatedUserItemsResponseInnerGroup? = null,

    @Json(name = "id")
    val id: kotlin.String? = null,

    @Json(name = "likes_count")
    val likesCount: kotlin.Int? = null,

    @Json(name = "private")
    val `private`: kotlin.Boolean? = null,

    @Json(name = "reactions_count")
    val reactionsCount: kotlin.Int? = null,

    @Json(name = "stocks_count")
    val stocksCount: kotlin.Int? = null,

    @Json(name = "tags")
    val tags: kotlin.collections.List<GetAuthenticatedUserItemsResponseInnerTagsInner>? = null,

    @Json(name = "title")
    val title: kotlin.String? = null,

    @Json(name = "updated_at")
    val updatedAt: kotlin.String? = null,

    @Json(name = "url")
    val url: kotlin.String? = null,

    @Json(name = "user")
    val user: GetAuthenticatedUserItemsResponseInnerUser? = null,

    @Json(name = "page_views_count")
    val pageViewsCount: kotlin.Int? = null,

    @Json(name = "team_membership")
    val teamMembership: GetAuthenticatedUserItemsResponseInnerTeamMembership? = null,

    @Json(name = "organization_url_name")
    val organizationUrlName: kotlin.String? = null,

    @Json(name = "slide")
    val slide: kotlin.Boolean? = null

) {


}

