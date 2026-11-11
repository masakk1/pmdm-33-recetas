package com.pmdm.recetas.ui.composables.buttonlike

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ButtonLike(
    liked: Boolean,
    likes: Int,
    onLiked: () -> Unit
) {
    Box() {
        IconButton(
            onClick = onLiked,
            modifier = Modifier
                .clip(ButtonDefaults.shape)
                .background(ButtonDefaults.buttonColors().containerColor)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorite",
                tint = if (liked)
                    ButtonDefaults.buttonColors().contentColor
                else
                    ButtonDefaults.buttonColors().disabledContentColor,

                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
        Badge(modifier = Modifier.align(Alignment.BottomEnd)) {
            Text( if (likes >= 100 ) "99+" else likes.toString())
        }
    }
}

@Composable
@Preview
fun ButtonLikePreview4Likes() {
    var liked by remember { mutableStateOf(false) }
    ButtonLike(
        liked = liked,
        likes = 4,
        onLiked = { liked = !liked }
    )
}
@Composable
@Preview
fun ButtonLikePreviewLikedManyLikes() {
    var liked by remember { mutableStateOf(true) }
    ButtonLike(
        liked = liked,
        likes = 100,
        onLiked = { liked = !liked }
    )
}