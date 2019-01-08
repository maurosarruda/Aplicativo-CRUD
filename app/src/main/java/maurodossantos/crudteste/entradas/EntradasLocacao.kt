package maurodossantos.crudteste.entradas

data class EntradasLocacao(
        val idLocacao: Int,
        val idCliente: Int,
        val nomeCliente: String,
        val idFita: Int,
        val nomeFita: String
)