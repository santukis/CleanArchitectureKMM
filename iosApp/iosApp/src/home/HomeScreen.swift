import SwiftUI
import MultiPlatformLibrary
import mokoMvvmFlowSwiftUI
import Combine

struct HomeScreen: View {
    @EnvironmentObject
    private var homeViewModel: HomeViewModel
    
    var body: some View {
        let homeState = homeViewModel.state(
            \.moviesState,
            equals: { state1, state2 in return state1 == state2 },
            mapper: { homeState in return homeState }
        )
        
        GeometryReader { geometry in
            HomeContent(
                homeState: homeState,
                geometry: geometry
                
            ).onAppear {
                homeViewModel.loadHomeData()
            }
        }
        .edgesIgnoringSafeArea(.all)
        .background(Color.black)
    }
}

struct HomeContent: View {
    var homeState: HomeState
    var geometry: GeometryProxy
    
    var body: some View {
        ScrollView(.vertical) {
            VStack(spacing: 20) {
                NowPlayingContent(
                    movies: homeState.nowPlayingMovies,
                    geometry: geometry
                )
                
                HomeSectionContent(
                    movies: homeState.upcomingMovies,
                    sectionTitle: String(localized: "upcoming")
                )
                
                HomeSectionContent(
                    movies: homeState.popularMovies,
                    sectionTitle: String(localized: "popular_movies")
                )
            }
        }
    }
}
