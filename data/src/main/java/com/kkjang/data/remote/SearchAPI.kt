package com.kkjang.data.remote

import com.kkjang.data.model.remote.SearchUserResponse
import retrofit2.http.Query
import retrofit2.http.GET

internal interface SearchAPI {

    /**
     * 유저 검색
     *
     * @param query     검색어
     * @param page      페이지
     * @param perPage  페이지별 갯수
     * @return 검색된 유저 정보 목록
     */
    @GET("search/users")
    suspend fun searchUserPerPage(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchUserResponse
}