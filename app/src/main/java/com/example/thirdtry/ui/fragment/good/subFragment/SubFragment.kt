package com.example.thirdtry.ui.fragment.good.subFragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.thirdtry.R
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.base.BaseFragment
import com.example.thirdtry.model.Good
import com.example.thirdtry.ui.fragment.good.GoodBindingAdapter
import kotlinx.android.synthetic.main.sub_fragment_fragment.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "category"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [subFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [subFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class SubFragment : BaseFragment() {
    private lateinit var viewModel: SubFragmentViewModel
    private var goods = mutableListOf<Good>()
    private val adapter: GoodBindingAdapter by lazy {
        GoodBindingAdapter(goods)
    }

    // TODO: Rename and change types of parameters
    private var category: String? = null
    //    private var listener: OnFragmentInteractionListener? = null
    override fun getLayoutId(): Int = R.layout.sub_fragment_fragment

    private val mObserver: Observer<BaseDataResult<MutableList<Good>>> by lazy {
        Observer<BaseDataResult<MutableList<Good>>> {
            if (it == null){
                Toast.makeText(activity, "无网络连接", Toast.LENGTH_SHORT).show()
                return@Observer
            }
            else{
                if (goods.isEmpty()) {
                    this.goods.addAll(it.subjects)
                    adapter.notifyDataSetChanged()
                    spin_kit.visibility = View.GONE
                }
                else{
                    this.goods.clear()
                    this.goods.addAll(it.subjects)
                    adapter.notifyDataSetChanged()
//                    this.goods.addAll(it.subjects)
//                    val a = this.goods
//                    adapter.setNewData(this.goods)

                }
            }
        }
    }

    override fun initView() {
        arguments?.let {
            category = it.getString(ARG_PARAM1)
        }
        spin_kit.visibility = View.VISIBLE
        line_recy_view.layoutManager = LinearLayoutManager(this.context)
        line_recy_view.adapter = adapter
        tv1.text = category
        SRL.setOnRefreshListener {
            viewModel.getGoods(token).observe(this, mObserver)
            //下拉刷新图标持续时间
            Handler().postDelayed({
                if (SRL.isRefreshing) {
                    SRL.isRefreshing = false
                }
            }, 750)
        }
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this.activity!!).get(SubFragmentViewModel::class.java)
        viewModel.getGoods(token).observe(this, mObserver)
    }




    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri)
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param category Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment subFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(category: String) =
            SubFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, category)
                }
            }
    }
}