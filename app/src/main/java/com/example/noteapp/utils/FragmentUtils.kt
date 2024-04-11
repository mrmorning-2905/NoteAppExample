package com.example.noteapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.example.noteapp.R

object FragmentUtils {

    fun replaceFragment(activity: FragmentActivity, fragment: Fragment, needAddToStack: Boolean = false) {
        activity.supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            setReorderingAllowed(true)
            if (needAddToStack) {
                addToBackStack(fragment::class.simpleName)
            }
        }
    }
}