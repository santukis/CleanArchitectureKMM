//
//  HomeContent.swift
//  iosApp
//
//  Created by David Santamaría Álvarez on 1/5/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import MultiPlatformLibrary
import mokoMvvmFlowSwiftUI
import Combine

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
