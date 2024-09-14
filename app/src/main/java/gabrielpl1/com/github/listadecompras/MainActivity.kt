package gabrielpl1.com.github.listadecompras

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

// Activity principal da aplicação que controla a UI
class MainActivity : AppCompatActivity() {

    // Instancia o ViewModel usando a delegação 'by viewModels()' para observar os dados
    val viewModel: ItemsViewModel by viewModels()

    // Método chamado quando a Activity é criada
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Define o layout da Activity
        setContentView(R.layout.activity_main)

        // Configura a Toolbar e define o título
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Lista de Compras"

        // Configura o RecyclerView e o seu adaptador
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter()
        recyclerView.adapter = itemsAdapter

        // Referência para o botão e o campo de texto (EditText)
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)

        // Define ação ao clicar no botão
        button.setOnClickListener {

            // Verifica se o EditText está vazio, e exibe um erro se for o caso
            if (editText.text.isEmpty()) {
                editText.error = "Preencha um valor"
                return@setOnClickListener // Sai do evento de clique se não houver texto
            }

            // Adiciona o item à lista no ViewModel
            viewModel.addItem(editText.text.toString())

            // Limpa o campo de texto após a adição do item
            editText.text.clear()
        }

        // Observa as mudanças nos dados da lista (LiveData) e atualiza o RecyclerView
        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items) // Atualiza o adaptador com os novos itens
        }
    }
}
