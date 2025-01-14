package com.brunoccbertolini.github.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brunoccbertolini.github.databinding.ItemJavaRepositoryBinding
import com.brunoccbertolini.github.domain.entity.GithubRepoItemModel
import com.bumptech.glide.Glide

class JavaRepoListAdapter :
    RecyclerView.Adapter<JavaRepoListAdapter.ViewHolder>() {
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
    val differ = AsyncListDiffer(
        this,
        differCallback
    )

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

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bindView(differ.currentList[position])
    }

    private var onRepoItemClickListener: ((GithubRepoItemModel) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemJavaRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(repo: GithubRepoItemModel) = binding.run {
            repoDataInclude.apply {
                repoTitleTv.text = repo.repositoryName
                repoForkCountTv.text = repo.forks.toString()
                repoStarCountTv.text = repo.stars.toString()
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