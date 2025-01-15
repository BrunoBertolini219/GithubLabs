package com.brunoccbertolini.github.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.brunoccbertolini.github.R
import com.brunoccbertolini.base.R as BaseR

fun Context.openPullRequestWeb(url: String) {
    try {
        startActivity(
            Intent().apply {
                data = Uri.parse(url)
                action = Intent.ACTION_VIEW
            }
        )
    } catch (_: Exception) {
        Toast.makeText(this, R.string.repo_pr_open_web_error, Toast.LENGTH_SHORT).show()
    }
}

fun Context.showDialog(
    title: String?,
    msg: String?,
    action: (() -> Unit)? = null,
    positiveText: Int = R.string.repo_pr_open_request_main_btn,
    dismiss: (() -> Unit)? = null,
    dismissText: Int = R.string.repo_pr_open_request_dismiss_btn
) {
    AlertDialog.Builder(this).run {
        setTitle(title)
        setMessage(msg)
        setPositiveButton(getString(positiveText)) { _, _ -> action?.invoke() }
        setNegativeButton(getString(dismissText)) { _, _ -> dismiss?.invoke() }
    }.create().show()
}
