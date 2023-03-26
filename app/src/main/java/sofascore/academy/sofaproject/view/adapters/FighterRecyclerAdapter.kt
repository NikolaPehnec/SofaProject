package sofascore.academy.sofaproject.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.data.Coach
import sofascore.academy.sofaproject.data.DataTypes
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.databinding.FighterRowBinding

class FighterRecyclerAdapter(
    private val context: Context,
    private var items: MutableList<Any>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<FighterRecyclerAdapter.ViewHolderFighter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFighter {
        return ViewHolderFighter(
            FighterRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderFighter, position: Int) {
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.animation_recycler)

        holder.bind(items[position])
    }

    fun getNumberOfItems(): Int = items.size

    fun addItems(newItems: List<Any>) {
        for (item in newItems) {
            if (!items.contains(item)) {
                items.add(item)
            }
        }
        notifyDataSetChanged()
    }

    fun removeAllItems() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Fighter -> DataTypes.FIGHTER.ordinal
            is Coach -> DataTypes.COACH.ordinal
            else -> -1
        }
    }

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(item: Any)
    }

    inner class ViewHolderFighter(private val binding: FighterRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Any) {
            when (item) {
                is Fighter -> {
                    binding.figterName.text =
                        context.getString(R.string.fighter_name, item.firstName, item.lastName)
                    binding.figterScore.text =
                        context.getString(R.string.fighter_score, item.win, item.draw, item.lose)
                    binding.coachLabel.visibility = View.INVISIBLE

                    val imgRequest = ImageRequest.Builder(context)
                        .data(item.imageUrl.toString())
                        .placeholder(R.drawable.person_placeholder)
                        .error(R.drawable.person_placeholder)
                        .target(binding.figterImage)
                        .build()
                    ImageLoader.Builder(context).build().enqueue(imgRequest)

                    binding.root.setOnClickListener {
                        listener.onItemClick(items[bindingAdapterPosition] as Fighter)
                    }
                }

                is Coach -> {
                    binding.figterName.text =
                        context.getString(R.string.fighter_name, item.firstName, item.lastName)
                    binding.figterScore.text = item.speciality
                    binding.coachLabel.visibility = View.VISIBLE

                    val imgRequest = ImageRequest.Builder(context)
                        .data(item.imageUrl.toString())
                        .placeholder(R.drawable.person_placeholder)
                        .error(R.drawable.person_placeholder)
                        .target(binding.figterImage)
                        .build()
                    ImageLoader.Builder(context).build().enqueue(imgRequest)

                    binding.root.setOnClickListener {
                        listener.onItemClick(items[bindingAdapterPosition] as Coach)
                    }
                }
            }
        }
    }
}
