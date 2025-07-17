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
    mainOrangeColor: Color,
    selectedOrangeColor: Color,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(containerColor = mainOrangeColor) {
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
                onClick = { onItemSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    indicatorColor = selectedOrangeColor,
                    unselectedIconColor = Color.White.copy(alpha = 0.7f),
                    unselectedTextColor = Color.White.copy(alpha = 0.7f)
                )
            )
        }
    }
}
