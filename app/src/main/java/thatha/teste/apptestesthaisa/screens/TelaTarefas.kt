package thatha.teste.apptestesthaisa.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import thatha.teste.apptestesthaisa.components.ItemTarefa
import thatha.teste.apptestesthaisa.models.Tarefa

@Composable
fun TelaTarefas() {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        var tarefas by remember {
            mutableStateOf(
                listOf(
                    Tarefa(
                        id = 1,
                        descricao = "Estudar Kotlin"
                    ),
                    Tarefa(
                        id = 2,
                        descricao = "Praticar Composable"
                    ),
                    Tarefa(
                        id = 3,
                        descricao = "Estudar para P1"
                    )
                )
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(
                tarefas,
                key = { it.id }
            ) { tarefa ->

                ItemTarefa(
                    tarefa = tarefa,
                    onConcluidaChange = { concluida ->

                        tarefas = tarefas.map {
                            if (it.id == tarefa.id) {
                                it.copy(concluida = concluida)
                            } else {
                                it
                            }
                        }

                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewTelaTarefas() {
    TelaTarefas()
}
