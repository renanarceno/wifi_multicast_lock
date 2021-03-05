import 'dart:async';

import 'package:flutter/services.dart';

class WifiMulticastLock {
  static const MethodChannel _channel = const MethodChannel('wifi_multicast_lock');
  static WifiMulticastLock? _lock;

  factory WifiMulticastLock() => _lock ??= WifiMulticastLock._();

  WifiMulticastLock._();

  Future<void> acquire() async => await _channel.invokeMethod('acquire');

  Future<void> release() async => await _channel.invokeMethod('release');

  Future<bool?> isHeld() async => await _channel.invokeMethod('isHeld');

  Future<void> setReferenceCounted(bool refCounted) async => await _channel.invokeMethod('setReferenceCounted', refCounted);
}
