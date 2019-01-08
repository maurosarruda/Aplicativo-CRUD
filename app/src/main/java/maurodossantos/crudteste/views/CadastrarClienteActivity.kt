package maurodossantos.crudteste.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastrar_cliente.*
import maurodossantos.crudteste.R
import maurodossantos.crudteste.constantes.ConstantesBD
import maurodossantos.crudteste.util.ManipulacaoBD

class CadastrarClienteActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var manipuladorBD: ManipulacaoBD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_cliente)

        inicializarListeners()
        manipuladorBD = ManipulacaoBD(this)
    }

    private fun inicializarListeners() {
        btCadastrarCliente.setOnClickListener(this)
        btListarClientes.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.btCadastrarCliente -> {
                manipularCadastro()
            }
            R.id.btListarClientes -> {
                val intent = Intent(this, ListarDadosActivity::class.java)
                intent.putExtra("nomeTabelaListar", ConstantesBD.CLIENTE.NOME_TABELA)
                startActivity(intent)
            }
        }
    }

    private fun manipularCadastro() {
        if(validarCampos()) {
            val idExecucaoSQL = manipuladorBD.inserirCliente(edNomeCliente.text.toString(),
                    edIdadeCliente.text.toString().toInt())

            if(idExecucaoSQL < 0) {
                Toast.makeText(this, "Erro ao inserir!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Inserido com Sucesso!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validarCampos(): Boolean {
        if(edNomeCliente.text.toString() == "" || edIdadeCliente.text.toString() == "") {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            return false
        } else {
            return true
        }
    }

}
