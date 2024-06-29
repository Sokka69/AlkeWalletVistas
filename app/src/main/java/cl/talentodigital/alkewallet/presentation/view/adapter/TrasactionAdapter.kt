package cl.talentodigital.alkewallet.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.talentodigital.alkewallet.R
import cl.talentodigital.alkewallet.data.model.Transaction
import cl.talentodigital.alkewallet.databinding.ListUsersTransactionsBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

class TransactionAdapter(private val transactionList: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

        inner class TransactionViewHolder(private val binding: ListUsersTransactionsBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(transaction: Transaction) {
                binding.concept.text = transaction.concept

                // Formatear la fecha con manejo de excepciones
                val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                val targetFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

                try {
                    val date = originalFormat.parse(transaction.date)
                    val formattedDate = targetFormat.format(date)
                    binding.dateList.text = formattedDate
                } catch (e: Exception) {
                    e.printStackTrace()
                    binding.dateList.text = transaction.date // Mostrar fecha original en caso de error
                }
                // Formatear el monto
                val amount = if (transaction.type == "payment") {
                    "-$${transaction.amount}"
                } else {
                    "$${transaction.amount}"
                }
                binding.ammountList.text = amount

                // Formatear icono
                val imageResource = when (transaction.type) {
                    "topup" -> R.drawable.ic_user_add
                    "payment" -> R.drawable.ic_user_send
                    else -> R.drawable.settings_icon

                }

                binding.imageViewType.setImageResource(imageResource)

                if (transaction.imgUrl.isNullOrEmpty()) {
               // Implementacion Glide
                Glide.with(binding.root.context)
                    .load(transaction.imgUrl)
                    .placeholder(imageResource)
                    .error(imageResource)
                    .into(binding.imageViewType)

                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ListUsersTransactionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactionList[position])

    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

}