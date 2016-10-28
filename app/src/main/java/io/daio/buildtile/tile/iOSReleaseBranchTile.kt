package io.daio.buildtile.tile

class iOSReleaseBranchTile: BuildTileService() {
    override val buildName: String
        get() = "ios-release"
}