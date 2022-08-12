package com.mingui.dallija.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mingui.dallija.ui.navigation.Screen
import com.mingui.dallija.ui.theme.DallijaTheme
import com.mingui.dallija.ui.theme.Teal200
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true ){
        startAnimation = true
        delay(4000)
        navController.navigate(Screen.Home.route)
    }
    Splash(alphaAnim.value )
}

@Composable
fun Splash(alpha : Float) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.secondary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
            imageVector = Icons.Default.Email,
            contentDescription = "Logo Icon",
            tint = Color.White
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    DallijaTheme() {
        Splash(1f)
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, name = "DAR")
@Composable
fun SplashScreenDarkPreview() {
    DallijaTheme() {
        Splash(1f)
    }
}
