package ir.hoseinahmadi.arzonine

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.hoseinahmadi.arzonine.Gold.GoldApiRepository
import ir.hoseinahmadi.arzonine.Gold.GoldRequest
import ir.hoseinahmadi.arzonine.databinding.ActivityMainBinding
import ir.hoseinahmadi.arzonine.datamodel.ContentModel
import ir.hoseinahmadi.arzonine.datamodel.DateModel
import ir.hoseinahmadi.arzonine.datamodel.GoldModel
import ir.hoseinahmadi.arzonine.remote.TimeApiRepository
import ir.hoseinahmadi.arzonine.remote.TimeRequest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val goldPrice = ArrayList<ContentModel>()
    private val currencyPrice = ArrayList<ContentModel>()
    private val cryptocurrencies = ArrayList<ContentModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!isInternetAvailable(this)){
            AlertDialog.Builder(this)
                .setTitle("آفلاین هستید")
                .setMessage("اتصال اینترنت وجود ندارد")
                .create()
                .show()
        }
        getPrice()
        TimeApiRepository.instance.getTime(
            object : TimeRequest {
                override fun onSuccess(data: DateModel) {
                    val date = data.date
                    val text = "${date.l} ${date.j} ${date.F} ${date.Y}"
                    binding.textView.text = text
                }

                override fun onNotSuccess(message: String) {
                    snack("خطا !!", Color.RED)

                }

                override fun onError(error: String) {
                    snack("در دریافت قیمت ها خطایی پیش آمد !!", Color.RED)

                }

            }
        )
        binding.recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.txtArz.setOnClickListener {
            setData(currencyPrice)
            binding.txtArz.setTextColor(getColor(R.color.gold_text))
            binding.txtGold.setTextColor(getColor(R.color.white_text))
            binding.txtCrypto.setTextColor(getColor(R.color.white_text))
        }
        binding.txtGold.setOnClickListener {
            setData(goldPrice)
            binding.txtGold.setTextColor(getColor(R.color.gold_text))
            binding.txtArz.setTextColor(getColor(R.color.white_text))
            binding.txtCrypto.setTextColor(getColor(R.color.white_text))
        }
        binding.txtCrypto.setOnClickListener {
            setData(cryptocurrencies)
            binding.txtCrypto.setTextColor(getColor(R.color.gold_text))
            binding.txtGold.setTextColor(getColor(R.color.white_text))
            binding.txtArz.setTextColor(getColor(R.color.white_text))
        }


    }

    private fun getPrice() {
        GoldApiRepository.instance.getGolds(
            object : GoldRequest {
                override fun onSuccess(data: GoldModel) {
                    goldPrice.addAll(data.data.golds)
                    currencyPrice.addAll(data.data.currencies)
                    cryptocurrencies.addAll(data.data.cryptocurrencies)
                    setData(goldPrice)
                }

                override fun onNotSuccess(message: String) {
                    snack("خطا!!", Color.RED)
                }

                override fun onError(error: String) {
                    snack("در دریافت قیمت ها خطایی پیش آمد !!", Color.RED)

                }

            }
        )
    }

    private fun setData(data: ArrayList<ContentModel>) {
        binding.recyclerview.adapter = RecyclerMainAdapter(data)

    }

    private fun snack(text: String, color: Int) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_INDEFINITE)
            .setText(text)
            .setBackgroundTint(color)
            .setTextColor(Color.WHITE)
            .show()
    }
    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}