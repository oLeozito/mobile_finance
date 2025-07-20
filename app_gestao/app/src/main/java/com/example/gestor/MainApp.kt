package com.example.gestor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    var isMenuExpanded by remember { mutableStateOf(false) }
    val nomeDaPessoa = "Sabrinna"
    val mainOrangeColor = Color(0xFFFF8C00)
    val selectedOrangeColor = Color(0xFFCC5500)

    var selectedItem by remember { mutableStateOf(2) }

    val items = listOf("Histórico", "Investir", "Home", "Carteira", "Mais")
    val icons = listOf(
        Icons.Filled.SwapHoriz,
        Icons.Filled.BarChart,
        Icons.Filled.Home,
        Icons.Filled.Wallet,
        Icons.Filled.MoreVert
    )

    Scaffold(
        topBar = {
            TopBar(nomeDaPessoa, mainOrangeColor)
        },
        bottomBar = {
            BottomNavigationBar(items, icons, selectedItem, mainOrangeColor, selectedOrangeColor) {
                selectedItem = it
            }
        },
        floatingActionButton = {
            if (selectedItem == 2) {
                FloatingActionButton(
                    onClick = {isMenuExpanded = !isMenuExpanded},
                    containerColor = mainOrangeColor,
                    contentColor = Color.White,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(55.dp)
                        .offset(x = (-2).dp, y = (-2).dp)
                ) {
                    Text(text = "+", fontSize = 30.sp)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                when (selectedItem) {
                    0 -> HistoricoScreen()
                    1 -> InvestirScreen()
                    2 -> HomeScreen()
                    3 -> CarteiraScreen()
                    4 -> MaisScreen()
                }
                if (isMenuExpanded) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 80.dp, end = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(1.dp) // espaçamento horizontal entre os itens
                    ) {
                        Button(
                            onClick = { /* ação 1 */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2E2E2E)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.TrendingUp,
                                contentDescription = "Ganho",
                                tint = Color.Green
                            )
                        }
                        Button(
                            onClick = { /* ação 2 */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2E2E2E)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.TrendingDown,
                                contentDescription = "Gasto",
                                tint = Color.Red
                            )
                        }
                        Button(
                            onClick = { /* ação 3 */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2E2E2E)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.CreditCard,
                                contentDescription = "Cartao",
                                tint = Color(0xFFB0B0B0)
                            )
                        }
                    }
                }
            }
        }
    )
}
