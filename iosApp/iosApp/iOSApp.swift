import SwiftUI
import MultiPlatformLibrary

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            let movieViewModel: MovieViewModel = DependencyInjectorKt.getDependencyInjector(
                moduleDependencies: nil
            ).moviesViewModel()
            
            MovieDetail(
                movieViewModel: movieViewModel,
                movieId: "500"
            )
        }
    }
}
