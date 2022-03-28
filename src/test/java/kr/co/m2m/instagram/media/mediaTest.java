package kr.co.m2m.instagram.media;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.m2m.instagram.media.service.MediaService;
import lombok.extern.java.Log;

class mediaTest {

	@Autowired
	MediaService mservice;
	
	@Test
	void testInsertPostMedia() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertProfileMedia() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateMedia() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteMedia() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectPostMedia() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectProfileMedia() {
		fail("Not yet implemented");
	}

	@Test
	void testUploadFile() {
		String filename = mservice.uploadFile(null);
		assertNotNull(filename);
		fail("Not yet implemented");
	}

}
