package com.code.practice.ui.adapter

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.code.practice.R
import com.code.practice.database.enities.UserName
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UserNameAdapter : ListAdapter<UserName, RecyclerView.ViewHolder>(USERNAME_COMPARATOR) {

    var callback: (username: UserName) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_username,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindView(getItem(position),callback)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(userName: UserName,callback: (username: UserName)-> Unit?) {
            itemView.findViewById<TextView>(R.id.text_name).text = userName.userName

            itemView.setOnClickListener {
                showDelete(itemView.context,userName,callback = callback)
            }
        }

        fun showDelete(context:Context,userName: UserName, callback: (username: UserName)-> Unit?){
          var dialog =  MaterialAlertDialogBuilder(context).setTitle("是否删除该条信息？")
                .setNegativeButton("否",DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                .setPositiveButton("是",DialogInterface.OnClickListener { dialog, which ->
                    callback?.invoke(userName)
                    dialog.dismiss()
                })
                .create()
            dialog.show()
        }
    }

    companion object {
        private val USERNAME_COMPARATOR = object : DiffUtil.ItemCallback<UserName>() {
            override fun areItemsTheSame(oldItem: UserName, newItem: UserName): Boolean {
                return oldItem.userName == newItem.userName
            }

            override fun areContentsTheSame(oldItem: UserName, newItem: UserName): Boolean {
                return oldItem.userName == newItem.userName
            }
        }
    }

}