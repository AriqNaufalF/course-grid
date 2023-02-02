package com.example.coursesgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursesgrid.data.DataSource
import com.example.coursesgrid.model.Topic
import com.example.coursesgrid.ui.theme.CoursesGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesGridTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoursesScreen()
                }
            }
        }
    }
}

@Composable
fun CoursesScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(DataSource.topics) {
            CourseCard(topic = it)
        }
    }
}

@Composable
fun CourseCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier, elevation = 4.dp) {
        Row {
            Image(
                painter = painterResource(topic.imageRes),
                contentDescription = stringResource(topic.name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 68.dp, height = 68.dp)
                    .aspectRatio(1f)
            )
            Column {
                Text(
                    text = stringResource(topic.name),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp)
                            .size(12.dp)
                    )
                    Text(text = topic.availableCourse.toString(), style = MaterialTheme.typography.caption)
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    CoursesGridTheme {
        CourseCard(Topic(R.string.photography, 321, R.drawable.photography))
    }
}