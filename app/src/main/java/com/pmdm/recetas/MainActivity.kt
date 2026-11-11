package com.pmdm.recetas

import android.R.attr.onClick
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.recetas.ui.features.ButtonLikeUiState
import com.pmdm.recetas.ui.features.Receta
import com.pmdm.recetas.ui.theme.RecetasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecetasTheme {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecetaPreview() {

    // Aquí definiremos los estados.
    var likeUiState by remember {
        mutableStateOf(ButtonLikeUiState(likeCount = 8, liked = false))
    }
    val onLocallyLikePressed: () -> Unit = {
        val newLiked = !likeUiState.liked
        val newLikeCount = if (newLiked) likeUiState.likeCount + 1 else likeUiState.likeCount - 1
        likeUiState = likeUiState.copy(
            liked = newLiked,
            likeCount = newLikeCount
        )
    }
    val onRemoteLikePressed: () -> Unit = {
        val newLikeCount = likeUiState.likeCount + 1
        likeUiState = likeUiState.copy(
            likeCount = newLikeCount
        )
    }

    RecetasTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Receta(
                likeButtonState = likeUiState,
                recipeName = "Magdalemas de la abuela",
                recipeDescription = "Fabulosas magdalenas con pepitas de chocolate y un suave sabor a naranja.",
                recipeChef = "Carlos Arguiñano",
                onLiked = onLocallyLikePressed
            )
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = onRemoteLikePressed) {
                Text(text = "Otro usuario pulsa Like")
            }
        }
    }
}