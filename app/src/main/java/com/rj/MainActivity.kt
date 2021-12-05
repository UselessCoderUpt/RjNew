package com.rj

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.rj.databinding.ActivityMainBinding
import com.rj.models.*
import com.rj.ui.PawnViewModel
import com.rj.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainAdapter.OnPawnItemListener {

    /*       private val mainViewModel: MainViewModel by lazy {
           ViewModelProvider(this).get(MainViewModel::class.java)
       }*/

    @Inject
    lateinit var connectivityManager: ConnectivityManager
    private val pawnViewModel: PawnViewModel by lazy {
        ViewModelProvider(this).get(PawnViewModel::class.java)
    }
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var pawnItemsList: List<PawnItem>

    //    private var pawnItemsList: List<PawnItem> = arrayListOf()

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        val isNetworkAvailable = connectivityManager.isNetworkAvailable.value
        if (!isNetworkAvailable) {
            Snackbar.make(
                findViewById(android.R.id.content),
                "NO NETWORK AVAILABLE!!!",
                Snackbar.LENGTH_LONG
            ).show()
            /*Toast.makeText(
                applicationContext,
                "" + screenState.data?.size,
                Toast.LENGTH_LONG
            ).show()*/
        } else {
            setContentView(activityMainBinding.root)
            pawnViewModel.PawnItemsListLiveData.observe(this, Observer { screenState ->
                processPawnItemResponse(screenState)
                Toast.makeText(
                    applicationContext,
                    "RAMESH-JEWELERY" + screenState.data?.size,
                    Toast.LENGTH_LONG
                ).show()
            })
        }
    }

    override fun onPawnItemClick(position: Int) {
        if (pawnItemsList != null) {
            try {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel:" + pawnItemsList.get(position).MobileNo.toString()))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "DialError" + e.message, Toast.LENGTH_LONG)
                    .show()
                Log.d("rj", e.message.toString())
            }
        }
    }

    private fun processPawnItemResponse(screenState: ScreenState<List<PawnItem>?>) {
        activityMainBinding.progressBar.visibility = View.GONE
        when (screenState) {
            is ScreenState.Loading -> {
                activityMainBinding.progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                activityMainBinding.progressBar.visibility = View.GONE
                if (screenState.data != null) {
                    //assign in pawnItemList for itemclick listener
                    pawnItemsList = screenState.data
                    mainAdapter = MainAdapter(screenState.data, this@MainActivity)
                    activityMainBinding.rvPawnItems.adapter = mainAdapter
                }
            }
            is ScreenState.Error -> {
                activityMainBinding.progressBar.visibility = View.GONE
                val pbParentView = activityMainBinding.progressBar.rootView
                Snackbar.make(
                    pbParentView,
                    "rjSNACKBAR" + screenState.message!!,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }


    }

    /*private fun fetchJsonRequest() {
        val client = ApiClient.apiService.fetchPawnItems()

        client.enqueue(object: retrofit2.Callback<PawnItemResponse> {
            //on successful response
            override fun onResponse(
                call: Call<PawnItemResponse>,
                response: Response<PawnItemResponse>
            ) {
                if(response.isSuccessful){
                    Log.d("PawnItemResponse", ""+response.body())
                    pawnsItemList = response.body()!!.result
                    pawnsItemList?.let{
                        // TODO: if pawnlist is not null do some work
                        mainAdapter = MainAdapter(pawnsItemList, this@MainActivity)
                        activityMainBinding.rvPawnItems.adapter = mainAdapter
                        Toast.makeText(applicationContext, "result NOT NULLL!!! size"+pawnItemsList.size, Toast.LENGTH_LONG).show()
                    }
                }
            }
            //on response failure
            override fun onFailure(call: Call<PawnItemResponse>, t: Throwable) {
                Log.e("PawnItemResponseFAILED", ""+t.message)
            }
        })

        //pawnLIST is not binding if adapter is placed outside the function

    }*/
}