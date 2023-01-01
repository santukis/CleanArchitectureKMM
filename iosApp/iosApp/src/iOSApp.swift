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
            MovieDetailView(movieId: "500")
                .environmentObject(movieViewModel)
        }
    }
}
