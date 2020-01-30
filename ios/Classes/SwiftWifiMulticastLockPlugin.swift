import Flutter
import UIKit

public class SwiftWifiMulticastLockPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "wifi_multicast_lock", binaryMessenger: registrar.messenger())
    let instance = SwiftWifiMulticastLockPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result(true)
  }
}
