package sofascore.academy.sofaproject.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.data.Coach
import sofascore.academy.sofaproject.data.DataTypes
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.databinding.CoachRowBinding
import sofascore.academy.sofaproject.databinding.FighterRowBinding

class FighterRecyclerAdapter(
    private val context: Context,
    private var items: MutableList<Any>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DataTypes.FIGHTER.ordinal ->
                ViewHolderFighter(
                    FighterRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            DataTypes.COACH.ordinal ->
                ViewHolderCoach(
                    CoachRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.animation_recycler)

        when (holder) {
            is ViewHolderFighter -> holder.bind(items[position] as Fighter)
            is ViewHolderCoach -> holder.bind(items[position] as Coach)
        }
    }

    fun getNumberOfItems(): Int = items.size

    fun addItems(newItems: List<Any>) {
        items.addAll(newItems)
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

        fun bind(item: Fighter) {
            binding.figterName.text =
                context.getString(R.string.fighter_name, item.firstName, item.lastName)
            binding.figterScore.text =
                context.getString(R.string.fighter_score, item.win, item.draw, item.lose)

            val imgRequest = ImageRequest.Builder(context)
                .data(item.imageUrl.toString())
                .placeholder(R.drawable.person_placeholder)
                .error(R.drawable.person_placeholder)
                .target(binding.figterImage)
                .build()
            ImageLoader.Builder(context).build().enqueue(imgRequest)

            binding.root.setOnClickListener {
                listener.onItemClick(items[adapterPosition] as Fighter)
            }
        }
    }

    inner class ViewHolderCoach(private val binding: CoachRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Coach) {
            binding.coachName.text =
                context.getString(R.string.fighter_name, item.firstName, item.lastName)
            binding.coachSpeciality.text = item.speciality

            val imgRequest = ImageRequest.Builder(context)
                .data(item.imageUrl.toString())
                .placeholder(R.drawable.person_placeholder)
                .error(R.drawable.person_placeholder)
                .target(binding.coachImage)
                .build()
            ImageLoader.Builder(context).build().enqueue(imgRequest)

            binding.root.setOnClickListener {
                listener.onItemClick(items[adapterPosition] as Coach)
            }
        }
    }
}
