package com.pmdm.recetas.ui.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
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
import com.pmdm.recetas.ui.composables.buttonlike.ButtonLike

data class ButtonLikeUiState(
    val likeCount: Int,
    val liked: Boolean
)

@Composable
fun Receta(
    likeButtonState: ButtonLikeUiState,
    recipeName: String,
    recipeDescription: String,
    recipeChef: String,
    onLiked: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(recipeName, style = MaterialTheme.typography.headlineSmall)
            Text(recipeDescription)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier .fillMaxWidth()
            ) {
                Text(recipeChef)
                ButtonLike(
                    liked = likeButtonState.liked,
                    likes = likeButtonState.likeCount,
                    onLiked = onLiked
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RecetaPreview() {
    var likeButtonUiState by remember {
        mutableStateOf(ButtonLikeUiState(4, true))
    }
    Receta(
        likeButtonState = likeButtonUiState,
        recipeName = "Magdalenas de la abuela",
        recipeDescription = "Fabulosas magdalenas con pepitas de chocolate y un suave sabor a naranja.",
        recipeChef = "Carlos Arguiñano",
        onLiked = {
            val newLiked = !likeButtonUiState.liked
            val newCount = if (newLiked) likeButtonUiState.likeCount + 1 else likeButtonUiState.likeCount - 1
            likeButtonUiState = likeButtonUiState.copy(
                liked = newLiked,
                likeCount = newCount
            )
        }
    )
}