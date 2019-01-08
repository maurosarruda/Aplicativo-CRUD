package maurodossantos.crudteste.util

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import maurodossantos.crudteste.constantes.ConstantesBD
import maurodossantos.crudteste.entradas.EntradasCliente
import maurodossantos.crudteste.entradas.EntradasFita

class ManipulacaoBD {

    private var conectaBD: ConectaBD

    constructor(context: Context) {
        conectaBD = ConectaBD(context)
    }

    fun inserirFita(titulo: String, ano: Int, preco: Int): Long {
        val bd = conectaBD.writableDatabase

        val valores = ContentValues()
        valores.put(ConstantesBD.FITA.COLUNAS.TITULO, titulo)
        valores.put(ConstantesBD.FITA.COLUNAS.ANO, ano)
        valores.put(ConstantesBD.FITA.COLUNAS.PRECO, preco)

        // Insere fita
        return bd.insert(ConstantesBD.FITA.NOME_TABELA, null, valores)
    }

    fun buscarFita(tituloFita: String): EntradasFita? {

        var entradaFita: EntradasFita? = null

        val cursor: Cursor

        val bd = conectaBD.readableDatabase

        val projecao = arrayOf(ConstantesBD.FITA.COLUNAS.ID,
                ConstantesBD.FITA.COLUNAS.TITULO,
                ConstantesBD.FITA.COLUNAS.ANO,
                ConstantesBD.FITA.COLUNAS.PRECO)

        val selecao = " ${ConstantesBD.FITA.COLUNAS.TITULO} = ?"
        val selecaoArgumentos = arrayOf(tituloFita)

        // Carrega fita
        cursor = bd.query(ConstantesBD.FITA.NOME_TABELA, projecao, selecao, selecaoArgumentos, null, null, null)

        // Verifica se existe 1 elemento de retorno
        if (cursor.count > 0) {
            cursor.moveToFirst()

            val id = cursor.getInt(cursor.getColumnIndex(ConstantesBD.FITA.COLUNAS.ID))
            val titulo = cursor.getString(cursor.getColumnIndex(ConstantesBD.FITA.COLUNAS.TITULO))
            val ano = cursor.getString(cursor.getColumnIndex(ConstantesBD.FITA.COLUNAS.ANO))
            val preco = cursor.getString(cursor.getColumnIndex(ConstantesBD.FITA.COLUNAS.PRECO))

            // Atribui valor a variável da fita
            entradaFita = EntradasFita(id, titulo, ano.toInt(), preco.toInt())
        }

        cursor.close()

        return entradaFita
    }

    fun buscarTodasFita(): String {

        val cursor: Cursor

        val bd = conectaBD.readableDatabase

        // Busca informações da tabela
        cursor = bd.rawQuery("SELECT * FROM ${ConstantesBD.FITA.NOME_TABELA}", null)

        val bufferString = StringBuffer()

        var id: Int
        var ano: Int
        var preco: Int
        var titulo: String

        while(cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(ConstantesBD.FITA.COLUNAS.ID))
            titulo = cursor.getString(cursor.getColumnIndex(ConstantesBD.FITA.COLUNAS.TITULO))
            ano = cursor.getInt(cursor.getColumnIndex(ConstantesBD.FITA.COLUNAS.ANO))
            preco = cursor.getInt(cursor.getColumnIndex(ConstantesBD.FITA.COLUNAS.PRECO))

            bufferString.append("Id: $id, Titulo: $titulo, Ano: $ano, Preço: $preco \n")
        }

        cursor.close()

