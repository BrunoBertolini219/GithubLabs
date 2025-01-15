package com.brunoccbertolini.github.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brunoccbertolini.github.databinding.FragmentRepoPrListBinding
import com.brunoccbertolini.github.databinding.ItemJavaRepositoryBinding
import com.brunoccbertolini.github.databinding.ItemRepoPrBinding
import com.brunoccbertolini.github.domain.entity.GithubRepoItemModel
import com.brunoccbertolini.github.domain.entity.RepositoryPrItemModel
import com.bumptech.glide.Glide

class RepoPrListAdapter :
    RecyclerView.Adapter<RepoPrListAdapter.ViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<RepositoryPrItemModel>() {
        override fun areItemsTheSame(
            oldItem: RepositoryPrItemModel,
            newItem: RepositoryPrItemModel
        ): Boolean {
            return oldItem.pullRequestUrl == newItem.pullRequestUrl
        }

        override fun areContentsTheSame(
            oldItem: RepositoryPrItemModel,
            newItem: RepositoryPrItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(
        this,
        differCallback
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemRepoPrBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bindView(differ.currentList[position])
    }

    private var onRepoItemClickListener: ((RepositoryPrItemModel) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemRepoPrBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(repo: RepositoryPrItemModel) = binding.run {
            repoDetailDataInclude.apply {
                repoTitleTv.text = repo.pullRequestName
                repoDescriptionTv.text = repo.pullRequestDescription
            }
            repoDetailUsernameInclude.apply {
                repoUserNameTv.text = repo.user.username
                Glide.with(itemView.context)
                    .load(repo.user.userAvatar)
                    .into(repoUserAvatarIv)
            }
            root.setOnClickListener {
                onRepoItemClickListener?.let { it(repo) }
            }
        }
    }

    fun setOnRepoItemClickListener(listener: (RepositoryPrItemModel) -> Unit) {
        onRepoItemClickListener = listener
    }
}