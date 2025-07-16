package com.example.gestor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle // Ícone para Perfil
import androidx.compose.material.icons.filled.Home // Ícone para Home (Casinha)
import androidx.compose.material.icons.filled.Menu // Ícone para o símbolo do app
import androidx.compose.material.icons.filled.MoreVert // Ícone para Mais (Três pontinhos)
import androidx.compose.material.icons.filled.Notifications // Ícone do sininho
import androidx.compose.material.icons.filled.SwapHoriz // Ícone para Transações (pode ser usado para duas setinhas)
import androidx.compose.material.icons.filled.Wallet // Ícone para Carteira
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestor.ui.theme.GestorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {
    val nomeDaPessoa = "Sabrinna"

    // Definindo a cor laranja principal para as barras
    val mainOrangeColor = Color(0xFFFF8C00) // Laranja Tangerina
    // Definindo um laranja mais escuro para a seleção (exemplo: Laranja Queimado)
    val selectedOrangeColor = Color(0xFFCC5500) // Pode ajustar este valor

    var selectedItem by remember { mutableStateOf(2) }

    val items = listOf("Perfil", "Histórico", "Home", "Carteira", "Mais")
    val icons = listOf(
        Icons.Filled.AccountCircle,
        Icons.Filled.SwapHoriz,
        Icons.Filled.Home,
        Icons.Filled.Wallet,
        Icons.Filled.MoreVert
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Símbolo do App",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Olá, $nomeDaPessoa!",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Ação ao clicar no sininho */ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notificações",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = mainOrangeColor
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = mainOrangeColor // Cor laranja principal
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = item,
                                tint = if (selectedItem == index) Color.White else Color.White.copy(alpha = 0.7f)
                            )
                        },
                        label = {
                            Text(
                                text = item,
                                color = if (selectedItem == index) Color.White else Color.White.copy(alpha = 0.7f)
                            )
                        },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        // --- AQUI ESTÁ A MUDANÇA PARA A COR DE SELEÇÃO ---
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White, // Ícone branco quando selecionado
                            selectedTextColor = Color.White, // Texto branco quando selecionado
                            indicatorColor = selectedOrangeColor, // Cor de fundo do indicador de seleção (o "retângulo" por trás)
                            unselectedIconColor = Color.White.copy(alpha = 0.7f), // Ícone branco opaco quando não selecionado
                            unselectedTextColor = Color.White.copy(alpha = 0.7f) // Texto branco opaco quando não selecionado
                        )
                    )
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Conteúdo da tela: ${items[selectedItem]}", fontSize = 24.sp)
            }
        }
    )
}