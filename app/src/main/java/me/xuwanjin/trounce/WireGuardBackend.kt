package me.xuwanjin.trounce

class WireGuardBackend {
    companion object {
        init {
            System.loadLibrary("wireguard")
        }
    }

    external fun wireGuardTurnOn(configName: String, fd: Int, config: String): Int
}
