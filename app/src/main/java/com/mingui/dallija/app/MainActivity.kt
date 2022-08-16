package com.mingui.dallija.app

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mingui.dallija.R
import com.mingui.dallija.presentation.theme.DallijaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DallijaTheme {
                /*val navController = rememberNavController()
                SetupNavGraph(navController = navController)*/
                MyApp()
                //OnboardingScreen()
            }
        }
    }
}

@Composable
fun CardContent(name: String) {
    val context = LocalContext.current
    var expanded by rememberSaveable { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    //val extraPadding = if (expanded) 48.dp else 0.dp


    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
//        Text(text = name, modifier = Modifier.padding(24.dp))
        Row(
            modifier = Modifier
                .padding(24.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello,")
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (expanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4)
                    )
                }
            }
            IconButton(onClick = {
                Toast.makeText(context, "안녕하세요", Toast.LENGTH_LONG).show()
                expanded = !expanded

            }, modifier = Modifier.weight(1f)) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(id = R.string.show_less)
                    } else {
                        stringResource(id = R.string.show_more)
                    }
                )
            }

        }


    }
}

@Composable
fun Greeting(name: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }

}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    /*var shouldShowOnboarding by remember {
        mutableStateOf(true)
    }*/

    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "코드랩 베이직에 온걸 환영합니다.")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text(text = "Continue")
            }

        }


    }
}

@Composable
private fun Greetings(names: List<String> = List(500) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun MyApp(names: List<String> = listOf("World", "Compose", "fdfdf", "dfdfdf")) {
    /*androidx.compose.material.Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            for (name in names) {
                Greeting(name = name)
            }
        }
    }*/
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Log.e("TAG", "작동합니다.")
    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        Greetings()
    }


}

@Preview(showBackground = true, widthDp = 320, heightDp = 320, name = "OnBoardingPreview")
@Composable
fun OnBoardingPreview() {
    DallijaTheme {
        OnboardingScreen(onContinueClicked = {})
//        MyApp()
//        val navController = rememberNavController()
//        SetupNavGraph(navController = navController)
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Composable
fun DefaultPreview() {
    DallijaTheme {
        //OnboardingScreen(onContinueClicked = {})
        Greetings()

//        val navController = rememberNavController()
//        SetupNavGraph(navController = navController)
    }
}
