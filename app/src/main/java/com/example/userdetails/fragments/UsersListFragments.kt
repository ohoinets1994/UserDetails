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
import com.example.userdetails.extentions.scrollListener
import com.example.userdetails.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.fragment_users_list.*

class UsersListFragments : Fragment() {
    private lateinit var viewModel: UserViewModel
    private val adapter = UsersAdapter()
    private var nextPage: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_users_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvUsersList.layoutManager = LinearLayoutManager(context)
        rvUsersList.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        viewModel.apply {
            srlCharactersList.setOnRefreshListener { loadUsers() }
            evUsersList.onRetryClicked = { loadUsers() }

            observe(users) {
                rvUsersList.isVisible = true
                adapter.users = it
                evUsersList.hideError()
            }
            observe(info) { nextPage = it.page }
            observe(loading) { evUsersList.setLoading(srlCharactersList, it) }
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

        rvUsersList.scrollListener { nextPage?.let { viewModel.loadNextPageUsers(it) } }
    }
}