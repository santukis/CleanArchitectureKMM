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
        let nowPlayingMovies = movieViewModel.state(
            \.nowPlayingMoviesState,
            equals: { state1, state2 in return state1 == state2 },
            mapper: { nowPlayingMoviesState in return nowPlayingMoviesState }
        )
        GeometryReader { geometry in
            HomeContent(
                nowPlayingMoviesState: nowPlayingMovies,
                geometry: geometry
                
            ).onAppear {
                movieViewModel.loadNowPlayingMovies()
            }
        }.edgesIgnoringSafeArea(.all)
    }
}

struct HomeContent: View {
    var nowPlayingMoviesState: MoviesState
    var geometry: GeometryProxy
    
    var body: some View {
        VStack {
            NowPlayingContent(
                movies: nowPlayingMoviesState.movies,
                geometry: geometry
            )
        }
    }
}

struct NowPlayingContent: View {
    var movies: [Movie]
    var geometry: GeometryProxy
    
    @State private var selectedMovie: Int32 = 0
    
    var body: some View {
            TabView(selection: $selectedMovie) {
                ForEach(movies, id: \.self.id) { movie in
                    ZStack() {
                        AsyncImage(
                            url: URL(string: movie.images.posterImage?.getUrl(size: .W_342()) ?? ""),
                            content: { image in
                                image.resizable()
                                    .aspectRatio(contentMode: .fill)
                                    .overlay(
                                        Rectangle()
                                            .fill(
                                                LinearGradient(
                                                    gradient: Gradient(colors: [.clear, .black]),
                                                    startPoint: .top,
                                                    endPoint: .bottom
                                                )
                                            )
                                        ,
                                        alignment: .topTrailing
                                    )
                            },
                            placeholder: {
                                ProgressView()
                                
                            }
                        )
                        .onTapGesture {
                            selectedMovie = movie.id
                        }
                        .frame(
                            width: geometry.size.width
                        )
                    }
                }
            }
            .tabViewStyle(PageTabViewStyle(indexDisplayMode: .always))
            .frame(
                width: geometry.size.width,
                height: geometry.size.width
            )
    }
}
