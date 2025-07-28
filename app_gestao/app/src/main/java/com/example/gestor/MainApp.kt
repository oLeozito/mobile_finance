package com.example.gestor

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.zIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    var isMenuExpanded by remember { mutableStateOf(false) }
    val nomeDaPessoa = "Sabrinna"
    val mainColor = Color(0xFFF5F5F5)
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

    val rotationAngle by animateFloatAsState(
        targetValue = if (isMenuExpanded) 45f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    Scaffold(
        topBar = {
            TopBar(nomeDaPessoa, mainColor)
        },
        bottomBar = {
            BottomNavigationBar(items, icons, selectedItem, mainColor) { selectedItem = it }
        },
        floatingActionButton = {
            if (selectedItem == 2) {
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Fundo clicável para fechar o menu
                    if (isMenuExpanded) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable(onClick = { isMenuExpanded = false })
                                .background(Color.Black.copy(alpha = 0.001f)) // quase invisível
                                .zIndex(0f)
                        )
                    }

                    // Botões animados
                    AnimatedVisibility(
                        visible = isMenuExpanded,
                        enter = slideInVertically(
                            initialOffsetY = { 200 },
                            animationSpec = tween(durationMillis = 300)
                        ) + fadeIn(animationSpec = tween(300)),
                        exit = slideOutVertically(
                            targetOffsetY = { 200 },
                            animationSpec = tween(durationMillis = 300)
                        ) + fadeOut(animationSpec = tween(300)),
                        modifier = Modifier
                            .padding(bottom = 75.dp, end = 3.dp) // <- Ajustado aqui!
                            .zIndex(1f)
                    )
                    {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(0.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            val buttonColor = Color(0xFFF5F5F5)
                            val iconDefaultColor = Color(0xFF2E2E2E)

                            Button(
                                onClick = { /* ação do botão de ganho */ },
                                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.TrendingUp,
                                    contentDescription = "Ganho",
                                    tint = iconDefaultColor
                                )
                            }

                            Button(
                                onClick = { /* ação do botão de gasto */ },
                                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.TrendingDown,
                                    contentDescription = "Gasto",
                                    tint = iconDefaultColor
                                )
                            }

                            Button(
                                onClick = { /* ação do botão de cartão */ },
                                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CreditCard,
                                    contentDescription = "Cartão",
                                    tint = iconDefaultColor
                                )
                            }
                        }
                    }

                    // Botão principal "+"
                    FloatingActionButton(
                        onClick = { isMenuExpanded = !isMenuExpanded },
                        containerColor = Color(0xFF2E2E2E),
                        contentColor = Color.White,
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(end = 10.dp, bottom = 10.dp)
                            .size(55.dp)
                            .zIndex(2f) // acima de tudo
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Menu",
                            modifier = Modifier.rotate(rotationAngle)
                        )
                    }
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
            }
        }
    )
}
