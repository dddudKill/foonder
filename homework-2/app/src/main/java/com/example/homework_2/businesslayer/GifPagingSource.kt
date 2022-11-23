package com.example.homework_2.businesslayer

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework_2.datalayer.IAccessor
import com.example.homework_2.objects.Gif

const val NETWORK_PAGE_SIZE = 25

class GifPagingSource(private val service: IAccessor) : PagingSource<Int, Gif>() {
    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
//        val anchorPosition = state.anchorPosition ?: return null
//        val offset = state.closestPageToPosition(anchorPosition) ?: return null
//        return offset.prevKey?.plus(25) ?: offset.nextKey?.minus(25)
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        return  try {
            val offset = params.key ?: INITIAL_OFFSET
            val limit = params.loadSize
            val response = service.getData(offset = offset, limit = limit)
            val gifsResponse = response.gifs
            val paginationResponse = response.pagination
            val nextOffset = if (offset + NETWORK_PAGE_SIZE > paginationResponse.totalCount) null else offset + NETWORK_PAGE_SIZE
            val prevOffset = if (offset == INITIAL_OFFSET) null else offset - NETWORK_PAGE_SIZE
            LoadResult.Page(
                data = gifsResponse,
                prevKey = prevOffset,
                nextKey = nextOffset
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_OFFSET = 0
    }
}
