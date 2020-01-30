package wifimulticastlock.wifi_multicast_lock

import android.content.Context
import android.net.wifi.WifiManager
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** WifiMulticastLockPlugin */
public class WifiMulticastLockPlugin : FlutterPlugin, MethodCallHandler {
    private var multicastLock: WifiManager.MulticastLock? = null
    private lateinit var context: Context

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        context = flutterPluginBinding.applicationContext
        MethodChannel(flutterPluginBinding.binaryMessenger, "wifi_multicast_lock").setMethodCallHandler(this)
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "acquire" -> {
                if (acquire())
                    result.success(null)
                else
                    result.error("WifiManager", "Cant find WifiManager", null)
            }
            "release" -> {
                if (release())
                    result.success(null)
                else
                    result.error("Released", "Couldn't release lock. Is referenceCounted enabled?", null)
            }
            "setReferenceCounted" -> {
                setReferenceCounted(call.arguments as Boolean)
                result.success(null)
            }
            "isHeld" -> {
                result.success(isHeld())
            }
            else -> result.notImplemented()
        }
    }

    private fun isHeld() = multicastLock?.isHeld ?: false

    private fun setReferenceCounted(refCounted: Boolean) {
        val lock = multicastLock ?: return
        lock.setReferenceCounted(refCounted)
    }

    private fun acquire(): Boolean {
        val manager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
                ?: return false

        multicastLock = manager.createMulticastLock("wifi_multicast_lock")
        multicastLock!!.acquire()
        return true
    }

    private fun release(): Boolean {
        val lock = multicastLock ?: return false

        lock.release()
        return true
    }
}
