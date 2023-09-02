package com.jp.navigationcontroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            App()

        }
    }
}

@Composable
fun App() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {

            /* we can navigate through passing function as parameter */
            LoginScreen {
                navController.navigate("registration")
            }
        }
        composable("registration") {
            /* we can navigate through passing navController object */
            RegistrationScreen(navController)
        }
        composable("main/{email}/{password}", arguments = listOf(
            /* set no of arguments we want to pass */
            navArgument("email") {
                type = NavType.StringType
            },
            navArgument("password") {
                type = NavType.StringType
            }
        )) {
            /* get arguments using key */
            val email = it.arguments?.getString("email")
            val password = it.arguments?.getString("password")
            MainScreen(email, password)
        }
    }

}

@Composable
fun RegistrationScreen(navController: NavHostController) {
    Text(text = "Registration",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .clickable {
                /* pass string argument with route */
                navController.navigate("main/${"jignesh9115@gmail.com"}/${"jignesh@9115"}")
            }
            .padding(10.dp))
}

@Composable
fun LoginScreen(onClick: () -> Unit) {
    Text(text = "Login",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(10.dp)
    )
}

@Composable
fun MainScreen(email: String?, password: String?) {
    Text(
        text = "Main -\nemail : $email \npassword : $password",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(10.dp)
    )
}
