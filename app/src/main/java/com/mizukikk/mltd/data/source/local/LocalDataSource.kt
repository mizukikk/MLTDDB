package com.mizukikk.mltd.data.source.local

import com.mizukikk.mltd.api.response.Card
import com.mizukikk.mltd.room.DBCallBack
import com.mizukikk.mltd.room.entity.IdolEntity
import com.mizukikk.mltd.room.query.IdolItem

interface LocalDataSource {
    fun checkDBData(callBack: DBCallBack<List<IdolEntity>>)
    fun saveAll(count: (progress: Int) -> Unit, vararg cards: Card.CardResponse)
    fun getIdolList(currentId: Int, lang: String, callBack: DBCallBack<List<IdolItem>>)
}