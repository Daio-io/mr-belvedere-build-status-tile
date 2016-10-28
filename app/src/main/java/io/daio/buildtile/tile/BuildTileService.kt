package io.daio.buildtile.tile

import android.service.quicksettings.TileService
import android.util.Log
import io.daio.buildtile.Constants
import io.daio.buildtile.api.jobs.BelvedereJobsAPI
import io.daio.buildtile.api.model.Job

abstract class BuildTileService : TileService() {

    abstract val buildName: String

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
        // TODO SET TILE ICON FOR BUILD STATUS
        qsTile.label = "${job?.name}\n ${job?.version}"
        qsTile.updateTile()
    }

    private fun request() {

        jobsApi.jobs({
            val job = it?.filter {
                it.name.equals(buildName)
            }
            updateTile(job?.first())
        })
    }

}
