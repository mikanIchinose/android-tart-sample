package io.github.mikan.tart.core.network.remote

import io.github.mikan.tart.core.network.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.squareup.moshi.Json

import io.github.mikan.tart.core.network.model.AuthenticatedUser
import io.github.mikan.tart.core.network.model.Comment
import io.github.mikan.tart.core.network.model.CreateCommentRequest
import io.github.mikan.tart.core.network.model.CreateItemRequest
import io.github.mikan.tart.core.network.model.IssueAccessTokenRequest
import io.github.mikan.tart.core.network.model.Item
import io.github.mikan.tart.core.network.model.LikeHistory
import io.github.mikan.tart.core.network.model.UpdateCommentRequest
import io.github.mikan.tart.core.network.model.UpdateItemRequest
import io.github.mikan.tart.core.network.model.User

interface UserApi {
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
     * PUT api/v2/items/{item_id}/like
     * Create item like
     * Qiita TeamのいいねAPIは2020年11月4日より廃止となりました。今後は絵文字リアクションAPIをご利用ください。 記事に「いいね」を付けます。
     * Responses:
     *  - 204: No Content
     *  - 403: Forbidden
     *
     * @param itemId Article ID
     * @return [Unit]
     */
    @PUT("api/v2/items/{item_id}/like")
    suspend fun createItemLike(@Path("item_id") itemId: kotlin.String): Response<Unit>

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
     * DELETE api/v2/access_tokens/{access_token}
     * 
     * 指定されたアクセストークンを失効させ、それ以降利用できないようにします。 
     * Responses:
     *  - 200: OK
     *
     * @param accessToken アクセストークンを表現する文字列
     * @return [Unit]
     */
    @DELETE("api/v2/access_tokens/{access_token}")
    suspend fun deleteApiV2AccessTokensAccessToken(@Path("access_token") accessToken: kotlin.String): Response<Unit>

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
     * DELETE api/v2/items/{item_id}/like
     * Delete item like
     * Qiita TeamのいいねAPIは2020年11月4日より廃止となりました。今後は絵文字リアクションAPIをご利用ください。 記事への「いいね」を取り消します。
     * Responses:
     *  - 204: No Content
     *  - 404: Not Found
     *
     * @param itemId Article ID
     * @return [Unit]
     */
    @DELETE("api/v2/items/{item_id}/like")
    suspend fun deleteItemLike(@Path("item_id") itemId: kotlin.String): Response<Unit>

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
     * PUT api/v2/users/{user_id}/following
     * Follow
     * ユーザーをフォローします。
     * Responses:
     *  - 200: OK
     *  - 403: Forbidden
     *
     * @param userId ユーザーID
     * @return [Unit]
     */
    @PUT("api/v2/users/{user_id}/following")
    suspend fun follow(@Path("user_id") userId: kotlin.String): Response<Unit>

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
     * GET api/v2/items/{item_id}/likes
     * Get item likes
     * Qiita TeamのいいねAPIは2020年11月4日より廃止となりました。今後は絵文字リアクションAPIをご利用ください。  記事につけられた「いいね」を作成日時の降順で返します。
     * Responses:
     *  - 200: OK
     *
     * @param itemId Article ID
     * @return [kotlin.collections.List<LikeHistory>]
     */
    @GET("api/v2/items/{item_id}/likes")
    suspend fun getItemLikes(@Path("item_id") itemId: kotlin.String): Response<kotlin.collections.List<LikeHistory>>

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
     * GET api/v2/oauth/authorize
     * Get OAuth authorize
     * read_qiita_team、write_qiita_teamを使う場合、authorizeページによる認証認可は2020年6月5日に非推奨となりました。今後はご利用されるチームのホストでteam_authorizeをご利用ください。 アクセストークンを発行するには、アプリケーションのユーザーに認可画面を表示する必要があります。ユーザーがアプリケーションからのアクセスを認可すると、アプリケーション登録時に指定されたURLにリダイレクトされます。このとき、リダイレクト先のURLクエリにcodeが付与されます。また指定した場合は state も付与されます。アプリケーションでは、この code の値を利用して POST /api/v2/access_tokens にリクエストを送り、アクセストークンを発行します。
     * Responses:
     *  - 200: OK
     *
     * @param clientId 登録されたAPIクライアントを特定するためのIDです。40桁の16進数で表現されます。
     * @param scope アプリケーションが利用するスコープをスペース区切りで指定できます。
     * @param state CSRF対策のため、認可後にリダイレクトするURLのクエリに含まれる値を指定できます。
     * @return [Unit]
     */
    @GET("api/v2/oauth/authorize")
    suspend fun getOauthAuthorize(@Query("client_id") clientId: kotlin.String, @Query("scope") scope: kotlin.String, @Query("state") state: kotlin.String): Response<Unit>

