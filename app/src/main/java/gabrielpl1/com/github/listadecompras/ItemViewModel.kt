package gabrielpl1.com.github.listadecompras

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// ViewModel que gerencia a lista de itens e expõe os dados para a UI através de LiveData
class ItemsViewModel : ViewModel() {

    // Lista mutável que contém os itens internamente
    private var items = mutableListOf<ItemModel>()

    // LiveData que a UI observa para atualizar automaticamente quando a lista de itens mudar
    val itemsLiveData = MutableLiveData<List<ItemModel>>()

    // Função que adiciona um novo item à lista
    fun addItem(name: String) {
        // Cria um novo ItemModel com um id e define a função de remoção (onRemove)
        val item = ItemModel(
            id = 0, // ID pode ser gerado dinamicamente em uma implementação mais avançada
            name = name,
            onRemove = ::removeItem // Passa a referência da função removeItem
        )

        // Verifica se o item já não está na lista antes de adicionar
        if (!items.contains(item)) {
            items.add(item) // Adiciona o item à lista
            itemsLiveData.value = items // Atualiza o valor do LiveData para notificar os observadores
        }
    }

    // Função privada que remove um item da lista
    private fun removeItem(item: ItemModel) {
        items.remove(item) // Remove o item da lista
        itemsLiveData.value = items // Atualiza o LiveData para refletir a remoção
    }
}
