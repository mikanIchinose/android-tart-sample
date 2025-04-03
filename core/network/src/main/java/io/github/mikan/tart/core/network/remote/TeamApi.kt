package io.github.mikan.tart.core.network.remote

import io.github.mikan.tart.core.network.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.squareup.moshi.Json

import io.github.mikan.tart.core.network.model.AddGroupMemberRequest
import io.github.mikan.tart.core.network.model.AuthenticatedUser
import io.github.mikan.tart.core.network.model.Comment
import io.github.mikan.tart.core.network.model.CreateCommentRequest
import io.github.mikan.tart.core.network.model.CreateGroupRequest
import io.github.mikan.tart.core.network.model.CreateImportedCommentRequest
import io.github.mikan.tart.core.network.model.CreateItemRequest
import io.github.mikan.tart.core.network.model.DeleteGroupMemberRequest
import io.github.mikan.tart.core.network.model.Group
import io.github.mikan.tart.core.network.model.GroupMember
import io.github.mikan.tart.core.network.model.Item
import io.github.mikan.tart.core.network.model.UpdateCommentRequest
import io.github.mikan.tart.core.network.model.UpdateGroupRequest
import io.github.mikan.tart.core.network.model.UpdateItemRequest
import io.github.mikan.tart.core.network.model.User

interface TeamApi {
    /**
     * POST api/v2/groups/{url_name}/members
     * Add group members
     * 新たにグループにメンバーを追加します。
     * Responses:
     *  - 201: Created
     *
     * @param urlName グループのチーム上での一意な名前
     * @param addGroupMemberRequest  (optional)
     * @return [GroupMember]
     */
    @POST("api/v2/groups/{url_name}/members")
    suspend fun addGroupMembers(@Path("url_name") urlName: kotlin.String, @Body addGroupMemberRequest: AddGroupMemberRequest? = null): Response<GroupMember>

    /**
     * POST api/v2/items/{item_id}/comments
     * Create comment
     * 記事に対してコメントを投稿します。
     * Responses:
     *  - 201: Created
     *
     * @param itemId Article ID
     * @param createCommentRequest  (optional)
     * @return [Comment]
     */
    @POST("api/v2/items/{item_id}/comments")
    suspend fun createComment(@Path("item_id") itemId: kotlin.String, @Body createCommentRequest: CreateCommentRequest? = null): Response<Comment>

    /**
     * POST api/v2/groups
     * Create group
     * 新たにグループを作成します。
     * Responses:
     *  - 201: Created
     *
     * @param createGroupRequest  (optional)
     * @return [Group]
     */
    @POST("api/v2/groups")
    suspend fun createGroup(@Body createGroupRequest: CreateGroupRequest? = null): Response<Group>

    /**
     * POST api/v2/items/{item_id}/imported_comments
     * Create imported comment
     * ユーザー名を指定して記事に対するコメントを作成します(Qiita Teamでのみ有効。管理者権限が必要)。
     * Responses:
     *  - 201: Created
     *
     * @param itemId Article ID
     * @param createImportedCommentRequest  (optional)
     * @return [Comment]
     */
    @POST("api/v2/items/{item_id}/imported_comments")
    suspend fun createImportedComment(@Path("item_id") itemId: kotlin.String, @Body createImportedCommentRequest: CreateImportedCommentRequest? = null): Response<Comment>

    /**
     * POST api/v2/items
     * Create item
     * 新たに記事を作成します。  Create a new article.
     * Responses:
     *  - 201: Created
     *
     * @param createItemRequest  (optional)
     * @return [Item]
     */
    @POST("api/v2/items")
    suspend fun createItem(@Body createItemRequest: CreateItemRequest? = null): Response<Item>

    /**
     * PUT api/v2/items/{item_id}/stock
     * Create item stock
     * 記事をストックします。
     * Responses:
     *  - 204: No Content
     *  - 403: Forbidden
     *
     * @param itemId Article ID
     * @return [Unit]
     */
    @PUT("api/v2/items/{item_id}/stock")
    suspend fun createItemStock(@Path("item_id") itemId: kotlin.String): Response<Unit>

    /**
     * DELETE api/v2/comments/{comment_id}
     * Delete comment
     * コメントを削除します。
     * Responses:
     *  - 204: No Content
     *
     * @param commentId コメントの一意なID
     * @return [Unit]
     */
    @DELETE("api/v2/comments/{comment_id}")
    suspend fun deleteComment(@Path("comment_id") commentId: kotlin.String): Response<Unit>

    /**
     * DELETE api/v2/groups/{url_name}
     * Delete group
     * グループを削除します。
     * Responses:
     *  - 204: No Content
     *
     * @param urlName グループのチーム上での一意な名前
     * @return [Unit]
     */
    @DELETE("api/v2/groups/{url_name}")
    suspend fun deleteGroup(@Path("url_name") urlName: kotlin.String): Response<Unit>

