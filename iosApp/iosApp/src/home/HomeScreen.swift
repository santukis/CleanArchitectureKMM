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
        ScrollView(.vertical) {
            VStack {
                NowPlayingContent(
                    movies: nowPlayingMoviesState.movies,
                    geometry: geometry
                )
            }
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
                    ZStack(alignment: .bottomLeading) {
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
                                        alignment: .top
                                    )
                            },
                            placeholder: {
                                ProgressView()
                                
                            }
                        )
                        .onTapGesture {
                            selectedMovie = movie.id
                        }
                        
                        
                        VStack(alignment: .leading) {
                            Text(movie.titles.title)
                                .font(.system(size: 22.0, weight: .bold))
                                .foregroundColor(Color.white)
                            
                            HStack(alignment: .center) {
                                Image(systemName: "star.fill")
                                    .font(.system(size: 10.0, weight: .bold))
                                    .foregroundColor(Color("MDB_Green"))
                                
                                Text(movie.rating.getText())
                                    .font(.system(size: 16.0, weight: .bold))
                                    .foregroundColor(Color.white)
                            }
                        }
                        .padding(.horizontal, 16)
                        .padding(.vertical, 40)
                    }
                    .frame(
                        height: geometry.size.height * 0.6,
                        alignment: .bottom
                    )
                }
            }
            .tabViewStyle(PageTabViewStyle(indexDisplayMode: .always))
            .frame(
                width: geometry.size.width,
                height: geometry.size.height * 0.6
            )
    }
}
