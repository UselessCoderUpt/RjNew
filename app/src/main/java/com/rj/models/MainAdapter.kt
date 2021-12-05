package com.rj.models

import android.util.Log
import com.rj.databinding.LayoutRvItemsBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(val pawnItemsList: List<PawnItem>, onPawnItemListener: OnPawnItemListener):
    RecyclerView.Adapter<MainAdapter.RvViewHolder>() {

    private val mOnPawnItemListener: OnPawnItemListener

    init{
        mOnPawnItemListener = onPawnItemListener
    }

    inner class RvViewHolder(private val layoutRvItemsBinding: LayoutRvItemsBinding, private val onPawnItemListener: OnPawnItemListener):
        RecyclerView.ViewHolder(layoutRvItemsBinding.root),
        View.OnClickListener {

        private val mOnPawnItemListener: OnPawnItemListener

        init {
            mOnPawnItemListener = onPawnItemListener
            layoutRvItemsBinding.root.setOnClickListener(this)
        }

        fun bindData(pawnItem: PawnItem){
            layoutRvItemsBinding.tvLoanNo.text = pawnItem.LoanNo.toString()
            layoutRvItemsBinding.tvCustomerName.text = pawnItem.CustomerName
            layoutRvItemsBinding.tvPlace.text = pawnItem.Place
            layoutRvItemsBinding.tvMobileNo.text = pawnItem.MobileNo.toString()
            layoutRvItemsBinding.tvItemType.text = pawnItem.ItemType
            layoutRvItemsBinding.tvItemName.text = pawnItem.ItemName
            layoutRvItemsBinding.tvItemWeight.text = pawnItem.Weight.toString()
            layoutRvItemsBinding.tvLoanAmount.text = pawnItem.Amount.toString()
            layoutRvItemsBinding.tvRenewInterest.text = pawnItem.RenewInterest.toString()
            layoutRvItemsBinding.tvTotalAmount.text = pawnItem.TotalAmount.toString()
        }

        override fun onClick(v: View?) {
            mOnPawnItemListener.onPawnItemClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(LayoutRvItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false), mOnPawnItemListener )
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bindData(pawnItemsList[position])
    }

    override fun getItemCount(): Int {
        return pawnItemsList.size
    }

    public interface OnPawnItemListener{
        fun onPawnItemClick(position: Int)
    }
}
