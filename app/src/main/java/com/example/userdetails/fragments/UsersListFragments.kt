package com.example.userdetails.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userdetails.R
import com.example.userdetails.adapters.UsersAdapter
import com.example.userdetails.extentions.observe
import com.example.userdetails.model.User
import com.example.userdetails.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_users_list.*

class UsersListFragments : Fragment() {
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_users_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        viewModel.apply {
            observe(users) { initRecyclerView(it) }
            observe(loading) { evUsersList.setLoading(it) }
            observe(error) {
                it?.let { error ->
                    rvUsersList.isVisible = false
                    evUsersList.setError(error)
                } ?: run {
                    rvUsersList.isVisible = true
                    evUsersList.hideError()
                }
            }

            loadUsers()
        }
    }

    private fun initRecyclerView(users: List<User>) {
        rvUsersList.layoutManager = LinearLayoutManager(context)
        rvUsersList.adapter = UsersAdapter(users)
    }
}