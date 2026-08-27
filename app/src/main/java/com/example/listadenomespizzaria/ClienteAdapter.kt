package com.example.listadenomespizzaria

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClienteAdapter(
    private val listaClientes: MutableList<Cliente>,
    private val onEditarClick: (position: Int) -> Unit
) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    class ClienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById(R.id.tvNomeCliente)
        val tvQuantidade: TextView = itemView.findViewById(R.id.tvQuantidadePedidos)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnRemover: Button = itemView.findViewById(R.id.btnRemover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cliente, parent, false)
        return ClienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val clienteAtual = listaClientes[position]
        holder.tvNome.text = clienteAtual.nome
        holder.tvQuantidade.text = "${clienteAtual.quantidadePedidos} pedidos"

        holder.btnRemover.setOnClickListener {
            removerCliente(holder.adapterPosition)
        }

        holder.btnEditar.setOnClickListener {
            onEditarClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return listaClientes.size
    }

    fun adicionarCliente(cliente: Cliente) {
        listaClientes.add(cliente)
        notifyItemInserted(listaClientes.size - 1)
    }

    fun removerCliente(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            listaClientes.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaClientes.size)
        }
    }

    fun atualizarCliente(position: Int, clienteAtualizado: Cliente) {
        if (position != RecyclerView.NO_POSITION) {
            listaClientes[position] = clienteAtualizado
            notifyItemChanged(position)
        }
    }

    fun getCliente(position: Int): Cliente {
        return listaClientes[position]
    }
}