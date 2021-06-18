package com.example.samplelivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SampleActivityViewModel:ViewModel() {
    lateinit var recylerListData:MutableLiveData<RecylerList>
    lateinit var recylerviewAdapter: RecylerviewAdapter
    init {
     recylerListData= MutableLiveData()
        recylerviewAdapter= RecylerviewAdapter()
    }
    fun getAdapter():RecylerviewAdapter{
        return  recylerviewAdapter
    }
    fun setAdapeter(data:ArrayList<RecylerData>){
      recylerviewAdapter.setDataList(data)
        recylerviewAdapter.notifyDataSetChanged()
    }
    fun getRecylerListDataObsever():MutableLiveData<RecylerList>{
        return  recylerListData
    }
    fun makeAPICall(input:String){
        var retroInstance=  RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        var call=   retroInstance.getDataFromAPI(input)
        call.enqueue(object:Callback<RecylerList>{
            override fun onFailure(call: Call<RecylerList>, t: Throwable) {
                recylerListData.postValue(null)
            }

            override fun onResponse(call: Call<RecylerList>, response: Response<RecylerList>) {
               if (response.isSuccessful){
                   recylerListData.postValue(response.body())
               }else{
                   recylerListData.postValue(null)
               }
            }
        })
    }

}