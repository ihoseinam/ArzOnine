package ir.hoseinahmadi.arzonine

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ir.hoseinahmadi.arzonine.databinding.RecyclerMainItemBinding
import ir.hoseinahmadi.arzonine.datamodel.ContentModel
import kotlinx.coroutines.delay

class RecyclerMainAdapter(
    private val Alldata: ArrayList<ContentModel>,
) : RecyclerView.Adapter<RecyclerMainAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RecyclerMainItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = Alldata.size


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.setData(Alldata[position])
    }


    inner class MainViewHolder(
        private val binding: RecyclerMainItemBinding,
    ) : ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun setData(data: ContentModel) {
            binding.txtLable.text = data.label
            val price =(data.price/10)
            val eee =String.format("%,d", price).toString()
            binding.txtPrice.text = "${eee}     تومان    "

            if (data.label=="بیت کوین"){
                val loli =String.format("%,d", data.price).toString()
                binding.txtPrice.text ="$  ${loli}"
            }
            when (data.label){
                "بیت کوین" ->binding.imgLable.setImageResource(R.drawable.btc)
              "تتر" ->binding.imgLable.setImageResource(R.drawable.usdt)
              "دلار" ->binding.imgLable.setImageResource(R.drawable.usd)
              "یورو" ->binding.imgLable.setImageResource(R.drawable.ic_uro)
              "درهم" ->binding.imgLable.setImageResource(R.drawable.ic_derham)
              "پوند" ->binding.imgLable.setImageResource(R.drawable.ic_pond)
                else ->binding.imgLable.setImageResource(R.drawable.gold)
            }
        }
    }

}