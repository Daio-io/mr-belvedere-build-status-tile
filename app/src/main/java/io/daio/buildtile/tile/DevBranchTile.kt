package io.daio.buildtile.tile


class DevBranchTile: BuildTileService() {

    override val buildName: String
        get() = "android-develop"

}