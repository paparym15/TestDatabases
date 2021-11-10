package com.one.testdatabases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.one.testdatabases.databinding.DogItemBinding
import com.one.testdatabases.models.Dog

class DogAdapter: ListAdapter<Dog, DogAdapter.DogViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder(DogItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DogViewHolder(private val binding: DogItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dog: Dog) {
            binding.tvId.text = dog.dogID.toString()
            binding.tvDogName.text = dog.name
        }
    }

    class Diff: DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem.dogID == newItem.dogID
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem == newItem
        }
    }
}