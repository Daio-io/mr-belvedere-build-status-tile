package io.daio.buildtile.tile

import android.graphics.drawable.Icon
import android.service.quicksettings.TileService
import io.daio.buildtile.Constants
import io.daio.buildtile.R
import io.daio.buildtile.api.jobs.BelvedereJobsAPI
import io.daio.buildtile.api.model.Job

abstract class BuildTileService : TileService() {

    abstract val jobId: String

    private val jobsApi = BelvedereJobsAPI(Constants.API_KEY)

    override fun onClick() {
        super.onClick()
        request()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartListening() {
        super.onStartListening()
        request()
    }

    private fun updateTile(job: Job?) {
        when (job?.status) {
            "success" -> qsTile.icon = Icon.createWithResource(applicationContext, R.drawable.success_icon)
            "building" -> qsTile.icon = Icon.createWithResource(applicationContext, R.drawable.building_icon)
            "failed" -> qsTile.icon = Icon.createWithResource(applicationContext, R.drawable.failed_icon)
        }

        qsTile.label = "${job?.name}\n ${job?.version}"
        qsTile.updateTile()
    }

    private fun request() {

        jobsApi.job(jobId, {
            updateTile(it)
        })
    }

}
