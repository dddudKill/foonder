package com.example.homework_2.businesslayer

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.homework_2.datalayer.IAccessor
import com.example.homework_2.objects.Gif
import kotlinx.coroutines.flow.Flow

class GifProvider(private val accessor: IAccessor) {

    suspend fun getGifs(): Flow<PagingData<Gif>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GifPagingSource(accessor) }
        ).flow
    }
}