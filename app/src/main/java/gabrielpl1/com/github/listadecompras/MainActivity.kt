package gabrielpl1.com.github.listadecompras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import gabrielpl1.com.github.listadecompras.ui.theme.ListaDeComprasTheme
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    // Função que é chamada quando a Activity é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        // Define o layout XML que será usado nesta Activity
        setContentView(R.layout.activity_main)

        // Chama o método onCreate da classe pai (AppCompatActivity) após setar o layout
        super.onCreate(savedInstanceState)

        // Inicializa a Toolbar a partir do layout e define como a barra de ações (ActionBar) da Activity
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Define o título da ActionBar para "Lista de Compras"
        supportActionBar?.title = "Lista de Compras"

        // Referencia o RecyclerView a partir do layout XML
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Cria uma instância do ItemsAdapter, que vai gerenciar os dados do RecyclerView
        val itemsAdapter = ItemsAdapter()

        // Define o adapter do RecyclerView para o itemsAdapter criado
        recyclerView.adapter = itemsAdapter

        // Referencia o botão e o campo de texto a partir do layout
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)

        // Define o comportamento ao clicar no botão
        button.setOnClickListener {
            // Verifica se o campo de texto está vazio. Se estiver, exibe uma mensagem de erro e interrompe a execução.
            if (editText.text.isEmpty()) {
                editText.error = "Preencha um valor" // Mostra um erro no campo de texto se estiver vazio
                return@setOnClickListener // Sai da função, sem executar o restante do código
            }

            // Cria um novo ItemModel com o texto inserido no campo de texto
            val item = ItemModel(
                name = editText.text.toString(), // Define o nome do item como o texto inserido
                onRemove = {
                    // Define a ação de remover o item, chamando o método removeItem do adapter
                    itemsAdapter.removeItem(it)
                }
            )

            // Adiciona o novo item ao adapter, que irá atualizá-lo no RecyclerView
            itemsAdapter.addItem(item)

            // Limpa o texto do campo de edição após adicionar o item à lista
            editText.text.clear()
        }
    }

}