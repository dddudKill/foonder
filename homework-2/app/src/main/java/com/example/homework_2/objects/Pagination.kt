package com.example.homework_2.objects

import com.google.gson.annotations.SerializedName

class Pagination {
    var offset = 0
    @SerializedName("total_count") var totalCount = 0
    var count = 0
}
