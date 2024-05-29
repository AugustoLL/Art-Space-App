package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.data.DataSource
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    val gallery = DataSource().loadGallery()
    var currentPhotoIndex by remember { mutableStateOf(0) }
    var currentPhoto by remember { mutableStateOf(gallery[currentPhotoIndex]) }

    Column (
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card {
            Card {
                Image(
                    painter = painterResource(id = currentPhoto.imageResourceId),
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = currentPhoto.title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${currentPhoto.author} (${currentPhoto.year})",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            InteractiveButton(
                text = R.string.previous,
                modifier = Modifier.size(width = 130.dp, height = 40.dp),
                onClick = {
                    if (currentPhotoIndex == 0)
                        currentPhotoIndex = gallery.size - 1
                    else
                        currentPhotoIndex--
                    currentPhoto = gallery[currentPhotoIndex]
                }
            )
            Spacer(modifier = Modifier.width(80.dp))
            InteractiveButton(
                text = R.string.next,
                modifier = Modifier.size(width = 120.dp, height = 40.dp),
                onClick = {
                    if (currentPhotoIndex == gallery.size - 1)
                        currentPhotoIndex = 0
                    else
                        currentPhotoIndex++
                    currentPhoto = gallery[currentPhotoIndex]
                }
            )
        }
    }
}

@Composable
private fun InteractiveButton(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        onClick = onClick,
    ) {
        Text(stringResource(id = text))
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}