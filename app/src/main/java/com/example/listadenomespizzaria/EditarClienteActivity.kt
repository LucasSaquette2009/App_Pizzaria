package com.example.listadenomespizzaria

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditarClienteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cliente)

        val etNome: EditText = findViewById(R.id.etNomeEditar)
        val etQuantidade: EditText = findViewById(R.id.etQuantidadeEditar)
        val btnCancelar: Button = findViewById(R.id.btnCancelar)
        val btnAlterar: Button = findViewById(R.id.btnAlterar)

        // Recebe os dados atuais do cliente que veio da MainActivity
        val nomeAtual = intent.getStringExtra("EXTRA_NOME") ?: ""
        val quantidadeAtual = intent.getIntExtra("EXTRA_QUANTIDADE", 0)
        val posicao = intent.getIntExtra("EXTRA_POSICAO", -1)

        etNome.setText(nomeAtual)
        etQuantidade.setText(quantidadeAtual.toString())

        btnCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        btnAlterar.setOnClickListener {
            val novoNome = etNome.text.toString().trim()
            val novaQuantidadeTexto = etQuantidade.text.toString().trim()

            if (novoNome.isEmpty() || novaQuantidadeTexto.isEmpty()) {
                Toast.makeText(this, "Preencha nome e quantidade", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val novaQuantidade = novaQuantidadeTexto.toInt()

            // Devolve os dados atualizados pra MainActivity
            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_POSICAO", posicao)
            resultIntent.putExtra("EXTRA_NOME", novoNome)
            resultIntent.putExtra("EXTRA_QUANTIDADE", novaQuantidade)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}