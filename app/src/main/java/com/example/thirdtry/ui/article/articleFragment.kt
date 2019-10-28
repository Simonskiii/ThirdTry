package com.example.thirdtry.ui.article

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thirdtry.R
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.article_fragment.*
import androidx.lifecycle.Observer
import android.os.Handler
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.thirdtry.databinding.ArticleItemsLayoutBinding


class articleFragment : Fragment() {
    companion object {
        fun newInstance() = articleFragment()
    }

    private lateinit var viewModel: ArticleViewModel
    private lateinit var binding: ArticleItemsLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        viewModel.lineData = MutableLiveData()
        viewModel.lineData.value = mutableListOf()
        viewModel.addData()
        line_recy_view.layoutManager = LinearLayoutManager(this.context)
        val adapter = articleItemsdbAdapter(viewModel.lineData.value!!)
        line_recy_view.adapter = adapter
        val nameObserver = Observer<MutableList<Article>> {
        }
        viewModel.lineData.observe(this, nameObserver)
//        line_recy_view.addItemDecoration(BottomPaddingDecoration(10))
        SRL.setOnRefreshListener {
            viewModel.lineData.value?.clear()
            viewModel.addData()
            adapter.notifyDataSetChanged()
            Handler().postDelayed({
                if (SRL.isRefreshing) {
                    SRL.isRefreshing = false
                }
            }, 750)
        }
        // TODO: Use the ViewModel
    }

//    private fun initRecyclerView() {
//        //获取RecyclerView
//        var mRecyclerView = view?.findViewById<RecyclerView>(R.id.line_recy_view)
//        //创建adapter
//        mRecyclerAdapter = articleItemsAdapter(this, )
//        //给RecyclerView设置adapter
//        mRecyclerView.setAdapter(mRecyclerAdapter)
//        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
//        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
//        mRecyclerView.setLayoutManager(
//            LinearLayoutManager(
//                activity,
//                LinearLayoutManager.VERTICAL,
//                false
//            )
//        )
//        //设置item的分割线
//        mRecyclerView.addItemDecoration(
//            DividerItemDecoration(
//                activity!!,
//                DividerItemDecoration.VERTICAL
//            )
//        )
//        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
//        mRecyclerAdapter.setOnItemClickListener(object :
//            CollectRecycleAdapter.OnItemClickListener() {
//            fun OnItemClick(view: View, data: GoodsEntity) {
//                //此处进行监听事件的业务处理
//                Toast.makeText(activity, "我是item", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}
