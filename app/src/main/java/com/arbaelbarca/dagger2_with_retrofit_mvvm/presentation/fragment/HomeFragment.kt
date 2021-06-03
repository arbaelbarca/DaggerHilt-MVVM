package com.arbaelbarca.dagger2_with_retrofit_mvvm.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.arbaelbarca.dagger2_with_retrofit_mvvm.R
import com.arbaelbarca.dagger2_with_retrofit_mvvm.adapter.AdapterUsersLoadState
import com.arbaelbarca.dagger2_with_retrofit_mvvm.adapter.AdapterUsersPaging
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ItemsItem
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.UiState
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.hideView
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.showView
import com.arbaelbarca.dagger2_with_retrofit_mvvm.viewmodel.ViewModelMain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val viewmodelMain: ViewModelMain by viewModels()

    val adapterSearchUsersPaging = AdapterUsersPaging()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial()
    }


    private fun initial() {

        rvListUser.apply {
            adapter = adapterSearchUsersPaging.withLoadStateFooter(
                footer = AdapterUsersLoadState { adapterSearchUsersPaging.retry() }
            )
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
        }


        lifecycleScope.launch {
            viewmodelMain.observerItems().observe(viewLifecycleOwner, Observer {
                val dataItem = it
                adapterSearchUsersPaging.submitData(viewLifecycleOwner.lifecycle, dataItem)
                hideView(progressList)
            })
        }

//        viewmodelMain.getListUser()
//        observerData()
    }

    private fun observerData() {
        viewmodelMain.observerUsers().observe(viewLifecycleOwner, Observer {
            when (it) {
                is UiState.Loading -> {
                    showView(progressList)
                }
                is UiState.Success -> {
                    hideView(progressList)
                    val dataItem = it.data
//                    adapterSearchUsersPaging.submitData(viewLifecycleOwner.lifecycle, dataItem)
                }
                is UiState.Failure -> {
                    hideView(progressList)
                    Toast.makeText(
                        requireContext(),
                        "Error ${it.throwable.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun initAdapter(dataItem: MutableList<ItemsItem?>) {
//        val groupieAdapter = GroupAdapter<GroupieViewHolder>().apply {
//            dataItem.forEach {
//                it?.let { it1 -> AdapterUsers(it1, this@MainActivity) }?.let { it2 -> add(it2) }
//            }
//        }
//        setRvAdapterVertikal(rvListUser, groupieAdapter)


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}