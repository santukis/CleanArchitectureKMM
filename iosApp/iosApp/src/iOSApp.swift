import SwiftUI
import MultiPlatformLibrary

@main
struct iOSApp: App {
    @StateObject
    private var homeViewModel: HomeViewModel = DependencyInjectorIOSKt
        .getDependencyInjector()
        .homeViewModel(platformDependencies: nil)
    
    var body: some Scene {
        WindowGroup {
            HomeScreen()
                .environmentObject(homeViewModel)
        }
    }
}