    /**
     * DELETE api/v2/groups/{url_name}/members
     * Delete group members
     * グループからメンバーを脱退させます。
     * Responses:
     *  - 204: No Content
     *
     * @param urlName グループのチーム上での一意な名前
     * @param deleteGroupMemberRequest  (optional)
     * @return [Unit]
     */
    @DELETE("api/v2/groups/{url_name}/members")
    suspend fun deleteGroupMembers(@Path("url_name") urlName: kotlin.String, @Body deleteGroupMemberRequest: DeleteGroupMemberRequest? = null): Response<Unit>

    /**
     * DELETE api/v2/items/{item_id}
     * Get item stockers
     * 記事を削除します。
     * Responses:
     *  - 204: No Content
     *  - 404: Not Found
     *
     * @param itemId Article ID
     * @return [Unit]
     */
    @DELETE("api/v2/items/{item_id}")
    suspend fun deleteItem(@Path("item_id") itemId: kotlin.String): Response<Unit>

    /**
     * DELETE api/v2/items/{item_id}/stock
     * Delete item stock
     * 記事をストックから取り除きます。
     * Responses:
     *  - 204: No Content
     *  - 404: Not Found
     *
     * @param itemId Article ID
     * @return [Unit]
     */
    @DELETE("api/v2/items/{item_id}/stock")
    suspend fun deleteItemStock(@Path("item_id") itemId: kotlin.String): Response<Unit>

    /**
     * GET api/v2/authenticated_user
     * Get authenticated user
     * アクセストークンに紐付いたユーザーを返します。
     * Responses:
     *  - 200: OK
     *
     * @return [AuthenticatedUser]
     */
    @GET("api/v2/authenticated_user")
    suspend fun getAuthenticatedUser(): Response<AuthenticatedUser>

    /**
     * GET api/v2/authenticated_user/items
     * Get authenticated user items
     * 認証中のユーザーの記事の一覧を作成日時の降順で返します。
     * Responses:
     *  - 200: OK
     *
     * @param page ページ番号 (1から100まで) (optional)
     * @param perPage 1ページあたりに含まれる要素数 (1から100まで) (optional)
     * @return [Item]
     */
    @GET("api/v2/authenticated_user/items")
    suspend fun getAuthenticatedUserItems(@Query("page") page: kotlin.Int? = null, @Query("per_page") perPage: kotlin.Int? = null): Response<Item>

    /**
     * GET api/v2/comments/{comment_id}
     * Get comment
     * コメントを取得します。
     * Responses:
     *  - 200: OK
     *
     * @param commentId コメントの一意なID
     * @return [Comment]
     */
    @GET("api/v2/comments/{comment_id}")
    suspend fun getComment(@Path("comment_id") commentId: kotlin.String): Response<Comment>

    /**
     * GET api/v2/groups/{url_name}
     * Get group
     * グループを取得します。
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *
     * @param urlName グループのチーム上での一意な名前
     * @return [Group]
     */
    @GET("api/v2/groups/{url_name}")
    suspend fun getGroup(@Path("url_name") urlName: kotlin.String): Response<Group>

    /**
     * GET api/v2/groups/{url_name}/members/{user_id}
     * Get group member
     * グループのメンバーの名前を取得します。
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *
     * @param urlName グループのチーム上での一意な名前
     * @param userId ユーザーID
     * @return [Group]
     */
    @GET("api/v2/groups/{url_name}/members/{user_id}")
    suspend fun getGroupMember(@Path("url_name") urlName: kotlin.String, @Path("user_id") userId: kotlin.String): Response<Group>

    /**
     * GET api/v2/groups/{url_name}/members
     * Get group members
     * チーム内に存在する特定のグループ一のメンバー一覧を作成日時の降順で返します。
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *
     * @param urlName グループのチーム上での一意な名前
     * @param page ページ番号 (1から100まで) (optional)
     * @param perPage 1ページあたりに含まれる要素数 (1から100まで) (optional)
     * @return [Group]
     */
    @GET("api/v2/groups/{url_name}/members")
    suspend fun getGroupMembers(@Path("url_name") urlName: kotlin.String, @Query("page") page: kotlin.Int? = null, @Query("per_page") perPage: kotlin.Int? = null): Response<Group>

    /**
     * GET api/v2/groups
     * Get groups
     * チーム内に存在するグループ一覧のうち未参加のプライベートグループ以外を作成日時の降順で返します。
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *
     * @param page ページ番号 (1から100まで) (optional)
     * @param perPage 1ページあたりに含まれる要素数 (1から100まで) (optional)
     * @return [kotlin.collections.List<Group>]
     */
    @GET("api/v2/groups")
    suspend fun getGroups(@Query("page") page: kotlin.Int? = null, @Query("per_page") perPage: kotlin.Int? = null): Response<kotlin.collections.List<Group>>

    /**
     * GET api/v2/items/{item_id}
     * Get item
     * 記事を取得します。
     * Responses:
     *  - 200: OK
     *
     * @param itemId Article ID
     * @return [Item]
     */
    @GET("api/v2/items/{item_id}")
    suspend fun getItem(@Path("item_id") itemId: kotlin.String): Response<Item>

