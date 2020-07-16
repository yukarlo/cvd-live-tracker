package com.yukarlo.coronow

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.github.javiersantos.appupdater.AppUpdater
import com.github.javiersantos.appupdater.enums.UpdateFrom
import com.yukarlo.coronow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_fragment.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var activityBinding: ActivityMainBinding

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)

        activityBinding.apply {
            setContentView(root)
            setSupportActionBar(toolbar)
        }

        AppUpdater(this)
            .setUpdateFrom(UpdateFrom.JSON)
            .setUpdateJSON(BuildConfig.GRADLE_UPDATE_JSON_CHANGELOG)
            .start()

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_information -> {
            showInformationDialog()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun showInformationDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_fragment, null)
        val dialog = AlertDialog
            .Builder(this)
            .setView(view)
            .create()

        dialog.window?.apply {
            attributes.run {
                y = toolbar.height
                gravity = Gravity.TOP
            }
        }

        dialog.run {
            show()
            dialogCreatedBy.movementMethod =
                LinkMovementMethod.getInstance()
            dialogNovelApi.movementMethod =
                LinkMovementMethod.getInstance()
            dialogIllustrationSourcesTitle.movementMethod =
                LinkMovementMethod.getInstance()
            dialogWHOSource.movementMethod =
                LinkMovementMethod.getInstance()
            dialogCDCSource.movementMethod =
                LinkMovementMethod.getInstance()
            dialogLatestAppUpdate.movementMethod =
                LinkMovementMethod.getInstance()
        }
    }
}