    /**
     * GET api/v2/users/{user_id}
     * Get user
     * ユーザーを取得します。
     * Responses:
     *  - 200: OK
     *
     * @param userId ユーザーID
     * @return [User]
     */
    @GET("api/v2/users/{user_id}")
    suspend fun getUser(@Path("user_id") userId: kotlin.String): Response<User>

    /**
     * GET api/v2/users/{user_id}/followees
     * Get user followees
     * ユーザーがフォローしているユーザー一覧を取得します。
     * Responses:
     *  - 200: OK
     *
     * @param userId ユーザーID
     * @param page ページ番号 (1から100まで) (optional)
     * @param perPage 1ページあたりに含まれる要素数 (1から100まで) (optional)
     * @return [kotlin.collections.List<User>]
     */
    @GET("api/v2/users/{user_id}/followees")
    suspend fun getUserFollowees(@Path("user_id") userId: kotlin.String, @Query("page") page: kotlin.Int? = null, @Query("per_page") perPage: kotlin.Int? = null): Response<kotlin.collections.List<User>>

    /**
     * GET api/v2/users/{user_id}/followers
     * Get user followers
     * ユーザーをフォローしているユーザー一覧を取得します。
     * Responses:
     *  - 200: OK
     *
     * @param userId ユーザーID
     * @param page ページ番号 (1から100まで) (optional)
     * @param perPage 1ページあたりに含まれる要素数 (1から100まで) (optional)
     * @return [kotlin.collections.List<User>]
     */
    @GET("api/v2/users/{user_id}/followers")
    suspend fun getUserFollowers(@Path("user_id") userId: kotlin.String, @Query("page") page: kotlin.Int? = null, @Query("per_page") perPage: kotlin.Int? = null): Response<kotlin.collections.List<User>>

    /**
     * GET api/v2/users
     * Get users
     * 全てのユーザーの一覧を作成日時の降順で取得します。
     * Responses:
     *  - 200: OK
     *
     * @param page ページ番号 (1から100まで) (optional)
     * @param perPage 1ページあたりに含まれる要素数 (1から100まで) (optional)
     * @return [kotlin.collections.List<User>]
     */
    @GET("api/v2/users")
    suspend fun getUsers(@Query("page") page: kotlin.Int? = null, @Query("per_page") perPage: kotlin.Int? = null): Response<kotlin.collections.List<User>>

    /**
     * GET api/v2/items/{item_id}/like
     * Is item like
     * Qiita TeamのいいねAPIは2020年11月4日より廃止となりました。今後は絵文字リアクションAPIをご利用ください。 記事に「いいね」を付けているかどうかを調べます。
     * Responses:
     *  - 204: No Content
     *  - 404: Not Found
     *
     * @param itemId Article ID
     * @return [Unit]
     */
    @GET("api/v2/items/{item_id}/like")
    suspend fun isItemLike(@Path("item_id") itemId: kotlin.String): Response<Unit>

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
     * GET api/v2/users/{user_id}/following
     * Is user following
     * ユーザーをフォローしている場合に204を返します。
     * Responses:
     *  - 204: No Content
     *  - 404: Not Found
     *
     * @param userId ユーザーID
     * @return [Unit]
     */
    @GET("api/v2/users/{user_id}/following")
    suspend fun isUserFollowing(@Path("user_id") userId: kotlin.String): Response<Unit>

    /**
     * POST api/v2/access_tokens
     * Issue access token
     * 与えられた認証情報をもとに新しいアクセストークンを発行します。
     * Responses:
     *  - 200: OK
     *
     * @param issueAccessTokenRequest  (optional)
     * @return [Unit]
     */
    @POST("api/v2/access_tokens")
    suspend fun issueAccessTokens(@Body issueAccessTokenRequest: IssueAccessTokenRequest? = null): Response<Unit>

    /**
     * DELETE api/v2/users/{user_id}/following
     * Unfollow
     * ユーザーへのフォローを外します。
     * Responses:
     *  - 204: No Content
     *  - 403: Forbidden
     *
     * @param userId ユーザーID
     * @return [Unit]
     */
    @DELETE("api/v2/users/{user_id}/following")
    suspend fun unfollow(@Path("user_id") userId: kotlin.String): Response<Unit>

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
