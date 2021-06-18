package com.example.samplelivedata

import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.samplelivedata.databinding.ActivitySampleBinding

class SampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sample)
        val viewModel= makeApiCall()
        setupBinding(viewModel)
    }
    fun setupBinding(viewModel: SampleActivityViewModel) {
        val activitySampleBinding:ActivitySampleBinding= DataBindingUtil.setContentView(this,R.layout.activity_sample)
        activitySampleBinding.setVariable(BR.viewModel,viewModel)
        activitySampleBinding.executePendingBindings()
        activitySampleBinding.recylerView.apply{
            layoutManager =LinearLayoutManager(this@SampleActivity)
           /* var decoration =DividerItemDecoration(this@SampleActivity, VERTICAL)
            addItemDecoration(decoration)*/
        }
    }
    fun makeApiCall():SampleActivityViewModel{
       val viewmodel = ViewModelProviders.of(this).get(SampleActivityViewModel::class.java)
        viewmodel.getRecylerListDataObsever().observe(this, Observer<RecylerList>  {
            if(it!=null){
                //update adapter
                viewmodel.setAdapeter(it.items)
            }else{
                Toast.makeText(this,"Error in fetching data",Toast.LENGTH_SHORT).show()
            }
        })
        viewmodel.makeAPICall("newyork")
        return viewmodel
    }
}