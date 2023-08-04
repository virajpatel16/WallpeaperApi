package com.example.wallpeaperapi

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.wallpaperapi.PhotosItem
import java.lang.Exception

class WallpeaperAdepter(data: ArrayList<PhotosItem>) :RecyclerView.Adapter<WallpeaperAdepter.Wallpeaperholder>(){
var data  = data
    lateinit var context:Context
    class Wallpeaperholder(itemView: View): ViewHolder(itemView) {

        var img = itemView.findViewById<ImageView>(R.id.rcvImg)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WallpeaperAdepter.Wallpeaperholder {
       return Wallpeaperholder(LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_item,parent,false))

    }
    override fun getItemCount(): Int {
        return data.size!!


    }

    override fun onBindViewHolder(holder: WallpeaperAdepter.Wallpeaperholder, position: Int) {
Glide.with(holder.itemView.context).load(data.get(position).src?.portrait).into(holder.img)
        holder.itemView.setOnClickListener {
            var dialog=Dialog(holder.itemView.context)
            dialog.setContentView(R.layout.dialog_layout)
            var img = dialog.findViewById<ImageView>(R.id.imgDialog)
            var btn = dialog.findViewById<Button>(R.id.btnDialog)

            Glide.with(holder.itemView.context).load(data.get(position).src?.portrait).into(img)

            btn.setOnClickListener {
                Glide.with(context)
                    .asBitmap().load(data.get(position).src?.portrait)
                    .listener(object : RequestListener<Bitmap>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
return false
                        }

                        @SuppressLint("MissingPermission")
                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            val wallpeaperManger=WallpaperManager.getInstance(context)
                            try {

                                wallpeaperManger.setBitmap(resource)
                            }catch (e : Exception){
                                e.printStackTrace()
                            }
                            return false
                        }

                    }).submit()
            }
            dialog.show()
        }
    }




}