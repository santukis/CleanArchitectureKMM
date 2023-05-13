import SwiftUI
import MultiPlatformLibrary

@main
struct iOSApp: App {
    @StateObject
    private var homeViewModel: HomeViewModel = DependencyInjectorProvider.shared
        .get()
        .homeViewModel(platformDependencies: nil)
    
    init() {
        DependencyInjectorProvider.shared
            .initialize(
                provider: DependencyInjectorProvider.Provider.kodein,
                moduleDependencies: nil
            )
    }
    
    var body: some Scene {
        WindowGroup {
            HomeScreen()
                .environmentObject(homeViewModel)
        }
    }
}
