package cl.talentodigital.alkewallet.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.talentodigital.alkewallet.R
import cl.talentodigital.alkewallet.databinding.CardViewListUsersTransactionBinding
import cl.talentodigital.alkewallet.data.model.Transaction
import com.bumptech.glide.Glide

class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

        inner class TransactionViewHolder(private val binding: CardViewListUsersTransactionBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(transaction: Transaction) {
                binding.name.text = transaction.name
                binding.lastName.text = transaction.lastName
                binding.dateList.text = transaction.date
                binding.ammountList.text = transaction.amount.toString()

                // Implementacion Glide
                Glide.with(binding.root.context)
                    .load(transaction.userImageUrl)
                    /*.placeholder(R.drawable.profile_picture) // optional placeholder*/
                    .error(R.drawable.profile_picture)
                    .into(binding.imgProfileList)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = CardViewListUsersTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

/*class ListTransactionAdapter(private val listaTransacciones: List<Transaction>) : RecyclerView.Adapter<ListTransactionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val lastName: TextView = view.findViewById(R.id.last_name)
        val date: TextView = view.findViewById(R.id.date_list)
        val amount: TextView = view.findViewById(R.id.ammount_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_list_users_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaTransacciones.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = listaTransacciones[position]
        holder.name.text = transaction.name
        holder.lastName.text = transaction.lastName
        holder.date.text = transaction.date
        holder.amount.text = transaction.amount
    }*/
}