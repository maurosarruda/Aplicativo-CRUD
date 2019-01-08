package maurodossantos.crudteste.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_listar_fita.*
import maurodossantos.crudteste.R
import maurodossantos.crudteste.constantes.ConstantesBD
import maurodossantos.crudteste.util.ManipulacaoBD

class ListarDadosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_fita)

        val nomeTabelaListar = intent.getStringExtra("nomeTabelaListar")

        if(nomeTabelaListar != "") {
            gerarTabela(nomeTabelaListar)
        }
    }

    private fun gerarTabela(nomeTabela: String) {

        var entradasTabela : String = ""

        when (nomeTabela) {
            ConstantesBD.FITA.NOME_TABELA -> {
                entradasTabela = ManipulacaoBD(this).buscarTodasFita()
            }
            ConstantesBD.CLIENTE.NOME_TABELA -> {
                entradasTabela = ManipulacaoBD(this).buscarTodosCliente()
            }
            ConstantesBD.LOCACAO.NOME_TABELA -> {
                entradasTabela = ManipulacaoBD(this).buscarTodasLocacoes()
            }
        }

        tvNomeTabela.text = nomeTabela.capitalize()
        tvInformacoesTabela.text = entradasTabela

        if(entradasTabela != "") {
            Toast.makeText(this, "Tabela carregada com sucesso!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Banco de Dados não possui elementos!", Toast.LENGTH_LONG).show()
        }

    }
}
