package sofascore.academy.sofaproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.databinding.FighterRowBinding

class FighterRecyclerAdapter(
    private val context: Context,
    private var items: List<Fighter>,
    listener: OnItemClickListener
) :
    RecyclerView.Adapter<FighterRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = FighterRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(items[position])

    fun setData(newItems: List<Fighter>) {
        items = newItems
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class MyViewHolder(private val binding: FighterRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Fighter) {
            binding.figterName.text =
                context.getString(R.string.fighter_name, item.firstName, item.lastName)
            binding.figterScore.text =
                context.getString(R.string.fighter_score, item.win, item.draw, item.lose)
            binding.figterImage.load(item.imageUrl.toString())
        }
    }

}