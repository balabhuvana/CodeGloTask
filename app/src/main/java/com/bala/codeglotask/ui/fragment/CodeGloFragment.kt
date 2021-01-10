package com.bala.codeglotask.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bala.codeglotask.R
import com.bala.codeglotask.model.BreedResponseModel
import com.bala.codeglotask.model.HeaderItem
import com.bala.codeglotask.model.RecyclerViewItem
import com.bala.codeglotask.model.SubItem
import com.bala.codeglotask.ui.activity.BaseApplication
import com.bala.codeglotask.ui.adapter.CustomAdapter
import com.bala.codeglotask.utils.CommonUtils
import com.bala.codeglotask.viewmodel.CodeGloViewModel
import kotlinx.android.synthetic.main.fragment_code_glo.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class CodeGloFragment : Fragment() {

    @Inject
    lateinit var codeGloViewModel: CodeGloViewModel

    private var linearLayoutManager: LinearLayoutManager? = null

    private var listItemMutableLiveData: MutableLiveData<ArrayList<RecyclerViewItem>>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity?.application as BaseApplication).appComponent
            ?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_code_glo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDogBreedList()
    }

    private fun loadDogBreedList() {
        codeGloViewModel.getDogBreedListViewModel()

        observeRegisterResponse()
    }

    private fun observeRegisterResponse() {
        CommonUtils.showHideView(progressBar, true)
        codeGloViewModel.observeDogBreedList()
            ?.observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer { breedResponseModel: BreedResponseModel? ->
                    when (breedResponseModel?.success) {
                        true -> {
                            updateDogBreedUI(splitData(breedResponseModel))
                        }
                        false -> {
                            context?.let {
                                CommonUtils.showToastMessage(
                                    it,
                                    getString(R.string.error_info)
                                )
                            }
                        }
                    }
                    CommonUtils.showHideView(progressBar, false)
                })
    }

    private fun splitData(breedResponseModel: BreedResponseModel): HashMap<String, ArrayList<String>> {
        val breedList: List<String>? = breedResponseModel.breedList?.toList()
        var breedNameList: java.util.ArrayList<String>?
        val breedHashMap: HashMap<String, ArrayList<String>> = HashMap()

        if (breedList != null) {
            for (name: String in breedList) {
                val key: String = name[0].toString().toUpperCase(Locale.getDefault())
                when {
                    breedHashMap.containsKey(key) -> {
                        breedNameList = breedHashMap[key]
                        breedNameList!!.add(name)
                        breedHashMap[key] = breedNameList
                    }
                    else -> {
                        breedNameList = ArrayList()
                        breedNameList.add(name)
                        breedHashMap[key] = breedNameList
                    }
                }
            }
        }
        return breedHashMap
    }


    private fun updateDogBreedUI(hashMap: HashMap<String, ArrayList<String>>) {
        listItemMutableLiveData = MutableLiveData()
        val listItemData = ArrayList<RecyclerViewItem>()
        hashMap.forEach { (key: String, value: java.util.ArrayList<String>) ->
            val headerItem = HeaderItem(key)
            listItemData.add(headerItem)
            value.forEach {
                val subItem = SubItem(it)
                listItemData.add(subItem)
            }
        }
        listItemMutableLiveData!!.postValue(listItemData)
        listItemMutableLiveData!!.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            linearLayoutManager = LinearLayoutManager(context)
            val adapter =
                CustomAdapter(it)
            myRecyclerView!!.layoutManager = linearLayoutManager
            myRecyclerView!!.adapter = adapter
        })
    }
}