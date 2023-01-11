import SwiftUI
import MultiPlatformLibrary

@main
struct iOSApp: App {
    @StateObject
    private var homeViewModel: HomeViewModel = DependencyInjectorKt
        .getDependencyInjector(moduleDependencies: nil)
        .homeViewModel()
    
    var body: some Scene {
        WindowGroup {
            HomeScreen()
                .environmentObject(homeViewModel)
        }
    }
}
