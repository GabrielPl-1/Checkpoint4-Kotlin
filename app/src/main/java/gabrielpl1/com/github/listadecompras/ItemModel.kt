package gabrielpl1.com.github.listadecompras

/**
 * ItemModel se refere a uma classe em kotlin que representa um item dentro da nossa lista
 * Ele recebe uma string que irá funcionar como nosso construtor
 * A função OnRemove irá remover um item da lista
 */
data class ItemModel(
    val id: Int,
    val name: String,
    val onRemove: (ItemModel) -> Unit
)