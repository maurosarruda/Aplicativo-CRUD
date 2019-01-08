package maurodossantos.crudteste.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import maurodossantos.crudteste.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarListeners()
    }

    private fun inicializarListeners() {
        btCadastrarFita.setOnClickListener(this)
        btCadastrarCliente.setOnClickListener(this)
        btCadastrarLocacao.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.btCadastrarFita -> {
                startActivity(Intent(this, CadastrarFitaActivity::class.java))
            }
            R.id.btCadastrarCliente -> {
                startActivity(Intent(this, CadastrarClienteActivity::class.java))
            }
            R.id.btCadastrarLocacao -> {
                startActivity(Intent(this, CadastrarLocacaoActivity::class.java))
            }
        }
    }
}
