package io.daio.buildtile.tile

import android.service.quicksettings.TileService
import android.util.Log
import io.daio.buildtile.Constants
import io.daio.buildtile.api.jobs.BelvedereJobsAPI

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

    private fun updateTile(jobName: String?, status: String?) {
        qsTile.label = "$jobName\n $status"
        qsTile.updateTile()
    }

    private fun request() {

        jobsApi.jobs({
            val release = it?.filter {
                it.name.equals(buildName)
            }
            updateTile(release?.first()?.name, release?.first()?.status)
        })
    }

}
