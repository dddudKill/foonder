package com.example.lecture05.businesslayer

import com.example.lecture05.datalayer.IAccessor
import com.example.lecture05.objects.Cat

class CatProvider(val accessor: IAccessor) {
    suspend fun getCats(offset: Int, limit: Int): List<Cat> {
        return accessor.getCats(offset, limit)
    }
}