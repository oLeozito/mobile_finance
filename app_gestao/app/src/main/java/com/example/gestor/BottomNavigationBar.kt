package com.example.gestor

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<String>,
    icons: List<ImageVector>,
    selectedItem: Int,
    mainColor: Color,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(containerColor = mainColor) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = item,
                        tint = if (selectedItem == index) Color.White else Color(0xFF2E2E2E)
                    )
                },
                label = {
                    Text(
                        text = item,
                        color = Color(0xFF2E2E2E)  // texto sempre cinza escuro
                    )
                },
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color(0xFF2E2E2E),
                    indicatorColor = Color(0xFF696969),   // fundo cinza claro quando selecionado
                    unselectedIconColor = Color(0xFF2E2E2E),
                    unselectedTextColor = Color(0xFF2E2E2E)
                )
            )
        }
    }
}
