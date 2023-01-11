//
//  MovieDetail.swift
//  iosApp
//
//  Created by David Santamaría Álvarez on 31/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import MultiPlatformLibrary
import mokoMvvmFlowSwiftUI
import Combine


struct MovieDetailView: View {
    @EnvironmentObject
    private var movieDetailViewModel: MovieDetailViewModel
    var movieId: String

    var body: some View {
        VStack(alignment: .leading) {
            let movieDetailState = movieDetailViewModel.state(
                \.movieDetailState,
                equals: { state1, state2 in return state1 == state2 },
                mapper: { movieDetailState in return movieDetailState }
            )
            
            Text(movieDetailState.movie?.imdbId
                 ?? movieDetailState.errorMessage
                 ?? "MovieDetailState is nil"
            )
            
        }.onAppear {
            movieDetailViewModel.loadMovie(movieId: movieId)
        }
    }
}
