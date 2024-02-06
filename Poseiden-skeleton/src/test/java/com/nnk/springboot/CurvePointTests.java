package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

// @RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

//	@Test
//	public void curvePointTest() {
//		CurvePoint curvePoint = new CurvePoint(10, null, null, 10d, 30d, null);
//
//		// Save
//		curvePoint = curvePointRepository.save(curvePoint);
//		assertNotNull(curvePoint.getId());
//		assertTrue(curvePoint.getCurveId() == 10);
//
//		// Update
//		curvePoint.setCurveId(20);
//		curvePoint = curvePointRepository.save(curvePoint);
//		assertTrue(curvePoint.getCurveId() == 20);
//
//		// Find
//		List<CurvePoint> listResult = curvePointRepository.findAll();
//		assertTrue(listResult.size() > 0);
//
//		// Delete
//		Integer id = curvePoint.getId();
//		curvePointRepository.delete(curvePoint);
//		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
//		assertFalse(curvePointList.isPresent());
//	}

}
