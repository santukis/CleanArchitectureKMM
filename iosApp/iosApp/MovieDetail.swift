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


struct MovieDetail: View {
    @ObservedObject var movieViewModel: MovieViewModel

    
    init(movieViewModel: MovieViewModel, movieId: String) {
        self.movieViewModel = movieViewModel
        
        self.movieViewModel.loadMovie(movieId: movieId)
    }

    var body: some View {
        VStack(alignment: .leading) {
            let response = movieViewModel.state(
                \.movieDetailState,
                equals: { (state1: MovieDetailState?, state2: MovieDetailState?) -> Bool in
                    state1 == state2
                 },
                mapper: { (movieDetailState: MovieDetailState) -> String in
                    movieDetailState.movie?.imdbId ?? movieDetailState.errorMessage ?? "No imdbId"
                }
            )
            
            Text(response)
        }
    }
}

