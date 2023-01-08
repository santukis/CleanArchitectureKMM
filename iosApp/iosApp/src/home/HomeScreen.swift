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
    private var movieViewModel: MovieViewModel
    
    var body: some View {
        VStack(alignment: .leading) {
            let nowPlayingMovies = movieViewModel.state(
                \.nowPlayingMoviesState,
                equals: { state1, state2 in return state1 == state2 },
                 mapper: { nowPlayingMoviesState in return nowPlayingMoviesState.movies }
            )
            
        }.onAppear {
            movieViewModel.loadNowPlayingMovies()
        }
    }
}
