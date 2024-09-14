package gabrielpl1.com.github.listadecompras


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


    class ItemsViewModel(application: Application) : AndroidViewModel(application) {

        private val itemDao: ItemDao
        val itemsLiveData: LiveData<List<ItemModel>>

        /**
         * Este método é responsável por adicionar um novo item à lista.
         * Ele cria um novo ItemModel e o adiciona à lista de itens.
         *
         * @param name O nome do item a ser adicionado.
         */

            init {
                val database = Room.databaseBuilder(
                    getApplication(),
                    ItemDatabase::class.java,
                    "items_database"
                ).build()

                /**
                 * Este bloco de código é responsável por adicionar um novo item à lista.
                 * Ele cria um novo ItemModel e o adiciona à lista de itens.
                 */

                itemDao = database.itemDao()
                itemsLiveData = itemDao.getAll()
            }

            /**
             * Observa as alterações na lista de itens na ViewModel.
             * Quando a lista de itens é alterada, atualiza o ItemsAdapter com a nova lista.
             */

                fun addItem(item: String) {
                    viewModelScope.launch(Dispatchers.IO) {
                        val newItem = ItemModel(name = item)
                        itemDao.insert(newItem)
                    }
                }

                /**
                 * Este método é responsável por remover um item da lista.
                 * Ele remove o item da lista de itens.
                 *
                 * @param item O item a ser removido.
                 */

                    fun removeItem(item: ItemModel) {
                        viewModelScope.launch(Dispatchers.IO) {
                            itemDao.delete(item)
                        }
                    }
                }
