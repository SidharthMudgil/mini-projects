package com.sidharth.vidyakhoj.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sidharth.vidyakhoj.data.University
import com.sidharth.vidyakhoj.databinding.ItemCardUniversityBinding
import com.sidharth.vidyakhoj.ui.callback.OnWebsiteClickCallback
import com.sidharth.vidyakhoj.util.RandomColorGenerator

class UniversityAdapter(
    private val universities: List<University>,
    private val onWebsiteClickCallback: OnWebsiteClickCallback,
) : Adapter<UniversityAdapter.UniversityViewHolder>() {

    inner class UniversityViewHolder(
        private val binding: ItemCardUniversityBinding
    ) : ViewHolder(binding.root) {
        fun bind(university: University) {
            binding.apply {
                cvUniversity.setCardBackgroundColor(RandomColorGenerator.generateRandomColor())
                tvName.text = university.name
                tvCountry.text = university.country
                tvWebsite.text = university.webpages[0]
                tvWebsite.setOnClickListener {
                    onWebsiteClickCallback.onWebsiteClick(university.webpages[0])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        return UniversityViewHolder(
            ItemCardUniversityBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return universities.size
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        holder.bind(universities[position])
    }
}