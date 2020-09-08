package com.mizukikk.mltd.data

import com.mizukikk.mltd.api.ResponseCallBack
import com.mizukikk.mltd.api.response.Card
import com.mizukikk.mltd.data.source.local.LocalDataSource
import com.mizukikk.mltd.data.source.remote.MLTDRemoteDataSource
import com.mizukikk.mltd.data.source.remote.RemoteDataSource
import com.mizukikk.mltd.room.DBCallBack
import com.mizukikk.mltd.room.entity.IdolEntity
import com.mizukikk.mltd.room.query.IdolItem

class MLTDRepository private constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RemoteDataSource, LocalDataSource {

    companion object {
        private var INSTANCE: MLTDRepository? = null
        fun newInstance(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource) =
            INSTANCE ?: synchronized(MLTDRemoteDataSource) {
                MLTDRepository(localDataSource, remoteDataSource)
            }
    }

    override fun downloadAllCard(callBack: ResponseCallBack<List<Card.CardResponse>>) {
        remoteDataSource.downloadAllCard(callBack)
    }

    override fun checkDBData(callBack: DBCallBack<List<IdolEntity>>) {
        localDataSource.checkDBData(callBack)
    }

    override fun saveAll(count: (progress: Int) -> Unit, vararg cards: Card.CardResponse) {
        localDataSource.saveAll(count, *cards)
    }

    override fun getIdolList(
        currentId: Int,
        lang: String,
        callBack: DBCallBack<List<IdolItem>>
    ) {
        localDataSource.getIdolList(currentId, lang, callBack)
    }
}