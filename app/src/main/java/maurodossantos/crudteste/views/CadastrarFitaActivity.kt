package maurodossantos.crudteste.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastrar_fita.*
import maurodossantos.crudteste.R
import maurodossantos.crudteste.constantes.ConstantesBD
import maurodossantos.crudteste.entradas.EntradasFita
import maurodossantos.crudteste.util.ManipulacaoBD

class CadastrarFitaActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var manipuladorBD: ManipulacaoBD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_fita)

        inicializarListeners()

        manipuladorBD = ManipulacaoBD(this)
        desativaBotoes()

    }

    private fun inicializarListeners() {
        btCadastrar.setOnClickListener(this)
        btExcluir.setOnClickListener(this)
        btPesquisar.setOnClickListener(this)
        btAlterar.setOnClickListener(this)
        btListarTodas.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        var id = view.id
        when (id) {
            R.id.btCadastrar -> {
                manipularCadastro()
            }
            R.id.btAlterar -> {
                manipularAlteracao()
            }
            R.id.btExcluir -> {
                manipularExclusao()
            }
            R.id.btPesquisar -> {
                manipularPesquisa()
            }
            R.id.btListarTodas -> {
                val intent = Intent(this, ListarDadosActivity::class.java)
                intent.putExtra("nomeTabelaListar", ConstantesBD.FITA.NOME_TABELA)
                startActivity(intent)
            }
        }
    }

    private fun ativaBotoes() {
        btAlterar.visibility = View.VISIBLE
        btExcluir.visibility = View.VISIBLE
    }

    private fun desativaBotoes() {
        btAlterar.visibility = View.INVISIBLE
        btExcluir.visibility = View.INVISIBLE
    }

    private fun manipularCadastro() {
        if (validarCampos()) {
            val idExecucaoSQL = manipuladorBD.inserirFita(edTituloFita.text.toString(),
                    edAnoFita.text.toString().toInt(),
                    edPrecoFita.text.toString().toInt())

            if (idExecucaoSQL < 0) {
                Toast.makeText(this, "Erro ao inserir!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Inserido com Sucesso!", Toast.LENGTH_LONG).show()
            }

            limparCampos()
            desativaBotoes()
        }
    }

    private fun limparCampos() {
        tvIdFita.text = "0"
        edTituloFita.setText("")
        edAnoFita.setText("")
        edPrecoFita.setText("")
    }

    private fun manipularPesquisa() {

        val titulo = edTituloFitaPesquisar.text.toString()
        if (titulo != "") {
            val entradasFita = manipuladorBD.buscarFita(titulo)

            setarValoresBuscaFita(entradasFita)
        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
        }

    }

    private fun setarValoresBuscaFita(entradasFita: EntradasFita?) {
        if (entradasFita != null) {
            tvIdFita.text = entradasFita.id.toString()
            edTituloFita.setText(entradasFita.titulo)
            edAnoFita.setText(entradasFita.ano.toString())
            edPrecoFita.setText(entradasFita.preco.toString())

            ativaBotoes()
        } else {
            Toast.makeText(this, "Fita não encontrada no Banco de Dados!", Toast.LENGTH_LONG).show()
        }
    }

    private fun manipularAlteracao() {

        if (validarCampos()) {
            manipuladorBD.alterarFita(tvIdFita.text.toString().toInt(),
                    edTituloFita.text.toString(),
                    edAnoFita.text.toString().toInt(),
                    edPrecoFita.text.toString().toInt())

            Toast.makeText(this, "Alterado com Sucesso!", Toast.LENGTH_LONG).show()
        }
    }

    private fun manipularExclusao() {

        manipuladorBD.excluirFita(tvIdFita.text.toString())

        Toast.makeText(this, "Excluído com Sucesso!", Toast.LENGTH_LONG).show()

        limparCampos()
        desativaBotoes()
    }

    private fun validarCampos(): Boolean {
        if (edTituloFita.text.toString() == "" || edAnoFita.text.toString() == "" || edPrecoFita.text.toString() == "") {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            return false
        } else {
            return true
        }
    }

}
