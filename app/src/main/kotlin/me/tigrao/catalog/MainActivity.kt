package me.tigrao.catalog

import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject

class MainActivity: AppCompatActivity(R.layout.activity_main) {
    private val navigator = AppNavigator(this, R.id.container)

    private val navigatorHolder by inject<NavigatorHolder>()

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}
