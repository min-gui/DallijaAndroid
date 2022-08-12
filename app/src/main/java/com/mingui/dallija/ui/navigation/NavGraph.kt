package com.mingui.dallija.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mingui.dallija.ui.AnimatedSplashScreen
import com.mingui.dallija.ui.theme.DallijaTheme

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route){
            AnimatedSplashScreen(navController)
        }
        composable(route = Screen.Home.route){
            Box(modifier = Modifier.fillMaxSize())
            Text(text = "안녕하세요")
        }
    }
}

@Preview
@Composable
fun NavPreview(){
    DallijaTheme() {
        Surface(color = MaterialTheme.colors.background) {
            //SetupNavGraph(navController = )
        }
    }
    
}