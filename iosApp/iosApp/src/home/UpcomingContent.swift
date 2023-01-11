//
//  UpcomingMoviesContent.swift
//  iosApp
//
//  Created by David Santamaría Álvarez on 11/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import MultiPlatformLibrary
import mokoMvvmFlowSwiftUI
import Combine

struct UpcomingContent: View {
    var movies: [Movie]
    var geometry: GeometryProxy
    
    var body: some View {
        VStack() {
            HStack(
                alignment: .center
            ) {
                Text(String(localized: "upcoming"))
                    .font(.system(size: 20.0, weight: .bold))
                    .foregroundColor(Color.white)
                
                Spacer()
                
                Image(systemName: "plus")
                    .font(.system(size: 24.0, weight: .bold))
                    .foregroundColor(Color.white)
            }
            
            ScrollView(.horizontal) {
                LazyHStack {
                    ForEach(movies, id: \.self.id) { movie in
                        AsyncImage(
                            url: URL(string: movie.images.posterImage?.getUrl(size: .W_342()) ?? ""),
                            content: { image in
                                image.resizable()
                                    .aspectRatio(CGFloat(0.7), contentMode: .fill)
                                
                            },
                            placeholder: {
                                ProgressView()
                            }
                        )
                        .frame(width: 175)
                        .cornerRadius(8)
                    }
                }
            }
        }
    }
}
