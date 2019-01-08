package maurodossantos.crudteste.constantes

class ConstantesBD {

    object BD_INFORMACOES {
        val BD_NOME = "Locadora.db"
        val BD_VERSAO = 1
    }

    object FITA {
        val NOME_TABELA = "fita"

        object COLUNAS {
            val ID = "id"
            val TITULO = "titulo"
            val ANO = "ano"
            val PRECO = "preco"
        }

    }

    object CLIENTE {
        val NOME_TABELA = "cliente"

        object COLUNAS {
            val ID = "id"
            val NOME = "nome"
            val IDADE = "idade"
        }

    }

    object LOCACAO {
        val NOME_TABELA = "locacao"

        object COLUNAS {
            val ID_LOCACAO = "id"
            val ID_CLIENTE = "idCliente"
            val NOME_CLIENTE = "nomeCliente"
            val ID_FITA = "idFita"
            val NOME_FITA = "nomeFita"
        }

    }

}