import SwiftUI

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
            let secret = Bundle.main.infoDictionary?["MOVIE_DATABASE_SECRET"] as? String ?? "No value stored"
            
            VStack {
                Text(secret)
            }
		}
	}
}
