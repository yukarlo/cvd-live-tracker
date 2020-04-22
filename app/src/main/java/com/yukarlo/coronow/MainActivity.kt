package com.yukarlo.coronow

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.size
import com.yukarlo.coronow.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.dialog_fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var activityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        activityBinding.apply {
            setContentView(root)
        }

        activityBinding.motionHeader.motionHeaderInformation
            .setOnClickListener {
                showInformationDialog()
            }

        activityBinding.motionHeader.motionHeaderBack
            .setOnClickListener {
                finish()
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
                y = activityBinding.mainMotionAppBar.findViewById<MotionLayout>(R.id.motionLayout).height
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