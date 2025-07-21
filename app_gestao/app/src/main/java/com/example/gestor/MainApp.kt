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

    // Animação da rotação do botão "+"
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
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Área invisível que detecta clique fora dos botões
                    if (isMenuExpanded) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable(onClick = { isMenuExpanded = false })
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
                        ) + fadeOut(animationSpec = tween(300))
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .offset(y = (-70).dp)
                                .offset(x = (-3).dp)
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
                            .rotate(rotationAngle)
                    ) {
                        Text(text = "+", fontSize = 30.sp, color = Color.White)
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
