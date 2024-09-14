package gabrielpl1.com.github.listadecompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter responsável por exibir uma lista de itens no RecyclerView
class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    // Lista de itens que será exibida no RecyclerView
    private var items = listOf<ItemModel>()

    // ViewHolder representa os elementos de UI individuais dentro da RecyclerView
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Referência ao TextView no layout do item
        val textView = view.findViewById<TextView>(R.id.textViewItem)

        // Referência ao botão de imagem no layout do item
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        // Função que "vincula" os dados do item às views
        fun bind(item: ItemModel) {
            // Define o texto do TextView com o nome do item
            textView.text = item.name

            // Define uma ação de clique no botão de imagem
            button.setOnClickListener {
                // Chama a função de remover associada ao item quando o botão é clicado
                item.onRemove(item)
            }
        }
    }

    // Método chamado para inflar o layout de cada item e criar o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // Inflando o layout do item a partir do XML e criando o ViewHolder correspondente
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    // Retorna o número de itens na lista
    override fun getItemCount(): Int = items.size

    // Método chamado para associar um item específico da lista ao ViewHolder
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Obtém o item da posição atual
        val item = items[position]
        // Vincula os dados do item ao ViewHolder
        holder.bind(item)
    }

    // Função para atualizar a lista de itens e notificar o RecyclerView das mudanças
    fun updateItems(newItems: List<ItemModel>) {
        // Atualiza a lista de itens
        items = newItems
        // Notifica o RecyclerView que os dados foram modificados, para que ele seja redesenhado
        notifyDataSetChanged()
    }
}
