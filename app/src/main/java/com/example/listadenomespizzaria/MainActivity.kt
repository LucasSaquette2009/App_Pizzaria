package com.example.listadenomespizzaria

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ClienteAdapter

    // Registra o "ouvinte" que espera o resultado voltar da tela de edição
    private val editarLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val posicao = data?.getIntExtra("EXTRA_POSICAO", -1) ?: -1
            val novoNome = data?.getStringExtra("EXTRA_NOME") ?: ""
            val novaQuantidade = data?.getIntExtra("EXTRA_QUANTIDADE", 0) ?: 0

            if (posicao != -1) {
                adapter.atualizarCliente(posicao, Cliente(novoNome, novaQuantidade))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listaClientes = mutableListOf(
            Cliente("João Silva", 5),
            Cliente("Maria Souza", 2),
            Cliente("Pedro Santos", 8),
            Cliente("Ana Lima", 1),
            Cliente("Carlos Oliveira", 4),
            Cliente("Fernanda Costa", 6)
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewClientes)

        adapter = ClienteAdapter(listaClientes) { position ->
            // Chamado quando clica em "Editar" no item da lista
            val cliente = adapter.getCliente(position)
            val intent = Intent(this, EditarClienteActivity::class.java)
            intent.putExtra("EXTRA_POSICAO", position)
            intent.putExtra("EXTRA_NOME", cliente.nome)
            intent.putExtra("EXTRA_QUANTIDADE", cliente.quantidadePedidos)
            editarLauncher.launch(intent)
        }

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

            etNome.text.clear()
            etQuantidade.text.clear()
        }
    }
}