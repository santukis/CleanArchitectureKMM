import SwiftUI
import MultiPlatformLibrary

@main
struct iOSApp: App {
    @StateObject
    private var movieViewModel: MovieViewModel = DependencyInjectorKt
        .getDependencyInjector(moduleDependencies: nil)
        .moviesViewModel()
    
    var body: some Scene {
        WindowGroup {
            HomeScreen()
                .environmentObject(movieViewModel)
        }
    }
}
