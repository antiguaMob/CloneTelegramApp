package com.antigua.mytelegram.ui.screens.groups

import androidx.recyclerview.widget.RecyclerView
import com.antigua.mytelegram.R
import com.antigua.mytelegram.models.CommonModel
import com.antigua.mytelegram.ui.screens.base.BaseFragment
import com.antigua.mytelegram.utilits.*
import kotlinx.android.synthetic.main.fragment_create_group.*

class CreateGroupFragment(private var listContacts: List<CommonModel>): BaseFragment(R.layout.fragment_create_group) {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AddContactsAdapter

    override fun onResume() {
        super.onResume()
        AppConstants.APP_ACTIVITY.title = getString(R.string.create_group)
        AppConstants.APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
        initRecyclerView()
        create_group_btn_complete.setOnClickListener {
            showToast("Click")
        }
        crete_group_input_name.requestFocus()
//        add_contacts_btn_next.setOnClickListener {
//            AddContactsFragment.listContacts.forEach{
//                replaceFragment(CreateGroupFragment(AddContactsFragment.listContacts))
//            }
//        }
    }

    private fun initRecyclerView() {
        mRecyclerView = create_group_recycle_view
        mAdapter = AddContactsAdapter()


        mRecyclerView.adapter = mAdapter
        listContacts.forEach {
            mAdapter.updateListItems(it)
        }

    }

}