    /**
     * GET api/v2/items/{item_id}/comments
     * Get item comments
     * 投稿に付けられたコメント一覧を投稿日時の降順で取得します。
     * Responses:
     *  - 200: OK
     *
     * @param itemId Article ID
     * @return [kotlin.collections.List<Comment>]
     */
    @GET("api/v2/items/{item_id}/comments")
    suspend fun getItemComments(@Path("item_id") itemId: kotlin.String): Response<kotlin.collections.List<Comment>>

    /**
     * GET api/v2/items/{item_id}/stockers
     * Get item stockers
     * 記事をストックしているユーザー一覧を、ストックした日時の降順で返します。
     * Responses:
     *  - 200: OK
     *
     * @param itemId Article ID
     * @param page ページ番号 (1から100まで) (optional)
     * @param perPage 1ページあたりに含まれる要素数 (1から100まで) (optional)
     * @return [kotlin.collections.List<User>]
     */
    @GET("api/v2/items/{item_id}/stockers")
    suspend fun getItemStockers(@Path("item_id") itemId: kotlin.String, @Query("page") page: kotlin.Int? = null, @Query("per_page") perPage: kotlin.Int? = null): Response<kotlin.collections.List<User>>

    /**
     * GET api/v2/items
     * Get items
     * 記事の一覧を作成日時の降順で返します。
     * Responses:
     *  - 200: OK
     *
     * @param page ページ番号 (1から100まで) (optional)
     * @param perPage 1ページあたりに含まれる要素数 (1から100まで) (optional)
     * @param query 検索クエリ (optional)
     * @return [kotlin.collections.List<Item>]
     */
    @GET("api/v2/items")
    suspend fun getItems(@Query("page") page: kotlin.Int? = null, @Query("per_page") perPage: kotlin.Int? = null, @Query("query") query: kotlin.String? = null): Response<kotlin.collections.List<Item>>

    /**
     * GET api/v2/oauth/team_authorize
     * Get OAuth team authorize
     * チーム別アクセストークンを発行するには、それぞれのチームでアプリケーションのユーザーに認可画面を表示する必要があります。ユーザーがアプリケーションからのアクセスを認可すると、アプリケーション登録時に指定されたURLにリダイレクトされます。このとき、リダイレクト先のURLクエリにcodeが付与されます。また指定した場合は state も付与されます。アプリケーションでは、この code の値を利用して POST /api/v2/team_access_tokens にリクエストを送り、チーム別アクセストークンを発行します。Qiita Teamでのみ有効です。
     * Responses:
     *  - 200: OK
     *
     * @param clientId 登録されたAPIクライアントを特定するためのIDです。40桁の16進数で表現されます。
     * @param scope アプリケーションが利用するスコープをスペース区切りで指定できます。
     * @param state CSRF対策のため、認可後にリダイレクトするURLのクエリに含まれる値を指定できます。
     * @return [Unit]
     */
    @GET("api/v2/oauth/team_authorize")
    suspend fun getOauthTeamAuthorize(@Query("client_id") clientId: kotlin.String, @Query("scope") scope: kotlin.String, @Query("state") state: kotlin.String): Response<Unit>

    /**
     * GET api/v2/items/{item_id}/stock
     * Is item stock
     * 記事をストックしているかどうかを調べます。
     * Responses:
     *  - 204: No Content
     *  - 404: Not Found
     *
     * @param itemId Article ID
     * @return [Unit]
     */
    @GET("api/v2/items/{item_id}/stock")
    suspend fun isItemStock(@Path("item_id") itemId: kotlin.String): Response<Unit>

    /**
     * PATCH api/v2/comments/{comment_id}
     * Update comment
     * コメントを更新します。
     * Responses:
     *  - 200: OK
     *
     * @param commentId コメントの一意なID
     * @param updateCommentRequest  (optional)
     * @return [Comment]
     */
    @PATCH("api/v2/comments/{comment_id}")
    suspend fun updateComment(@Path("comment_id") commentId: kotlin.String, @Body updateCommentRequest: UpdateCommentRequest? = null): Response<Comment>

    /**
     * PATCH api/v2/groups/{url_name}
     * Update group
     * グループを更新します。
     * Responses:
     *  - 200: OK
     *
     * @param urlName グループのチーム上での一意な名前
     * @param updateGroupRequest  (optional)
     * @return [Group]
     */
    @PATCH("api/v2/groups/{url_name}")
    suspend fun updateGroup(@Path("url_name") urlName: kotlin.String, @Body updateGroupRequest: UpdateGroupRequest? = null): Response<Group>

    /**
     * PATCH api/v2/items/{item_id}
     * 
     * 記事を更新します。
     * Responses:
     *  - 200: OK
     *
     * @param itemId Article ID
     * @param updateItemRequest  (optional)
     * @return [Item]
     */
    @PATCH("api/v2/items/{item_id}")
    suspend fun updateItem(@Path("item_id") itemId: kotlin.String, @Body updateItemRequest: UpdateItemRequest? = null): Response<Item>

}
