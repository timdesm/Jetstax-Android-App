package be.timdesmet.hogent_mobielplus_project.ui.adapters.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.timdesmet.hogent_mobielplus_project.databinding.HomeShopGroupContentBinding
import be.timdesmet.hogent_mobielplus_project.domain.shop.ProductGroup
import com.squareup.picasso.Picasso

class HomeProductGroupListAdapter(val clickListener: HomeProductGroupListListener) : ListAdapter<ProductGroup, HomeProductGroupListAdapter.ViewHolder>(HomeProductGroupDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: HomeShopGroupContentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ProductGroup, clickListener: HomeProductGroupListListener) {
            binding.group = item
            binding.clickListener = clickListener
            Picasso.get().load(item.image).into(binding.groupImage)
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HomeShopGroupContentBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class HomeProductGroupDiffCallback: DiffUtil.ItemCallback<ProductGroup>() {
    override fun areItemsTheSame(oldItem: ProductGroup, newItem: ProductGroup): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: ProductGroup, newItem: ProductGroup): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.image == newItem.image
    }
}

class HomeProductGroupListListener(val _viewGroup: (group: ProductGroup) -> Unit) {
    fun viewGroup(group: ProductGroup) = _viewGroup(group)
}