package be.timdesmet.hogent_mobielplus_project.ui.adapters.shop

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.timdesmet.hogent_mobielplus_project.databinding.GroupShopProductContentBinding
import be.timdesmet.hogent_mobielplus_project.domain.shop.product.Product
import com.squareup.picasso.Picasso

class GroupProductListAdapter(val clickListener: GroupProductListListener) : ListAdapter<Product, GroupProductListAdapter.ViewHolder>(GroupProductDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: GroupShopProductContentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Product, clickListener: GroupProductListListener) {
            binding.product = item
            binding.productDescription.text = Html.fromHtml(item.description)
            val displayPrice = item.pricing.getDisplayPrice("eur")
            binding.productPrice.text = displayPrice.formatRecurring() + "/" + displayPrice.cycle.getShortened()
            binding.clickListener = clickListener
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GroupShopProductContentBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class GroupProductDiffCallback: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.pid == newItem.pid
    }
    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.pid == newItem.pid
    }
}

class GroupProductListListener(val _viewProduct: (product: Product) -> Unit) {
    fun viewProduct(product: Product) = _viewProduct(product)
}