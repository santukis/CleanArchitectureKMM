import SwiftUI
import MultiPlatformLibrary

@main
struct iOSApp: App {
    @StateObject
    private var moviesViewModel: MoviesViewModel = DependencyInjectorIOSKt
        .getDependencyInjector(moduleDependencies: nil)
        .moviesViewModel(platformDependencies: nil)
    
    var body: some Scene {
        WindowGroup {
            HomeScreen()
                .environmentObject(moviesViewModel)
        }
    }
}
