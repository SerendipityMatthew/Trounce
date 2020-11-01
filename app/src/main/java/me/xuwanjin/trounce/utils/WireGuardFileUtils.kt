package me.xuwanjin.trounce.utils

import android.util.Log
import me.xuwanjin.trounce.model.Peer
import me.xuwanjin.trounce.model.WireGuard
import me.xuwanjin.trounce.model.WireGuardInterface
import java.io.File
import java.io.FileNotFoundException
import kotlin.text.Charsets.UTF_8

fun readWireGuardConfigFile(path: String): WireGuard? {
    var configFile: String = ""
    var address: String = "";
    var privateKey: String = "";
    var dns: String = "";

    var allowedIPs: String = "";
    var endpoint: String = "";
    var preSharedKey: String = "";
    var publicKey: String = "";
    try {
        configFile = File(path).readText(charset = UTF_8)
    } catch (exception: FileNotFoundException) {
        exception.printStackTrace()
    }
    File(path).forEachLine {
        var stringList: List<String> = it.split("=")
        if (stringList == null || stringList.isEmpty() || stringList.size == 1) {
            return@forEachLine
        }
        var configKey = stringList[0].trim()
        var configValue = stringList[1].trim()
        if (configKey.equals("Address", true)) {
            address = configValue
        }
        if (configKey.equals("DNS", true)) {
            dns = configValue
        }
        if (configKey.equals("PrivateKey", true)) {
            privateKey = "$configValue="
        }
        if (configKey.equals("AllowedIPs", true)) {
            allowedIPs = configValue
        }
        if (configKey.equals("Endpoint", true)) {
            endpoint = configValue
        }
        if (configKey.equals("PublicKey", true)) {
            publicKey = "$configValue="
        }
        if (configKey.equals("PublicKey", true)) {
            preSharedKey = "$configValue="
        }
    }

    val wireGuard = WireGuard(
        id = "1",
        isInActive = false,
        wireGuardInterface = WireGuardInterface(
            "Matthew",
            publicKey = publicKey,
            addresses = address,
            dnsServers = dns,
        ),
        peerList = listOf(
            Peer(
                publicKey = publicKey,
                preSharedKey = preSharedKey,
                endpoint = endpoint,
                allowedIps = allowedIPs,
            )
        ),
    )
    return wireGuard
}