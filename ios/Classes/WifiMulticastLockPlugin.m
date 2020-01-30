#import "WifiMulticastLockPlugin.h"
#if __has_include(<wifi_multicast_lock/wifi_multicast_lock-Swift.h>)
#import <wifi_multicast_lock/wifi_multicast_lock-Swift.h>
#else
#import "wifi_multicast_lock-Swift.h"
#endif

@implementation WifiMulticastLockPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftWifiMulticastLockPlugin registerWithRegistrar:registrar];
}
@end
