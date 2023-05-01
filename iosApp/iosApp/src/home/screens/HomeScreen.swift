import SwiftUI
import MultiPlatformLibrary
import mokoMvvmFlowSwiftUI
import Combine

struct HomeScreen: View {
    @EnvironmentObject
    private var homeViewModel: HomeViewModel
    
    var body: some View {
        let homeState = homeViewModel.state(
            \.homeState,
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
