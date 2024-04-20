package com.bobby.cadastrar

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase // Import para Firebase Realtime Database
import com.google.firebase.firestore.FirebaseFirestore // Import para Firestore
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editCpf: EditText
    private lateinit var editRg: EditText
    private lateinit var editNasc: EditText
    private lateinit var editFone1: EditText
    private lateinit var editFone2: EditText
    private lateinit var editCep: EditText
    private lateinit var editRua: EditText
    private lateinit var editPlano: EditText
    private lateinit var editEmail: EditText
    private lateinit var btCadastrar: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Corrigido para usar `setContentView` corretamente

        // Inicialize os campos de edição e o botão
        editNome = findViewById(R.id.edit_nome)
        editCpf = findViewById(R.id.edit_cpf)
        editRg = findViewById(R.id.edit_rg)
        editNasc = findViewById(R.id.edit_nasc)
        editFone1 = findViewById(R.id.edit_fone1)
        editFone2 = findViewById(R.id.edit_fone2)
        editCep = findViewById(R.id.edit_cep)
        editRua = findViewById(R.id.edit_rua)
        editPlano = findViewById(R.id.edit_plano)
        editEmail = findViewById(R.id.edit_email)
        btCadastrar = findViewById(R.id.bt_cadastrar)

        // Defina um listener para o botão de cadastro
        btCadastrar.setOnClickListener {
            // Coletar os dados dos campos de texto
            val nome = editNome.text.toString()
            val cpf = editCpf.text.toString()
            val rg = editRg.text.toString()
            val nasc = editNasc.text.toString()
            val fone1 = editFone1.text.toString()
            val fone2 = editFone2.text.toString()
            val cep = editCep.text.toString()
            val rua = editRua.text.toString()
            val plano = editPlano.text.toString()
            val email = editEmail.text.toString()

            // Crie um mapa com os dados coletados
            val dados = mapOf(
                "nome" to nome,
                "cpf" to cpf,
                "rg" to rg,
                "nasc" to nasc,
                "fone1" to fone1,
                "fone2" to fone2,
                "cep" to cep,
                "rua" to rua,
                "plano" to plano,
                "email" to email
            )


            salvarDadosFirebase(dados)
        }
    }


    private fun salvarDadosFirebase(dados: Map<String, String>) {

        val database = FirebaseDatabase.getInstance().reference
        val userRef = database.child("usuarios").push()


         val db = FirebaseFirestore.getInstance()
         db.collection("usuarios")
            .add(dados)
            .addOnSuccessListener {
                 Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
             }
            .addOnFailureListener { exception ->
                 Toast.makeText(this, "Falha ao salvar dados: ${exception.message}", Toast.LENGTH_SHORT).show()
             }


    }
}
