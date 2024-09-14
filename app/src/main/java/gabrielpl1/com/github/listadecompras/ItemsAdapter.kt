package gabrielpl1.com.github.listadecompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.ImageButton


// Adapter para o RecyclerView, que manipula uma lista de ItemModel
class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    // Lista mutável de itens que será usada pelo Adapter
    private val items = mutableListOf<ItemModel>()

    // ViewHolder que mantém a referência às views de cada item no RecyclerView
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Referências às views do layout do item (um TextView e um ImageButton)
        val textView = view.findViewById<TextView>(R.id.textViewItem)
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        // Função para vincular (bind) os dados do ItemModel à UI (views)
        fun bind(item: ItemModel) {
            // Define o texto do TextView como o nome do item
            textView.text = item.name

            // Define o comportamento do botão, chamando a função de remoção passada no ItemModel
            button.setOnClickListener {
                item.onRemove(item) // Ao clicar no botão, a função onRemove do ItemModel é chamada
            }
        }
    }

    // Função responsável por criar e inflar o layout do item quando o RecyclerView precisar de um novo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // Infla o layout XML do item e cria um novo ItemViewHolder com ele
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    // Retorna o número total de itens na lista (necessário para o RecyclerView)
    override fun getItemCount(): Int = items.size

    // Função responsável por vincular os dados a um ViewHolder já existente
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Pega o item da posição atual e chama a função bind para vincular os dados ao ViewHolder
        val item = items[position]
        holder.bind(item)
    }

    // Função pública para adicionar um novo item à lista de itens
    fun addItem(newItem: ItemModel) {
        // Adiciona o novo item à lista
        items.add(newItem)
        // Notifica o RecyclerView que os dados mudaram, para ele atualizar a interface
        notifyDataSetChanged()
    }

    // Função pública para remover um item da lista
    fun removeItem(item: ItemModel) {
        // Remove o item da lista
        items.remove(item)
        // Notifica o RecyclerView que os dados mudaram, para ele atualizar a interface
        notifyDataSetChanged()
    }
}
