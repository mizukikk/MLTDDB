package com.mizukikk.mltd.main.event.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mizukikk.mltd.api.ResponseCallBack
import com.mizukikk.mltd.api.response.Event
import com.mizukikk.mltd.main.event.model.EventDetailData
import com.mizukikk.mltd.main.viewmodel.BaseMainViewModel
import retrofit2.Call

class EventListViewModel(application: Application) : BaseMainViewModel(application) {

    val eventListLiveData = MutableLiveData<List<Event.EventResponse>>()

    private var isCheckApiRunning = false

    fun getEventList() {
        repository.getEventList(object : ResponseCallBack<List<Event.EventResponse>>() {
            override fun success(response: List<Event.EventResponse>) {
                eventListLiveData.postValue(response.sortedBy { it.sort }.reversed())
            }

            override fun fail(
                errorMessage: String,
                errorCode: Int?,
                call: Call<List<Event.EventResponse>>
            ) {

            }
        })
    }

    inline fun goToEventPage(id: Int, action: (EventDetailData) -> Unit) {
        action.invoke(EventDetailData(id))
    }
}