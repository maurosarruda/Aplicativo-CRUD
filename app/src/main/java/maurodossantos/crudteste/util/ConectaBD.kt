package maurodossantos.crudteste.util


import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import maurodossantos.crudteste.constantes.ConstantesBD


class ConectaBD(private var context: Context) : SQLiteOpenHelper(context, ConstantesBD.BD_INFORMACOES.BD_NOME, null, ConstantesBD.BD_INFORMACOES.BD_VERSAO) {

    private val createTabelaFita = (
            "CREATE TABLE " + ConstantesBD.FITA.NOME_TABELA + " ("
                    + ConstantesBD.FITA.COLUNAS.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ConstantesBD.FITA.COLUNAS.TITULO + " TEXT, "
                    + ConstantesBD.FITA.COLUNAS.ANO + " INTEGER, "
                    + ConstantesBD.FITA.COLUNAS.PRECO  + " INTEGER);")

    private val createTabelaCliente = (
            "CREATE TABLE " + ConstantesBD.CLIENTE.NOME_TABELA + " ("
                    + ConstantesBD.CLIENTE.COLUNAS.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ConstantesBD.CLIENTE.COLUNAS.NOME + " TEXT, "
                    + ConstantesBD.CLIENTE.COLUNAS.IDADE  + " INTEGER);")

    private val createTabelaLocacao = (
            "CREATE TABLE " + ConstantesBD.LOCACAO.NOME_TABELA + " ("
                    + ConstantesBD.LOCACAO.COLUNAS.ID_LOCACAO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ConstantesBD.LOCACAO.COLUNAS.ID_CLIENTE + " INTEGER NOT NULL " +
                    "REFERENCES ${ConstantesBD.CLIENTE.NOME_TABELA} (${ConstantesBD.CLIENTE.COLUNAS.ID})\n ON DELETE CASCADE, "
                    + ConstantesBD.LOCACAO.COLUNAS.NOME_CLIENTE + " TEXT, "
                    + ConstantesBD.LOCACAO.COLUNAS.ID_FITA + " INTEGER NOT NULL " +
                    "REFERENCES ${ConstantesBD.FITA.NOME_TABELA} (${ConstantesBD.FITA.COLUNAS.ID})\n ON DELETE CASCADE, "
                    + ConstantesBD.LOCACAO.COLUNAS.NOME_FITA  + " TEXT);")

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

        try{
            sqLiteDatabase.execSQL(createTabelaFita)
            sqLiteDatabase.execSQL(createTabelaCliente)
            sqLiteDatabase.execSQL(createTabelaLocacao)

            Toast.makeText(context, "Banco de Dados criado!", Toast.LENGTH_LONG).show()
        } catch (e : SQLException) {
            Toast.makeText(context, "Erro ao criar Banco de Dados! " + e, Toast.LENGTH_LONG).show()
        }

    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, versaoAntiga: Int, versaoNova: Int) {

    }


}