package sofascore.academy.sofaproject.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sofascore.academy.sofaproject.databinding.DetailItemRowBinding

class DetailRecylerAdapter(
    private val labels: Array<String>,
    private val values: Array<String>
) : RecyclerView.Adapter<DetailRecylerAdapter.ViewHolderDetail>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailRecylerAdapter.ViewHolderDetail {
        return ViewHolderDetail(
            DetailItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailRecylerAdapter.ViewHolderDetail, position: Int) {
        holder.bind(labels[position], values[position])
    }

    inner class ViewHolderDetail(private val binding: DetailItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(label: String, value: String) {
            binding.label.text = label
            binding.value.text = value
        }
    }

    override fun getItemCount(): Int = labels.size
}
