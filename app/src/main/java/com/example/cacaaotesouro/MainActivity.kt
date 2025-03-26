package com.example.cacaaotesouro

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cacaaotesouro.ui.theme.CacaAoTesouroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "/d1"

                ){
                composable("/d1") {Tela(
                    "Dica 1: aaa",
                    clickb1 = {navController.navigate("/d2")},
                    clickb2 = {}
                )}
                composable("/d2") {Tela(
                    "Dica 2: bbb",
                    clickb1 = {navController.navigate("/d3")},
                    clickb2 = {navController.navigate("/d1")}
                )}
                composable("/d3") {Tela(
                    "Dica 3: ccc",
                    clickb1 = {},
                    clickb2 = {navController.navigate("/d2")}
                )}
            }

        }
    }
}

@Composable
fun Tela(name: String,
         clickb1: () -> Unit,
         clickb2: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    )
    {
        Text(text=name)

        Button(onClick = clickb1) { Text(text="Proxima Dica")}
        Button(onClick = clickb2) { Text(text="Dica Anterior")}
        InputTextComponent()

    }
}

@Composable
fun InputTextComponent() {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    var isPasswordCorrect by remember { mutableStateOf<Boolean?>(null) }

    val correctPassword = "12345" // Senha de exemplo

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Resposta:",
            fontSize = 16.sp,
            color = Color.Black
        )

        OutlinedTextField(
            value = textState,
            onValueChange = {
                textState = it
                isPasswordCorrect = it.text == correctPassword
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color(0xFFD1D1D1), RoundedCornerShape(8.dp)),
            placeholder = { Text("Digite sua senha") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        // Exibe a validação da senha
        isPasswordCorrect?.let {
            Text(
                text = if (it) "Senha correta!" else "Senha incorreta",
                color = if (it) Color.Green else Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
