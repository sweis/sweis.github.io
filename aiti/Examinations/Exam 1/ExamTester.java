class ExamTester {
	static boolean passed = true;
	public static void main(String[] args) {
		Track candyShop = new Track("50 Cent", "Candyshop", 100);
		Track inDaClub = new Track("50 Cent", "In Da Club", 200);		
		Track richGirl = new Track("Gwen Stefani", "Rich Girl", 150);
		ASSERT(Track.numTracks()==3, "checking Track.numTracks() and Track initalization");
		
		Track ninetyNineProblems = new Track("Jay-Z", "99 Problems", 99);
		Track ninetyNineMashUp = new Track("Jay-Z/Linkin Park", "99 Problems", 100);
		Track ninetyNineRemix = new Track("Jay-Z/DJ Dangermouse", "99 Problems", 101);
		Track ikeTurner = new Track("Ike and Tina Turner", "Ike's Theme", 12);
		ASSERT(Track.numTracks()==7, "checking Track.numTracks() and Track initalization");

		MusicMatatu mm = new MusicMatatu();
		mm.addTrack(candyShop);
		mm.addTrack(inDaClub);
		mm.addTrack(richGirl);
		ASSERT(mm.numTracks() ==3, "checking mm.numTracks() and addTrack()");

		printTracks("Testing positive search by artist result: ", mm.getByArtist("50 Cent"));
		ASSERT(mm.getByArtist("50 Cent").length == 2, "checking positive search by artist");
		
		printTracks("Testing negative search result: ", mm.getByArtist("Barry Manilow"));
		ASSERT(mm.getByArtist("Barry Manilow")== null, "checking negative search by artist");
		
		mm.addTrack(ninetyNineProblems);
		mm.addTrack(ninetyNineMashUp);
		mm.addTrack(ninetyNineRemix);
		mm.addTrack(ikeTurner);
		ASSERT(mm.numTracks() ==7, "checking mm.numTracks() and addTrack()");

		printTracks("Testing positive search by song name result: ", mm
				.getBySong("99 Problems"));
		ASSERT(mm.getBySong("99 Problems").length == 3, "checking positive search by name");

		printTracks("Testing negative search by song name result: ", mm
				.getBySong("Big Pimpin'"));
		ASSERT(mm.getBySong("Big Pimpin'") == null, "checking negative search by name");

		candyShop.play();
		inDaClub.play();
		System.out.println("Testing topSong(): " + mm.topSong());
		ASSERT(mm.topSong().toString().equals("50 Cent - Candyshop"), "testing top song");
		inDaClub.play();
		richGirl.play();
		richGirl.play();
		System.out.println("Testing topSong(): " + mm.topSong());		
		ASSERT(mm.topSong().toString().equals("50 Cent - In Da Club"), "testing top song");
		richGirl.play();
		System.out.println("Testing topSong(): " + mm.topSong());		
		ASSERT(mm.topSong().toString().equals("Gwen Stefani - Rich Girl"), "testing top song");
		if (ExamTester.passed)
			System.out.println("Successfully passed all tests");
		else 
			System.out.println("Failed a test");
	}

	private static void printTracks(String header, Track[] tracks) {
		System.out.println(header);
		if (tracks != null)
			for (int i = 0; i < tracks.length; i++)
				System.out.println(tracks[i]);
		else
			System.out.println("(none)");
	}
	
	public static void ASSERT(boolean condition, String message) {
		if (!condition) 	{		
			System.out.println("MusicMatatu failed test: " + message);
			(new Error()).printStackTrace();
			ExamTester.passed = false;
		}
		
	}
}