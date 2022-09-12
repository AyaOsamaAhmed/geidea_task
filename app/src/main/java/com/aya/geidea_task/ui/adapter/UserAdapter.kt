package com.aya.geidea_task.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aya.geidea_task.domain.model.User
import com.aya.geidea_task.ui.interfaces.onClick
import com.aya.geidea_task.R
import com.aya.geidea_task.BR
import com.aya.geidea_task.databinding.ItemUserBinding

class UserAdapter (private val models : List<User>,
                   private val onClick : onClick
):
    RecyclerView.Adapter<UserAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemUserBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = models[position]
          holder.bind(model)

        holder.itemRowBinding.details.setOnClickListener {
            onClick.onClickDetails(model.id!!)
        }
    }
    override fun getItemCount(): Int {
        return if (models.isNotEmpty()) models.size else 0
    }


    class ViewHolder(binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemRowBinding: ItemUserBinding = binding
        fun bind(obj: Any?) {
            itemRowBinding.setVariable(BR.model, obj)
            itemRowBinding.executePendingBindings()
        }
    }

}