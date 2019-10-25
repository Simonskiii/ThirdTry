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

import kotlin.collections.HashMap
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Rect
import androidx.lifecycle.Observer
import kotlin.random.Random
import androidx.core.os.HandlerCompat.postDelayed
import android.text.method.TextKeyListener.clear
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.core.os.HandlerCompat.postDelayed
import android.os.Handler
import androidx.lifecycle.MutableLiveData


class articleFragment : Fragment() {
    companion object {
        fun newInstance() = articleFragment()
    }

    private lateinit var viewModel: ArticleViewModel

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
        val adapter = articleItemsAdapter(this.context, viewModel.lineData.value)
        line_recy_view.adapter = adapter
        val nameObserver = Observer<MutableList<Map<String, Any>>> {
            //            tv.text = viewModel.lineData.value!!.get(1).values.toString()
            adapter.notifyDataSetChanged()
        }
        viewModel.lineData.observe(this, nameObserver)
//        line_recy_view.addItemDecoration(BottomPaddingDecoration(10))
        SRL.setOnRefreshListener {
            //            viewModel.lineData.value?.clear()
//            viewModel.addData()

            val data = mutableListOf<Map<String, Any>>()
            for (i in 0..29) {
                val random = Random
                val n = random.nextInt(viewModel.pics.size)
                val map : MutableMap<String, Any>? = HashMap()
                map?.set("pic", viewModel.pics[n])
                map?.set("name", viewModel.names[n])
                map?.set("desc", "我是一只" + viewModel.names[n])
                data.add(map as HashMap<String, Any>)
            }
            viewModel.lineData.value = data

            Handler().postDelayed({
                if (SRL.isRefreshing) {
                    SRL.isRefreshing = false
                }
            }, 1000)
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
