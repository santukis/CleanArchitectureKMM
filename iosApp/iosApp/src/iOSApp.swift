import SwiftUI
import MultiPlatformLibrary

@main
struct iOSApp: App {
    @StateObject
    private var moviesViewModel: DefaultMoviesViewModel = DependencyInjectorIOSKt
        .getDependencyInjector(moduleDependencies: nil)
        .moviesViewModel(platformDependencies: nil) as! DefaultMoviesViewModel
    
    var body: some Scene {
        WindowGroup {
            HomeScreen()
                .environmentObject(moviesViewModel)
        }
    }
}
