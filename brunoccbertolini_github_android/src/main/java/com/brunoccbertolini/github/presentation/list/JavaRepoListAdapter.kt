package com.brunoccbertolini.github.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brunoccbertolini.github.databinding.ItemJavaRepositoryBinding
import com.brunoccbertolini.github.domain.entity.GithubRepoItemModel
import com.bumptech.glide.Glide

class JavaRepoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<GithubRepoItemModel>() {

        override fun areItemsTheSame(
            oldItem: GithubRepoItemModel,
            newItem: GithubRepoItemModel
        ): Boolean {
            return oldItem.repositoryFullName == newItem.repositoryFullName
        }

        override fun areContentsTheSame(
            oldItem: GithubRepoItemModel,
            newItem: GithubRepoItemModel
        ): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(
        this,
        differCallback
    )
    private val currentList = differ.currentList.toMutableList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemJavaRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if(holder is ViewHolder) holder.bindView(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    private var onRepoItemClickListener: ((GithubRepoItemModel) -> Unit)? = null

    fun addItems(newItems: List<GithubRepoItemModel>) {
        val currentSize = differ.currentList.size
        val updatedList = differ.currentList.toMutableList().apply {
            addAll(newItems)
        }
        differ.submitList(updatedList) {
            notifyItemRangeInserted(currentSize, newItems.size)
        }
    }

    inner class ViewHolder(private val binding: ItemJavaRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(repo: GithubRepoItemModel) = binding.run {
            repoDataInclude.apply {
                repoTitleTv.text = repo.repositoryName
                repoStatsInclude.root.isVisible = true
                repoStatsInclude.repoForkCountTv.text = repo.forks.toString()
                repoStatsInclude.repoStarCountTv.text = repo.stars.toString()
                repoDescriptionTv.text = repo.repositoryDescription
            }
            repoUserInclude.apply {
                repoUserNameTv.text = repo.owner.username
                Glide.with(itemView.context)
                    .load(repo.owner.userAvatar)
                    .into(repoUserAvatarIv)
            }
            root.setOnClickListener {
                onRepoItemClickListener?.let { it(repo) }
            }
        }
    }

    fun setOnRepoItemClickListener(listener: (GithubRepoItemModel) -> Unit) {
        onRepoItemClickListener = listener
    }

}