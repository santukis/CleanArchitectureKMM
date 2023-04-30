import SwiftUI
import MultiPlatformLibrary
import mokoMvvmFlowSwiftUI
import Combine

struct HomeScreen: View {
    @EnvironmentObject
    private var homeViewModel: HomeViewModel
    
    var body: some View {
        let moviesState = homeViewModel.state(
            \.moviesState,
            equals: { state1, state2 in return state1 == state2 },
            mapper: { moviesState in return moviesState }
        )
        
        GeometryReader { geometry in
            HomeContent(
                moviesState: moviesState,
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
    var moviesState: MoviesState
    var geometry: GeometryProxy
    
    var body: some View {
        ScrollView(.vertical) {
            VStack(spacing: 20) {
                NowPlayingContent(
                    movies: moviesState.nowPlayingMovies,
                    geometry: geometry
                )
                
                HomeSectionContent(
                    movies: moviesState.upcomingMovies,
                    sectionTitle: String(localized: "upcoming")
                )
                
                HomeSectionContent(
                    movies: moviesState.popularMovies,
                    sectionTitle: String(localized: "popular_movies")
                )
            }
        }
    }
}
