package com.example.sleephelperapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ProgressBar_Splash(
    modifier: Modifier = Modifier,
    size: Dp = 100.dp,
    gridSize: Int = 3,
    cellColor: Color = Color.Magenta.copy(alpha = 1f)
) {
    // Define the grid and entry positions
    var grid by remember { mutableStateOf(Array(gridSize) { Array(gridSize) { 1 } }) }
    val entryPositions = listOf(
        Pair(2, 1), Pair(2, 2), Pair(1, 2),
        Pair(0, 2), Pair(0, 1), Pair(0, 0),
        Pair(1, 0), Pair(2, 0), Pair(2, 1)
    )

    var currentPosition by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(300)
            val newGrid = Array(gridSize) { Array(gridSize) { 1 } }
            currentPosition = (currentPosition + 1) % entryPositions.size
            val (row, col) = entryPositions[currentPosition]
            newGrid[row][col] = 0
            grid = newGrid
        }
    }

    // Calculate the size of each cell based on the overall size and grid size
    val cellSize = size / gridSize.dp

    // Render the grid
    Box(
        modifier = modifier
            .size(size)
            .graphicsLayer {
                rotationZ = 45f
                // Add these lines to prevent clipping
                transformOrigin = androidx.compose.ui.graphics.TransformOrigin.Center
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            grid.forEachIndexed { _, rowArray ->
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(1.dp),
                    horizontalArrangement = Arrangement.spacedBy(1.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rowArray.forEachIndexed { _, cellValue ->
                        val color = if (cellValue == 0) Color.Transparent else cellColor
                        Box(
                            modifier = Modifier
                                .size(cellSize.dp)  // Explicitly convert to dp
                                .background(color = color, shape = RoundedCornerShape(5.dp))
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview_() {
    ProgressBar_Splash()
}