package maurodossantos.crudteste.util


import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import maurodossantos.crudteste.constantes.ConstantesBD


class ConectaBD(private var context: Context) : SQLiteOpenHelper(context, ConstantesBD.BD_INFORMACOES.BD_NOME, null, ConstantesBD.BD_INFORMACOES.BD_VERSAO) {

    private val createTabelaFita = (
            "create table " + ConstantesBD.FITA.NOME_TABELA + " ("
                    + ConstantesBD.FITA.COLUNAS.ID + " integer primary key autoincrement, "
                    + ConstantesBD.FITA.COLUNAS.TITULO + " text, "
                    + ConstantesBD.FITA.COLUNAS.ANO + " integer, "
                    + ConstantesBD.FITA.COLUNAS.PRECO  + " integer);")

    private val createTabelaCliente = (
            "create table " + ConstantesBD.CLIENTE.NOME_TABELA + " ("
                    + ConstantesBD.CLIENTE.COLUNAS.ID + " integer primary key autoincrement, "
                    + ConstantesBD.CLIENTE.COLUNAS.NOME + " text, "
                    + ConstantesBD.CLIENTE.COLUNAS.IDADE  + " integer);")

    private val createTabelaLocacao = (
            "create table " + ConstantesBD.LOCACAO.NOME_TABELA + " ("
                    + ConstantesBD.LOCACAO.COLUNAS.ID_LOCACAO + " integer primary key autoincrement, "
                    + ConstantesBD.LOCACAO.COLUNAS.ID_CLIENTE + " integer not null " +
                    "REFERENCES ${ConstantesBD.CLIENTE.NOME_TABELA} (${ConstantesBD.CLIENTE.COLUNAS.ID})\n ON DELETE CASCADE, "
                    + ConstantesBD.LOCACAO.COLUNAS.NOME_CLIENTE + " text, "
                    + ConstantesBD.LOCACAO.COLUNAS.ID_FITA + " integer not null " +
                    "REFERENCES ${ConstantesBD.FITA.NOME_TABELA} (${ConstantesBD.FITA.COLUNAS.ID})\n ON DELETE CASCADE, "
                    + ConstantesBD.LOCACAO.COLUNAS.NOME_FITA  + " text);")

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