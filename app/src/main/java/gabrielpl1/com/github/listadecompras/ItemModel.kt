package gabrielpl1.com.github.listadecompras

/**
 * ItemModel se refere a uma classe em kotlin que representa um item dentro da nossa lista
 * Ele recebe uma string que irá funcionar como nosso construtor
 * A função OnRemove irá remover um item da lista
 */

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)