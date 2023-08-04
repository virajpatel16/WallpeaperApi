package com.example.wallpeaperapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaperapi.PhotosItem
import com.example.wallpaperapi.WallpaperModel
import com.example.wallpeaperapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var data=ArrayList<PhotosItem>()
    lateinit var serch:String
    lateinit var binding : ActivityMainBinding
    lateinit var adapter : WallpeaperAdepter
    var page=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
inintview()

    }

     fun inintview() {
        binding.imgBtn.setOnClickListener {
            serch=binding.edtSearch.text.toString()
            data.clear()
            loadWallpeaper(serch,page)
        }
         binding.nestedScr.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
             if (scrollY == v.getChildAt(0).measuredHeight  - v.measuredHeight){
                 page++
                 binding.progress.visibility = View.VISIBLE
                 loadWallpeaper(serch,page)
             }
         })

    }

    private fun loadWallpeaper(search : String , page1 : Int) {
        var apiinterface= Apiclint.getApiclint().create(Apiinterface::class.java)
        apiinterface.getdata(
            serch,
            page1,
            "AiE8mEHipmRX28hFBB1WfEyGAsQiQxNTovdKHZCVEf7pmeZSzHsq4PvM"
        ).enqueue(object :Callback<WallpaperModel>{
            override fun onResponse(
                call: Call<WallpaperModel>,
                response: Response<WallpaperModel>
            ) {
                data.addAll(response.body()?.photos as ArrayList<PhotosItem>)
adapter=WallpeaperAdepter(data)
                binding.recycler.layoutManager=GridLayoutManager(this@MainActivity,3)
                    binding.recycler.adapter=adapter
            }

            override fun onFailure(call: Call<WallpaperModel>, t: Throwable) {
                }

        })

    }
}