package com.sidharth.inappupdate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.ActivityResult.RESULT_IN_APP_UPDATE_FAILED
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.sidharth.inappupdate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val appUpdateManager by lazy { AppUpdateManagerFactory.create(this) }
    private lateinit var installStateUpdatedListener: InstallStateUpdatedListener

    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        when (result.resultCode) {
            RESULT_OK -> {
                Log.d("Update flow result", "OKAY ${result.resultCode}")
            }

            RESULT_CANCELED -> {
                Log.d("Update flow result", "CANCELLED ${result.resultCode}")
            }

            RESULT_IN_APP_UPDATE_FAILED -> {
                Log.d("Update flow result", "FAILED ${result.resultCode}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)
        initializeViewsAndListeners()
        checkForUpdateAvailability()
    }

    private fun initializeViewsAndListeners() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackbarForCompleteUpdate()
            }

            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                startImmediateUpdate()
            }
        }

        installStateUpdatedListener = InstallStateUpdatedListener { state ->
            when (state.installStatus()) {
                InstallStatus.DOWNLOADING -> {
                    val bytesDownloaded = state.bytesDownloaded()
                    val totalBytesToDownload = state.totalBytesToDownload()
                    Log.d(
                        "Download Percentage",
                        "${bytesDownloaded / totalBytesToDownload * 100}% downloaded"
                    )
                }

                InstallStatus.DOWNLOADED -> {
                    popupSnackbarForCompleteUpdate()
                }

                InstallStatus.INSTALLED -> {
                    appUpdateManager.unregisterListener(installStateUpdatedListener)
                }

                else -> {

                }
            }
        }
    }

    private fun checkForUpdateAvailability() {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            when {
                appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
                        && (appUpdateInfo.clientVersionStalenessDays()
                    ?: -1) >= DAYS_FOR_FLEXIBLE_UPDATE -> {
                    startImmediateUpdate()
                }

                appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
                        && (appUpdateInfo.clientVersionStalenessDays()
                    ?: -1) >= DAYS_FOR_FLEXIBLE_UPDATE -> {
                    startFlexibleUpdate()
                }
            }
        }
    }

    private fun startImmediateUpdate() {
        appUpdateManager.startUpdateFlowForResult(
            appUpdateManager.appUpdateInfo.result,
            activityResultLauncher,
            AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
        )
    }

    private fun startFlexibleUpdate() {
        appUpdateManager.registerListener(installStateUpdatedListener)

        appUpdateManager.startUpdateFlowForResult(
            appUpdateManager.appUpdateInfo.result,
            activityResultLauncher,
            AppUpdateOptions.newBuilder(AppUpdateType.FLEXIBLE).build()
        )
    }

    private fun popupSnackbarForCompleteUpdate() {
        Snackbar.make(
            activityMainBinding.root,
            "An update has just been downloaded.",
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction("RESTART") { appUpdateManager.completeUpdate() }
            show()
        }
    }

    companion object {
        const val DAYS_FOR_FLEXIBLE_UPDATE = 2
    }
}