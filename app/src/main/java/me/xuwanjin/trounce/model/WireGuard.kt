package me.xuwanjin.trounce.model


data class WireGuard(
    val id: String,
    var isInActive: Boolean,
    var wireGuardInterface: WireGuardInterface,
    var peerList: List<Peer>,
)

data class WireGuardInterface(
    val name: String,
    val publicKey: String,
    val addresses: String,
    val DnsServers: String,
)

data class Peer(
    val publicKey: String,
    val preSharedKey: String,
    val endpoint: String,
    val allowedIps: String,
)