package org.ucb.encuentra.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.ucb.encuentra.feature.login.presentation.screen.LoginScreen
import org.ucb.encuentra.feature.matchdetail.presentation.screen.MatchDetailScreen
import org.ucb.encuentra.feature.matchlist.presentation.screen.MatchListScreen
import org.ucb.encuentra.feature.signup.presentation.screen.RegisterScreen
import org.ucb.encuentra.feature.userstate.presentation.screen.UserStateScreen
import org.ucb.encuentra.userinformation.presentation.screen.UserInformationScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Login) {

        composable<NavRoute.Login> {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(NavRoute.MatchList) {
                        popUpTo<NavRoute.Login> { inclusive = true }
                    }
                },
                onNavigateToSignUp = { navController.navigate(NavRoute.SignUp) }
            )
        }

        composable<NavRoute.SignUp> {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(NavRoute.MatchList) {
                        popUpTo<NavRoute.Login> { inclusive = true }
                    }
                },
                onNavigateToLogin = { navController.popBackStack() }
            )
        }

        composable<NavRoute.MatchList> {
            MatchListScreen(
                onNavigateToDetail = { matchId -> navController.navigate(NavRoute.MatchDetail(matchId)) },
                onNavigateToProfile = { navController.navigate(NavRoute.Profile) }
            )
        }

        composable<NavRoute.MatchDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<NavRoute.MatchDetail>()
            MatchDetailScreen(
                matchId = route.matchId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<NavRoute.Profile> {
            UserStateScreen(
                onNavigateToLogin = {
                    navController.navigate(NavRoute.Login) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigateToUserSearch = { navController.navigate(NavRoute.UserSearch) }
            )
        }

        composable<NavRoute.UserSearch> {
            UserInformationScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}
