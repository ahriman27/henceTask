package com.example.hencetest

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.hencetest.databinding.ActivityMainBinding
import com.google.android.gms.maps.model.Marker


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var list = arrayListOf<MarkerModel>()
    var markerList = arrayListOf<Marker?>()
    var mapFragment = MapFragment(list)
    var listFragment = ListFragment(list)
    var loginFragment = LoginFragment()
    lateinit var listInter : Notify
    lateinit var mapInter : Notify

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.apply {
            showHideFrag(mapFragment)
            bottomNavigationView.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.map->showHideFrag(mapFragment)
                    R.id.list-> {
                        showHideFrag(listFragment)
                        Handler().postDelayed(Runnable { listInter.onEvent(0) },1000)

                    }
                    R.id.login->showHideFrag(loginFragment)
                }
                true
            }
        }

    }

    private fun showHideFrag(showFragment: Fragment) {

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()

        for (frag in supportFragmentManager.fragments) {
            ft.hide(frag!!)
        }

        if (showFragment.isAdded) {
            ft.show(showFragment)
        } else {
            ft.add(R.id.flFragment, showFragment)
        }

        ft.commit()
    }

}