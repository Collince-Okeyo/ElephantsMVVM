package com.ramgdeveloper.elephantsmvvm.data.paging

import android.os.Parcel
import android.os.Parcelable
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ramgdeveloper.elephantsmvvm.data.remote.ElephantsApi
import com.ramgdeveloper.elephantsmvvm.model.Elephants
import com.ramgdeveloper.elephantsmvvm.util.Constants.ELEPHANTS_STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class ElephantsPagingSource(
    private val backend: ElephantsApi,
) : PagingSource<Int, Elephants>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Elephants> {
        val position = params.key ?: ELEPHANTS_STARTING_PAGE_INDEX
        return try {
            // Start refresh at page 1 if undefined.
            val response = backend.getElephant()

            LoadResult.Page(
                data = response,
                prevKey = if (position == ELEPHANTS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Elephants>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

