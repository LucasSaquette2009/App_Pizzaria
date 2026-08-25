package com.example.listadenomespizzaria

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ClienteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lista de nomes dos clientes com a quantidade de pedidos de cada um
        val listaClientes = mutableListOf(
            Cliente("João Silva", 5),
            Cliente("Maria Souza", 2),
            Cliente("Pedro Santos", 8),
            Cliente("Ana Lima", 1),
            Cliente("Carlos Oliveira", 4),
            Cliente("Fernanda Costa", 6)
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewClientes)
        adapter = ClienteAdapter(listaClientes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val etNome: EditText = findViewById(R.id.etNomeCliente)
        val etQuantidade: EditText = findViewById(R.id.etQuantidadePedidos)
        val btnAdicionar: Button = findViewById(R.id.btnAdicionar)

        btnAdicionar.setOnClickListener {
            val nome = etNome.text.toString().trim()
            val quantidadeTexto = etQuantidade.text.toString().trim()

            if (nome.isEmpty() || quantidadeTexto.isEmpty()) {
                Toast.makeText(this, "Preencha nome e quantidade", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantidade = quantidadeTexto.toInt()
            adapter.adicionarCliente(Cliente(nome, quantidade))

            // Limpa os campos depois de adicionar
            etNome.text.clear()
            etQuantidade.text.clear()
        }
    }
}