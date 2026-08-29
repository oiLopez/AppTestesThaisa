package thatha.teste.apptestesthaisa.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import giovane.giraldi.app_tarefas.components.ItemTarefa
import thatha.teste.apptestesthaisa.models.Tarefa

@Composable
fun TelaTarefas() {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        var novaTarefa by remember {
            mutableStateOf("")
        }

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(
                    value = novaTarefa,
                    onValueChange = {
                        novaTarefa = it
                    },
                    label = {
                        Text("Nova tarefa")
                    },
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = {

                        if (novaTarefa.isNotBlank()) {

                            val tarefa = Tarefa(
                                id = (tarefas.maxOfOrNull { it.id } ?: 0) + 1,
                                descricao = novaTarefa.trim()
                            )

                            tarefas = tarefas + tarefa

                            novaTarefa = ""
                        }
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Adicionar")
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

                items(
                    items = tarefas,
                    key = { it.id }
                ) { tarefa ->

                    ItemTarefa(
                        tarefa = tarefa,
                        onConcluidaChange = { concluida ->

                            tarefas = tarefas.map { item ->

                                if (item.id == tarefa.id) {

                                    item.copy(
                                        concluida = concluida
                                    )

                                } else {

                                    item
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewTelaTarefas() {
    TelaTarefas()
}