package io.daio.buildtile.tile

class ReleaseBranchTile: BuildTileService() {

    override val buildName: String
        get() = "android-release"

}