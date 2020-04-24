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
import com.yukarlo.coronow.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.dialog_fragment.*
import kotlinx.android.synthetic.main.motion_header.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var activityBinding: ActivityMainBinding

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)

        activityBinding.apply {
            setContentView(root)

            motionHeader.motionHeaderInformation
                .setOnClickListener {
                    showInformationDialog()
                }

            motionHeader.motionHeaderBack
                .setOnClickListener {
                    onBackPressed()
                }
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val title = when (destination.id) {
                R.id.HomeFragment -> R.string.summary
                R.id.ContinentsFragment -> R.string.continent
                R.id.CountriesFragment -> R.string.countries
                else -> R.string.app_name
            }

            activityBinding.mainMotionAppBar.motionHeaderLabel.text = getString(title)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_information -> {
                showInformationDialog()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showInformationDialog() {
        val inflater = layoutInflater

        val view = inflater.inflate(R.layout.dialog_fragment, null)
        val dialog = AlertDialog
            .Builder(this)
            .setView(view)
            .create()

        dialog.window?.apply {
            attributes.run {
                y = activityBinding.motionHeader.motionLayout.height
                gravity = Gravity.TOP
            }
        }

        dialog.show()

        dialog.dialogCreatedBy.movementMethod =
            LinkMovementMethod.getInstance()

        dialog.dialogNovelApi.movementMethod =
            LinkMovementMethod.getInstance()
    }
}