        // Retorna listagem completa
        return bufferString.toString()
    }

    fun alterarFita(idFita: Int, titulo: String, ano: Int, preco: Int) {
        val bd = conectaBD.writableDatabase

        val valores = ContentValues()
        valores.put(ConstantesBD.FITA.COLUNAS.ID, idFita)
        valores.put(ConstantesBD.FITA.COLUNAS.TITULO, titulo)
        valores.put(ConstantesBD.FITA.COLUNAS.ANO, ano)
        valores.put(ConstantesBD.FITA.COLUNAS.PRECO, preco)

        // Faz a atualização
        bd.update(ConstantesBD.FITA.NOME_TABELA, valores,"${ConstantesBD.FITA.COLUNAS.ID}=?", arrayOf(idFita.toString()))
    }

    fun excluirFita(idFita: String) {
        val bd = conectaBD.writableDatabase

        // Faz a remoção
        bd.delete(ConstantesBD.FITA.NOME_TABELA, "${ConstantesBD.FITA.COLUNAS.ID}=?", arrayOf(idFita))
    }

    fun inserirCliente(nome: String, idade: Int): Long {
        val bd = conectaBD.writableDatabase

        val valores = ContentValues()
        valores.put(ConstantesBD.CLIENTE.COLUNAS.NOME, nome)
        valores.put(ConstantesBD.CLIENTE.COLUNAS.IDADE, idade)

        // Insere cliente
        return bd.insert(ConstantesBD.CLIENTE.NOME_TABELA, null, valores)
    }

    fun buscarCliente(nomeCliente: String): EntradasCliente? {

        var entradasCliente: EntradasCliente? = null

        val cursor: Cursor

        val bd = conectaBD.readableDatabase

        val projecao = arrayOf(ConstantesBD.CLIENTE.COLUNAS.ID,
                ConstantesBD.CLIENTE.COLUNAS.NOME,
                ConstantesBD.CLIENTE.COLUNAS.IDADE)

        val selecao = " ${ConstantesBD.CLIENTE.COLUNAS.NOME} = ?"
        val selecaoArgumentos = arrayOf(nomeCliente)

        // Carrega cliente
        cursor = bd.query(ConstantesBD.CLIENTE.NOME_TABELA, projecao, selecao, selecaoArgumentos, null, null, null)

        // Verifica se existe 1 elemento de retorno
        if (cursor.count > 0) {
            cursor.moveToFirst()

            val id = cursor.getInt(cursor.getColumnIndex(ConstantesBD.CLIENTE.COLUNAS.ID))
            val nome = cursor.getString(cursor.getColumnIndex(ConstantesBD.CLIENTE.COLUNAS.NOME))
            val idade = cursor.getString(cursor.getColumnIndex(ConstantesBD.CLIENTE.COLUNAS.IDADE))

            // Atribui valor a variável de cliente
            entradasCliente = EntradasCliente(id, nome, idade.toInt())
        }

        cursor.close()

        return entradasCliente
    }

    fun buscarTodosCliente(): String {

        val cursor: Cursor

        val bd = conectaBD.readableDatabase

        // Busca informações da tabela
        cursor = bd.rawQuery("SELECT * FROM ${ConstantesBD.CLIENTE.NOME_TABELA}", null)

        val bufferString = StringBuffer()

        var id: Int
        var nome: String
        var idade: Int

        while(cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(ConstantesBD.CLIENTE.COLUNAS.ID))
            nome = cursor.getString(cursor.getColumnIndex(ConstantesBD.CLIENTE.COLUNAS.NOME))
            idade = cursor.getInt(cursor.getColumnIndex(ConstantesBD.CLIENTE.COLUNAS.IDADE))

            bufferString.append("Id: $id, Nome: $nome, Idade: $idade \n")
        }

        cursor.close()

        return bufferString.toString()
    }

    fun inserirLocacao(idCliente: Int, nomeCliente: String, idFita: Int, nomeFita: String): Long {
        val bd = conectaBD.writableDatabase

        val valores = ContentValues()
        valores.put(ConstantesBD.LOCACAO.COLUNAS.ID_CLIENTE, idCliente)
        valores.put(ConstantesBD.LOCACAO.COLUNAS.NOME_CLIENTE, nomeCliente)
        valores.put(ConstantesBD.LOCACAO.COLUNAS.ID_FITA, idFita)
        valores.put(ConstantesBD.LOCACAO.COLUNAS.NOME_FITA, nomeFita)

        // Insere locação
        return bd.insert(ConstantesBD.LOCACAO.NOME_TABELA, null, valores)
    }

    fun buscarTodasLocacoes(): String {

        val cursor: Cursor

        val bd = conectaBD.readableDatabase

        // Busca informações da tabela
        cursor = bd.rawQuery("SELECT * FROM ${ConstantesBD.LOCACAO.NOME_TABELA}", null)

        val bufferString = StringBuffer()

        var idLocacao: Int
        var idCliente: Int
        var nomeCliente: String
        var idFita: Int
        var nomeFita: String

        while(cursor.moveToNext()) {
            idLocacao = cursor.getInt(cursor.getColumnIndex(ConstantesBD.LOCACAO.COLUNAS.ID_LOCACAO))
            idCliente = cursor.getInt(cursor.getColumnIndex(ConstantesBD.LOCACAO.COLUNAS.ID_CLIENTE))
            nomeCliente = cursor.getString(cursor.getColumnIndex(ConstantesBD.LOCACAO.COLUNAS.NOME_CLIENTE))
            idFita = cursor.getInt(cursor.getColumnIndex(ConstantesBD.LOCACAO.COLUNAS.ID_FITA))
            nomeFita = cursor.getString(cursor.getColumnIndex(ConstantesBD.LOCACAO.COLUNAS.NOME_FITA))

            bufferString.append("Id: $idLocacao, Cliente: $idCliente - $nomeCliente, Fita: $idFita - $nomeFita \n")
        }

        cursor.close()

        return bufferString.toString()
    }


}