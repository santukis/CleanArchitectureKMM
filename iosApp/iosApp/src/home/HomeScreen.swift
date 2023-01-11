//
//  HomeScreen.swift
//  iosApp
//
//  Created by David Santamaría Álvarez on 8/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

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

struct HomeContent: View {
    var homeState: HomeState
    var geometry: GeometryProxy
    
    var body: some View {
        ScrollView(.vertical) {
            VStack {
                NowPlayingContent(
                    movies: homeState.nowPlayingMovies,
                    geometry: geometry
                )
                
                UpcomingContent(
                    movies: homeState.upcomingMovies,
                    geometry: geometry
                )
            }
        }
    }
}
