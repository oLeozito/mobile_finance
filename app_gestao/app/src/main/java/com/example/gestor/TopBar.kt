package com.example.gestor

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(nomeDaPessoa: String, mainOrangeColor: Color) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight()
            ) {
                IconButton(
                    onClick = { /* ação ao clicar no perfil */ },
                    modifier = Modifier.offset(x = 2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Perfil",
                        modifier = Modifier.size(38.dp),
                        tint = Color(0xFF2E2E2E)
                    )
                }
                Spacer(modifier = Modifier.width(1.dp))
                Text(
                    text = "Olá, $nomeDaPessoa!",
                    modifier = Modifier.offset(y = 2.dp),
                    color = Color(0xFF2E2E2E),
                    fontSize = 18.sp
                )
            }
        },
        actions = {
            IconButton(onClick = { /* ação do sininho */ }) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notificações",
                    modifier = Modifier
                        .size(28.dp)
                        .offset(y = 2.dp),
                    tint = Color(0xFF2E2E2E)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = mainOrangeColor
        )
    )
}
