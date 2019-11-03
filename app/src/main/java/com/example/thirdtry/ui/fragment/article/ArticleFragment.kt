package com.example.thirdtry.ui.fragment.article

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
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.thirdtry.databinding.ArticleItemsLayoutBinding
import com.example.thirdtry.ui.base.BaseFragment
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ArticleFragment : BaseFragment() {

    private lateinit var viewModel: ArticleViewModel
    private lateinit var binding: ArticleItemsLayoutBinding

    override fun getLayoutId(): Int = R.layout.article_fragment

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        viewModel.lineData = MutableLiveData()
        viewModel.lineData.value = mutableListOf()
        viewModel.addData()
        val nameObserver = Observer<MutableList<Article>> {
        }
        viewModel.lineData.observe(this, nameObserver)
    }

    override fun initView() {
        line_recy_view.layoutManager = LinearLayoutManager(this.context)
        val adapter = ArticleBindingAdapter(viewModel.lineData.value!!)
        line_recy_view.adapter = adapter
        SRL.setOnRefreshListener {
            viewModel.lineData.value?.clear()
            viewModel.addData()
            adapter.notifyDataSetChanged()
            //下拉刷新图标持续时间
            Handler().postDelayed({
                if (SRL.isRefreshing) {
                    SRL.isRefreshing = false
                }
            }, 750)
            val retorfit = Retrofit.Builder()
                .baseUrl("https://api.apiopen.top/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = retorfit.create(Api::class.java)
            val a = api.category("28654780")
            a.enqueue(object : Callback<article1>{
                override fun onResponse(call: Call<article1>, response: Response<article1>) {
                    response.let{ it ->
                        Log.d("god***************", it.raw().toString())
                        it.body()?.let {
                            Log.d("Weather", it.toString())
//                        Log.d("Weather", it.comment)
                        }
                    }
                }
                override fun onFailure(call: Call<article1>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }
    }

    override fun lazyLoad() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface Api {
        @GET("getSingleJoke")
        fun category(@Query("sid") sid: String): Call<article1>
//                     @Query("count") count: Int,
//                     @Query("type") type: String) : Call<article1>
    }
}


class ArticleFragment1 : Fragment() {

    private lateinit var viewModel: ArticleViewModel
    private lateinit var binding: ArticleItemsLayoutBinding


    companion object {
        fun newInstance() = ArticleFragment()
    }

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
        val adapter = ArticleBindingAdapter(viewModel.lineData.value!!)
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
        val retorfit = Retrofit.Builder()
            .baseUrl("https://api.apiopen.top/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retorfit.create(Api::class.java)
        val a = api.category("28654780")
        a.enqueue(object : Callback<article1>{
            override fun onResponse(call: Call<article1>, response: Response<article1>) {
                response.let{ it ->
                    Log.d("god***************", it.raw().toString())
                    it.body()?.let {
                        Log.d("Weather", it.result.uid.toString())
//                        Log.d("Weather", it.comment)
                    }
                }
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onFailure(call: Call<article1>, t: Throwable) {

                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        // TODO: Use the ViewModel
    }
    interface Api {
        @GET("getSingleJoke")
        fun category(@Query("sid") sid: String): Call<article1>
//                     @Query("count") count: Int,
//                     @Query("type") type: String) : Call<article1>
    }
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

