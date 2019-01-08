package maurodossantos.crudteste.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastrar_cliente.*
import kotlinx.android.synthetic.main.activity_cadastrar_locacao.*
import maurodossantos.crudteste.R
import maurodossantos.crudteste.constantes.ConstantesBD
import maurodossantos.crudteste.entradas.EntradasCliente
import maurodossantos.crudteste.entradas.EntradasFita
import maurodossantos.crudteste.util.ManipulacaoBD

class CadastrarLocacaoActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var manipuladorBD: ManipulacaoBD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_locacao)

        inicializarListeners()
        manipuladorBD = ManipulacaoBD(this)
    }

    private fun inicializarListeners() {
        btCadastrarLocacao.setOnClickListener(this)
        btListarLocacoes.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        var id = view.id
        when (id) {
            R.id.btCadastrarLocacao -> {
                manipularCadastro()
            }
            R.id.btListarLocacoes -> {
                val intent = Intent(this, ListarDadosActivity::class.java)
                intent.putExtra("nomeTabelaListar", ConstantesBD.LOCACAO.NOME_TABELA)
                startActivity(intent)
            }
        }
    }

    private fun manipularCadastro() {
        if(validarCampos()) {

            val entradasFita : EntradasFita?
            val entradasCliente : EntradasCliente?

            entradasFita = manipuladorBD.buscarFita(edNomeFitaLocacao.text.toString())
            entradasCliente = manipuladorBD.buscarCliente(edNomeClienteLocacao.text.toString())

            if(entradasFita != null && entradasCliente != null) {
                val idExecucaoSQL = manipuladorBD.inserirLocacao(
                        entradasCliente.id,
                        edNomeClienteLocacao.text.toString(),
                        entradasFita.id,
                        edNomeFitaLocacao.text.toString())

                if (idExecucaoSQL < 0) {
                    Toast.makeText(this, "Erro ao inserir!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Inserido com Sucesso!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun validarCampos(): Boolean {
        if(edNomeClienteLocacao.text.toString() == "" || edNomeFitaLocacao.text.toString() == "") {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            return false
        } else {
            return true
        }
    }
}
