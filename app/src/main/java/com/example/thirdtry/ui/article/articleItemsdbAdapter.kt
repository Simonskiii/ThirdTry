package com.example.thirdtry.ui.article
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thirdtry.R
import com.example.thirdtry.databinding.ArticleItemsLayoutBinding
import androidx.databinding.ViewDataBinding


abstract class BaseAdapter<T, B : ViewDataBinding>(private var items: MutableList<T>, private var layoutRes: Int) : RecyclerView.Adapter<BaseAdapter<T, B>.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<B>(inflater, layoutRes, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setNewData(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
    }

    inner class ViewHolder(private val binding: B) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            bindItem(binding, item)
            binding.executePendingBindings()
        }
    }

    abstract fun bindItem(binding: B, item: T)
}


class articleItemsdbAdapter(items: MutableList<Article>) : BaseAdapter<Article, ArticleItemsLayoutBinding>(items, R.layout.article_items_layout) {
    override fun bindItem(binding: ArticleItemsLayoutBinding, item: Article) {
        binding.data = item
    }
}



//class articleItemsdbAdapter constructor(context: Context?, d:MutableList<Article>?):
//    RecyclerView.Adapter<articleItemsdbAdapter.DataBindingViewHolder>() {
//
//
//    private var mContext:Context? = context
//    private var data: MutableList<Article>? = d
//
//    inner class DataBindingViewHolder(var dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {
//        val name: TextView = itemView.findViewById(R.id.tv_recy_item_1_name)
//        val img: ImageView = itemView.findViewById(R.id.img_recy_item_1_pic)
//        val desc: TextView = itemView.findViewById(R.id.tv_recy_item_1_desc)
//        private var binding : ArticleItemsLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.article_items_layout, parent, false)
//
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): DataBindingViewHolder {
//        var binding : ArticleItemsLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.article_items_layout, parent, false)
//        return DataBindingViewHolder(binding)
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getItemCount(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//
//}