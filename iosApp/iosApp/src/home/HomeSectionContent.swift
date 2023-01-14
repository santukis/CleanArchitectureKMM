//
//  HomeSectionContent.swift
//  iosApp
//
//  Created by David Santamaría Álvarez on 14/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import MultiPlatformLibrary
import mokoMvvmFlowSwiftUI
import Combine

struct HomeSectionContent: View {
    var movies: [Movie]
    var sectionTitle: String
    
    var body: some View {
        VStack() {
            HStack(
                alignment: .center
            ) {
                Text(sectionTitle)
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
