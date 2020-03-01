package com.example.userdetails.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userdetails.R
import com.example.userdetails.activities.UserDetailsActivity
import com.example.userdetails.extentions.fullName
import com.example.userdetails.extentions.load
import com.example.userdetails.model.User
import kotlinx.android.synthetic.main.user_layout.view.*

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    var users: List<User> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) =
        holder.bindUser(users[position])

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindUser(user: User) {
            itemView.apply {
                imgAvatar.load(user.picture.large)
                tvName.text = fullName(user.name.first, user.name.last)

                setOnClickListener { startUserDetailsActivity(user) }
            }
        }

        private fun startUserDetailsActivity(user: User) {
            itemView.context.startActivity(UserDetailsActivity.newIntent(itemView.context, user))
        }
    }
}