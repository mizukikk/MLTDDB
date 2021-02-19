package com.mizukikk.mltd.main.event.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mizukikk.mltd.R
import com.mizukikk.mltd.databinding.ItemEventBorderBinding
import com.mizukikk.mltd.databinding.ItemPointBinding
import com.mizukikk.mltd.main.event.model.EventBorder
import com.mizukikk.mltd.ui.recyclerview.BaseViewHolder
import java.text.NumberFormat

class EventBorderAdapter(
    private var lastPointList: List<EventBorder>,
    private val inProgress: Boolean
) :
    RecyclerView.Adapter<EventBorderAdapter.EventBorderHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventBorderHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventBorderHolder(ItemEventBorderBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: EventBorderHolder, position: Int) {
        holder.bindData(lastPointList[position])
    }

    override fun getItemCount() = lastPointList.size

    inner class EventBorderHolder(binding: ViewDataBinding) :
        BaseViewHolder<ItemEventBorderBinding>(binding) {
        fun bindData(data: EventBorder) {
            setTitle(data)
            setBorders(data)
            setIdolData(data)
        }

        private fun setIdolData(data: EventBorder) {
            data.idolData?.let {
                binding.data = it
            }
        }

        private fun setBorders(data: EventBorder) {
            data.borderList.filter { it.rank <= 50000 }.forEach {
                val pointBinding =
                    ItemPointBinding.inflate(LayoutInflater.from(binding.root.context))
                pointBinding.tvNo.text =
                    getString(R.string.item_last_point_rank).format(it.rank)
                pointBinding.tvScore.text = NumberFormat.getNumberInstance().format(it.score)
                binding.llBorders.addView(pointBinding.root)
            }
        }

        private fun setTitle(data: EventBorder) {
            binding.tvTitle.text = data.title
            if (inProgress) {
                binding.tvUpdate.text =
                    getString(R.string.item_last_point_update).format(data.updateDate)
            }
        }
    }

}