package com.fgr.neonews.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.fgr.neonews.navigation.NavGraph
import com.fgr.neonews.ui.theme.NeoNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoNewsTheme {
                NavGraph(navHostController = rememberNavController())
//                val scope = rememberCoroutineScope()
//                LaunchedEffect(Unit) {
//                    scope.launch {
//                        try {
//                            val callHotNews = ApiConfig.apiService(BuildConfig.BASE_URL)
//                                .getHotNews(apiKey = BuildConfig.NEWS_API_KEY)
//                            if (callHotNews.status == "ok") {
//                                Log.e("success" ,callHotNews.articles.toString())
//                            } else {
//                                // if status is 401 = Invalid API Key
//                                Log.e("failure" ,callHotNews.status)
//                            }
//                        } catch (e: Exception) {
//                            Log.e("exception", e.message.toString())
//                        }
//                    }
//                }
            }
        }
    }
}